package com.example.testprog.data

import com.example.testprog.data.domain.*

class ZabbixLocalRepositoryImpl : IZabbixRepository {
    override suspend fun authZabbix(authParam: AuthParam): AuthZab {
        TODO("Not yet implemented")
    }

    override suspend fun loadProblemZab(): TriggerZab {
        val testResultProblem = ResultTrigger(
            triggerId = "22621",
            priority = "4",
            lastChange = "1650530964",
            description = "High CPU utilization (over 90% for 5m)",
            hosts = listOf(HostZab("10522", "S-Win1C-TLK"))
        )
        return TriggerZab(
            jsonrpc = "",
            result = listOf<ResultTrigger>(testResultProblem),
            id = 1
        )
    }
}