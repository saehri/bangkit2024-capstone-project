package com.example.kulit
import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.kulit.response.PredictRequest
import com.example.kulit.response.PredictResponse
import com.example.kulit.retrofit.ApiConfig
import com.example.kulit.response.UploadResponse
import com.example.kulit.result.ResultActivity
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var scanImageButton: Button
    private lateinit var takePictureButton: Button
    private lateinit var selectedImageView: ImageView
    private lateinit var overlayProcessingLayout: LinearLayout
    private lateinit var tvStatus: TextView
    private lateinit var tvDone: TextView
    private var photoUri: Uri? = null

    private val REQUEST_CODE_READ_STORAGE = 1001
    private val REQUEST_CODE_CAMERA = 1002
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageView.setImageURI(it)
            startProcessingActivity(it)
        }
    }

    private val timeStamp: String = SimpleDateFormat(
        "dd-MMM-yyyy",
        Locale.US
    ).format(System.currentTimeMillis())

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
        if (success) {
            photoUri?.let {
                selectedImageView.setImageURI(it)
                startProcessingActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanImageButton = findViewById(R.id.btnSelectImage)
        takePictureButton = findViewById(R.id.btnTakePicture)
        selectedImageView = findViewById(R.id.ivPhoto)
        overlayProcessingLayout = findViewById(R.id.overlayProcessingLayout)
        tvStatus = findViewById(R.id.tvStatus)
        tvDone = findViewById(R.id.tvDone)

        scanImageButton.setOnClickListener {
            checkAndRequestPermissions()
        }

        takePictureButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val tempFile = File.createTempFile("photo_", ".jpg", externalCacheDir)
                photoUri = FileProvider.getUriForFile(this, "com.example.kulit.provider", tempFile)
                takePicture.launch(photoUri)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)
            }
        }
    }

    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_READ_STORAGE)
        } else {
            getImage.launch("image/*")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin kamera diberikan, lanjutkan untuk mengambil foto
                val tempFile = File.createTempFile("photo_", ".jpg", externalCacheDir)
                photoUri = FileProvider.getUriForFile(this, "com.example.kulit.provider", tempFile)
                takePicture.launch(photoUri)
            } else {
                // Jika izin ditolak, beri tahu pengguna
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startProcessingActivity(image: Uri?) {
        image?.let {
            toggleUIVisibility(false)
            Thread {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    Log.e("MainActivity", "Error during sleep: ${e.message}", e)
                }
                runOnUiThread {
                    tvStatus.visibility = TextView.GONE
                    tvDone.visibility = TextView.VISIBLE
                }
                runOnUiThread {
                    uploadImage(it) // Pass the non-null Uri
                }
            }.start()
        }
    }

    private fun uploadImage(imageUri: Uri) {
        val imageFile = uriToFile(imageUri, this)
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
        val body = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        val description = RequestBody.create("text/plain".toMediaTypeOrNull(), "Image description")

//        val intent = Intent(this@MainActivity, ResultActivity::class.java)
//        performSkinAnalysis("https://storage.googleapis.com/bucket-divaskin/DALL%C2%B7E%202024-12-12%2012.07.16%20-%20A%20visually%20stunning%20and%20futuristic%20background%20image%20for%20a%20HackerRank%20contest%20landing%20page%20named%20%27Supernova%27.%20The%20theme%20should%20include%20elements%20of%20a%20co.jpg", intent)

        ApiConfig.instance.uploadImage(body, description).enqueue(object : Callback<UploadResponse> {
            override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                if (response.isSuccessful) {
                    val uploadResponse = response.body()
                    Toast.makeText(this@MainActivity, "Upload successful: ${uploadResponse?.message}", Toast.LENGTH_SHORT).show()

                    // Proceed with the skin analysis after successful upload
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra("imageUri", imageUri.toString())  // Pass URI as a string

                    // Perform skin analysis
                    performSkinAnalysis(uploadResponse?.url ?: "", intent)
                } else {
                    Log.e("MainActivity", "Upload failed. Response: ${response.errorBody()?.string()}")
                    // Proceed with ResultActivity even if the upload failed
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra("imageUri", imageUri.toString())  // Pass URI even if upload fails
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Log.e("MainActivity", "Upload failed: ${t.message}", t)
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("imageUri", imageUri.toString())  // Pass URI even if upload fails
                startActivity(intent)
            }
        })
    }

    private fun performSkinAnalysis(imageUrl:String, intent: Intent) {
        ApiConfig.instance.getSkinAnalysis(PredictRequest(imageUrl)).enqueue(object : Callback<PredictResponse> {
            override fun onResponse(call: Call<PredictResponse>, response: Response<PredictResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val analysisResult = response.body()
                    Log.d("MainActivity", "Skin analysis successful: $analysisResult")

                    // Pass the PredictResponse to ResultActivity
                    intent.putExtra("predictResponse", analysisResult)  // Pass the analysis result

                    // Start the ResultActivity with the analysis result
                    startActivity(intent)
//                    finish() // Close MainActivity
                } else {
                    Log.e("MainActivity", "Skin analysis failed: ${response.errorBody()?.string()}")
                    // Handle failure scenario here if needed
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                Log.e("MainActivity", "Skin analysis failed: ${t.message}", t)
                // Handle failure - show a message to the user
            }
        })
    }


    fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createTempFile(context)

        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    fun createTempFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }




    private fun getRealPathFromURI(uri: Uri): String? {
        val cursor = contentResolver.query(uri, arrayOf(MediaStore.Images.Media.DATA), null, null, null)
        cursor?.let {
            it.moveToFirst()
            val index = it.getColumnIndex(MediaStore.Images.Media.DATA)
            val path = it.getString(index)
            it.close()
            return path
        }
        return null
    }

    private fun toggleUIVisibility(isProcessing: Boolean) {
        if (isProcessing) {
            scanImageButton.visibility = Button.GONE
            takePictureButton.visibility = Button.GONE
            selectedImageView.visibility = ImageView.GONE
            overlayProcessingLayout.visibility = LinearLayout.VISIBLE
            tvStatus.visibility = TextView.VISIBLE
            tvDone.visibility = TextView.GONE
        } else {
            scanImageButton.visibility = Button.VISIBLE
            takePictureButton.visibility = Button.VISIBLE
            selectedImageView.visibility = ImageView.VISIBLE
            overlayProcessingLayout.visibility = LinearLayout.GONE
        }
    }
}
