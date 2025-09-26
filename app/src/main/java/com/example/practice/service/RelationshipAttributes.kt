package com.example.practice.service

import com.google.gson.annotations.SerializedName

data class RelationshipAttributes(
    @SerializedName("fileName")
    val fileName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("volume")
    val volume: String? = null,
    @SerializedName("chapter")
    val chapter: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
    @SerializedName("version")
    val version: Int? = null
)