package com.example.practice


import com.example.practice.service.RelationshipAttributes
import com.google.gson.annotations.SerializedName

data class Relationship(
    @SerializedName("id")
    val id: String,
    @SerializedName("related")
    val related: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: RelationshipAttributes? = null
)

