package com.example.kulit.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(

	@field:SerializedName("message")
	val message: String?,

	@field:SerializedName("url")
	val url: String?
)
