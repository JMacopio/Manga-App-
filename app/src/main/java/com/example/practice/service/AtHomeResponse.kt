package com.example.practice.service

import com.google.gson.annotations.SerializedName

data class AtHomeResponse (
    @SerializedName("result")
    val result: String,
    @SerializedName("baseUrl")
    val baseUrl: String,
    @SerializedName("chapter")
    val chapter: ChapterImageData
)

data class ChapterImageData(
    @SerializedName("hash")
    val hash: String,
    @SerializedName("data")
    val data: List<String>,
    @SerializedName("dataSaver")
    val dataSaver: List<String>
)