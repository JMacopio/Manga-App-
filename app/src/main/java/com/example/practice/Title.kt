package com.example.practice


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("en")
    val en: String
)