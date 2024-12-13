package com.example.kulit.retrofit

import com.example.kulit.response.PredictRequest
import com.example.kulit.response.PredictResponse
import com.example.kulit.response.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ): Call<UploadResponse>

    @Headers("Content-Type: application/json")
    @POST("predict")
    fun getSkinAnalysis(

       @Body request:PredictRequest
    ): Call<PredictResponse>
}
