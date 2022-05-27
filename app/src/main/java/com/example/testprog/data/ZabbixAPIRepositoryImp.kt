package com.example.testprog.data


import com.example.testprog.App
import com.example.testprog.data.domain.AuthZab
import com.example.testprog.data.domain.TriggerZab
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://192.168.254.119/"
private const val RESERVED_URL = "http://192.168.254.115/"

class ZabbixAPIRepositoryImp : IZabbixRepository {
    private val apiAuth = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()
        )
        .build()
        .create(ZabbixAPI::class.java)

    private val apiProblem = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()
        )
        .build()
        .create(ZabbixAPI::class.java)

    override suspend fun authZabbix(authParam: AuthParam): AuthZab {
        val jsonParam = JSONObject()
        val loginParam = JSONObject()

        loginParam.put("user", authParam.login)
        loginParam.put("password", authParam.passwd)
        jsonParam.put("jsonrpc","2.0")
        jsonParam.put("method", "user.login")
        jsonParam.put("params",loginParam)
        jsonParam.put("id", 1)
        jsonParam.put("auth", JSONObject.NULL)
        val paramRequest = jsonParam.toString().toRequestBody("application/json".toMediaType())

        return apiAuth.loadAuthKey(paramRequest)
    }

    override suspend fun loadProblemZab(): TriggerZab {
        //var dateStart = LocalDateTime.now().minusDays(2)
        val jsonParam = JSONObject()
        val jsonParam2 = JSONArray()
        val jsonParam3 = JSONArray()
        val jsonParam4 = JSONObject()
        val contentParam = JSONObject()
        jsonParam2.put("trigersid")
        jsonParam2.put("description")
        jsonParam2.put("priority")
        jsonParam2.put("hosts")
        jsonParam2.put("lastchange")
        jsonParam3.put("host")
        jsonParam4.put("value", 1)
        contentParam.put("output", jsonParam2)
        contentParam.put("selectHosts", jsonParam3)
        contentParam.put("filter", jsonParam4)
        jsonParam.put("jsonrpc","2.0")
        jsonParam.put("method", "trigger.get")
        jsonParam.put("params",contentParam)
        jsonParam.put("id", 1)
        jsonParam.put("auth", App.instance.getAuthKey())
        //jsonParam.put("auth", "126cc5c50452ccd15ba86246a71d54c5")
        val paramRequest = jsonParam.toString().toRequestBody("application/json".toMediaType())
        return apiProblem.loadProblem(paramRequest)
    }
}