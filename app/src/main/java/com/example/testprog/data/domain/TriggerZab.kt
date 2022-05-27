package com.example.testprog.data.domain

import com.google.gson.annotations.SerializedName

data class TriggerZab(
    @SerializedName("jsonrpc")
    val jsonrpc: String,
    @SerializedName("result")
    val result: List<ResultTrigger>,
    @SerializedName("id")
    val id: Int
)

data class ResultTrigger(
    @SerializedName("triggerid")
    val triggerId: String,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lastchange")
    val lastChange: String,
    @SerializedName("hosts")
    val hosts: List<HostZab>
)

data class HostZab(
    @SerializedName("hostid")
    val hostId: String,
    @SerializedName("host")
    val host: String
)
