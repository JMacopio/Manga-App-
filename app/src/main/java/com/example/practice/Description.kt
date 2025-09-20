package com.example.practice


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String,
    @SerializedName("es-la")
    val esLa: String,
    @SerializedName("hu")
    val hu: String,
    @SerializedName("it")
    val `it`: String,
    @SerializedName("ja")
    val ja: String,
    @SerializedName("pl")
    val pl: String,
    @SerializedName("pt-br")
    val ptBr: String,
    @SerializedName("uk")
    val uk: String
)