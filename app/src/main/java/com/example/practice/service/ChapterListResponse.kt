package com.example.practice.service

import com.google.gson.annotations.SerializedName

data class ChapterListResponse (
    @SerializedName("result")
    val result: String,
    @SerializedName("response")
    val response: String,
    @SerializedName("data")
    val data: List<ChapterData>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)