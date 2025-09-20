package com.example.practice


import com.google.gson.annotations.SerializedName

data class Relationship(
    @SerializedName("id")
    val id: String,
    @SerializedName("related")
    val related: String,
    @SerializedName("type")
    val type: String
)