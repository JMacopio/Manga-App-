package com.example.practice


import com.google.gson.annotations.SerializedName

data class AltTitle(
    @SerializedName("en")
    val en: String,
    @SerializedName("es-la")
    val esLa: String,
    @SerializedName("fr")
    val fr: String,
    @SerializedName("it")
    val `it`: String,
    @SerializedName("ja")
    val ja: String,
    @SerializedName("ja-ro")
    val jaRo: String,
    @SerializedName("kk")
    val kk: String,
    @SerializedName("pl")
    val pl: String,
    @SerializedName("pt-br")
    val ptBr: String,
    @SerializedName("ru")
    val ru: String,
    @SerializedName("tr")
    val tr: String,
    @SerializedName("uk")
    val uk: String,
    @SerializedName("vi")
    val vi: String,
    @SerializedName("zh")
    val zh: String,
    @SerializedName("zh-ro")
    val zhRo: String
){
    // Helper function to get the first available title
    fun getFirstAvailableTitle(): String? {
        return listOfNotNull(en, esLa, fr, it, ja, jaRo, kk, pl, ptBr, ru, tr, uk, vi, zh, zhRo).firstOrNull()
    }
}