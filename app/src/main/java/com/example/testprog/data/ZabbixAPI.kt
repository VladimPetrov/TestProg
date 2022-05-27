package com.example.testprog.data

import com.example.testprog.data.domain.AuthZab
import com.example.testprog.data.domain.TriggerZab
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ZabbixAPI {
    @Headers("Content-Type: application/json-rpc")
    @POST("api_jsonrpc.php")
    suspend fun loadAuthKey(
        @Body() param: RequestBody
    ) : AuthZab

    @Headers("Content-Type: application/json-rpc")
    @POST("api_jsonrpc.php")
    suspend fun loadProblem(
        @Body() param: RequestBody
    ) : TriggerZab
}