package com.example.practice.service
import com.example.practice.Data
import com.google.gson.annotations.SerializedName

data class MangaDexSingleResponse (
    @SerializedName("result")
    val result: String,
    @SerializedName("response")
    val response: String,
    @SerializedName("data")
    val data: Data
)