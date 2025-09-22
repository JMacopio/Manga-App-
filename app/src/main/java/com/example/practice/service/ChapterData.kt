package com.example.practice.service

import com.example.practice.Relationship
import com.google.gson.annotations.SerializedName

data class ChapterData (
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: ChapterAttributes,
    @SerializedName("relationships")
    val relationships: List<Relationship>
    )

data class ChapterAttributes(
    @SerializedName("title")
    val title: String?,
    @SerializedName("volume")
    val volume: String?,
    @SerializedName("chapter")
    val chapter: String?,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("translatedLanguage")
    val translatedLanguage: String,
    @SerializedName("uploader")
    val uploader: String,
    @SerializedName("externalUrl")
    val externalUrl: String?,
    @SerializedName("version")
    val version: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("publishAt")
    val publishAt: String,
    @SerializedName("readableAt")
    val readableAt: String
)

