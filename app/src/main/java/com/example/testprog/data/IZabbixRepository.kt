package com.example.testprog.data

import com.example.testprog.data.domain.AuthZab
import com.example.testprog.data.domain.TriggerZab

interface IZabbixRepository {
    suspend fun authZabbix(authParam : AuthParam): AuthZab
    suspend fun loadProblemZab():TriggerZab
}