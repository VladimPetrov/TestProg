package com.example.testprog.data

import com.example.testprog.R
import com.example.testprog.data.domain.HostZab
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun clockZabbixToString(numSeconds: Int) = LocalDateTime
    .parse("1970-01-01T00:00:00")
    .plusSeconds(numSeconds.toLong())
    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

fun getSeverityColor(num: Int) =
    when (num) {
        5 -> R.color.color_severity_5
        4 -> R.color.color_severity_4
        3 -> R.color.color_severity_3
        2 -> R.color.color_severity_2
        else -> R.color.color_severity_1
    }

fun getSeverityIcon(num: Int) =
    when (num) {
        5 -> R.drawable.ic_baseline_report_problem_24
        4 -> R.drawable.ic_baseline_report_problem_24
        3 -> R.drawable.ic_baseline_report_24
        2 -> R.drawable.ic_baseline_report_24
        else -> R.drawable.ic_baseline_report_24
    }
fun getHostName(hostList:List<HostZab>) : String {
    var str : String = ""
    for (host in hostList) {
        if (str.count() == 0) str = host.host
          else str += ","+host.host
    }
    return str
}