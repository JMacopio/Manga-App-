package com.example.practice


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("al")
    val al: String,
    @SerializedName("amz")
    val amz: String,
    @SerializedName("ap")
    val ap: String,
    @SerializedName("bw")
    val bw: String,
    @SerializedName("cdj")
    val cdj: String,
    @SerializedName("ebj")
    val ebj: String,
    @SerializedName("engtl")
    val engtl: String,
    @SerializedName("kt")
    val kt: String,
    @SerializedName("mal")
    val mal: String,
    @SerializedName("mu")
    val mu: String,
    @SerializedName("nu")
    val nu: String,
    @SerializedName("raw")
    val raw: String
)