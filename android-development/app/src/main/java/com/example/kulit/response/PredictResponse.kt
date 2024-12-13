package com.example.kulit.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class PredictResponse(
	@field:SerializedName("hasil_analisa") val hasilAnalisa: String?,
	@field:SerializedName("akurasi") val akurasi: String?,
	@field:SerializedName("rekomendasi") val rekomendasi: Rekomendasi?,
	@field:SerializedName("deskripsi") val deskripsi: String?
) : Parcelable
data class PredictRequest(
	@field:SerializedName("image_url") val imageUrl: String
)
@Parcelize
data class Rekomendasi(
	@field:SerializedName("Facial Wash") val facialWash: FacialWash?,
	@field:SerializedName("Sunscreen") val sunscreen: Sunscreen?,
	@field:SerializedName("Moisturizer") val moisturizer: Moisturizer?
) : Parcelable

@Parcelize
data class FacialWash(
	@field:SerializedName("image") val image: String?,
	@field:SerializedName("link") val link: String?,
	@field:SerializedName("name") val name: String?
) : Parcelable

@Parcelize
data class Sunscreen(
	@field:SerializedName("image") val image: String?,
	@field:SerializedName("link") val link: String?,
	@field:SerializedName("name") val name: String?
) : Parcelable

@Parcelize
data class Moisturizer(
	@field:SerializedName("image") val image: String?,
	@field:SerializedName("link") val link: String?,
	@field:SerializedName("name") val name: String?
) : Parcelable
