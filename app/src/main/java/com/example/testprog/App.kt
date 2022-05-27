package com.example.testprog

import android.app.Application
import android.content.Context
import com.example.testprog.data.AuthParam

class App:Application() {

    private val authParam:AuthParam = AuthParam()
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
   fun setAuthParam(login:String,passwd:String) {
        authParam.login = login
        authParam.passwd = passwd
    }
    fun setAuthKey(key:String) {
        authParam.authKey = key
    }
   fun getAuthKey():String = authParam.authKey
   fun getAutParam() = authParam
    companion object {
         lateinit var instance:App
         private set
    }
}