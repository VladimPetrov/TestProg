package com.example.testprog.data.domain

import com.example.testprog.data.domain.ErrorZab
import com.google.gson.annotations.SerializedName

data class AuthZab(
    @SerializedName("jsonrpc")
    val versionRPC : String,
    @SerializedName("error")
    val error : ErrorZab,
    @SerializedName("result")
    val authKey : String,
    @SerializedName("id")
    val id : Int
)
