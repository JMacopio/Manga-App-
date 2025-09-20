package com.example.practice


import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("description")
    val description: DescriptionX,
    @SerializedName("group")
    val group: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("version")
    val version: Int
)