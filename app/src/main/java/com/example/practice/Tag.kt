package com.example.practice


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("attributes")
    val attributes: AttributesX,
    @SerializedName("id")
    val id: String,
    @SerializedName("relationships")
    val relationships: List<Any?>,
    @SerializedName("type")
    val type: String
)