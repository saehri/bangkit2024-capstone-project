package com.example.kulit.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kulit.MainActivity
import com.example.kulit.R
import com.example.kulit.response.PredictResponse
import com.example.kulit.response.FacialWash
import com.example.kulit.response.Sunscreen
import com.example.kulit.response.Moisturizer
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvAccuracy: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivPhoto: ImageView
    private lateinit var btnBackToHome: Button
    private lateinit var rvProductItems: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var recommendedProducts: List<Any> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Initialize UI components
        tvResult = findViewById(R.id.tvResult)
        tvAccuracy = findViewById(R.id.tvAccuracy)  // TextView for accuracy
        tvDescription = findViewById(R.id.tvDescription)  // TextView for description
        ivPhoto = findViewById(R.id.ivPhoto)
        btnBackToHome = findViewById(R.id.btnBackToHome)
        rvProductItems = findViewById(R.id.rvProductItems)

        // Set up RecyclerView and Adapter
        productAdapter = ProductAdapter()
        rvProductItems.layoutManager = LinearLayoutManager(this)
        rvProductItems.adapter = productAdapter

        // Get the passed PredictResponse and image URI from the Intent
        val predictResponse: PredictResponse? = intent.getParcelableExtra("predictResponse")
        Log.d("ResultActivity", "Received PredictResponse: $predictResponse") // Log the entire response

        // Show the result using the showResult function
        predictResponse?.let { showResult(it) }

        // Set the image to the ImageView
        val imageUriString = intent.getStringExtra("imageUri")
        Log.d("ResultActivity", "Received Image URI: $imageUriString") // Log image URI
        val imageUri = Uri.parse(imageUriString)
        ivPhoto.setImageURI(imageUri)

        // Show recommended products in RecyclerView
        predictResponse?.let {
            recommendedProducts = it.rekomendasi?.let { rekomendasi ->
                listOfNotNull(rekomendasi.facialWash, rekomendasi.sunscreen, rekomendasi.moisturizer)
            } ?: listOf()
            productAdapter.submitList(recommendedProducts)
        }

        // Set an OnClickListener for the Back to Home button
        btnBackToHome.setOnClickListener {
            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showResult(predictResponse: PredictResponse) {
        // Extract the values from PredictResponse
        val result = predictResponse.hasilAnalisa ?: "No result available"
        val accuracy = predictResponse.akurasi ?: "N/A"
        val description = predictResponse.deskripsi ?: "No description available"

        // Log the values
        Log.d("ResultActivity", "Skin Analysis Result: $result") // Log the result
        Log.d("ResultActivity", "Accuracy: $accuracy") // Log the accuracy
        Log.d("ResultActivity", "Description: $description") // Log the description

        // Display the skin analysis result in the TextView
        tvResult.text = "Your Skin Analysis Result:\n$result"
        tvAccuracy.text = "Accuracy: $accuracy"
        tvDescription.text = "Description: $description"

        // Display the analysis time
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        tvResult.append("\nAnalyzed on: $currentTime")
    }
}
