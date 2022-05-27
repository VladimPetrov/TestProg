package com.example.testprog.data.domain

import com.google.gson.annotations.SerializedName

data class ErrorZab(
    @SerializedName("code")
    val code : Int,
    @SerializedName("message")
    val message :String,
    @SerializedName("data")
    val data :String,
)
