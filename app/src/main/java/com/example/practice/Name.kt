package com.example.practice


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("en")
    val en: String
)