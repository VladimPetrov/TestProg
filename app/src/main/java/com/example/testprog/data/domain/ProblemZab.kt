package com.example.testprog.data

import com.google.gson.annotations.SerializedName

data class ProblemZab(
    @SerializedName("jsonrpc")
    val jsonrpc:String,
    @SerializedName("result")
    val result :List<RezultProblem>,
    @SerializedName("id")
    val id :Int
)
data class RezultProblem(
    @SerializedName("eventid")
    val eventid :String,
    @SerializedName("source")
    val source :String,
    @SerializedName("object")
    val objectParam :String,
    @SerializedName("objectid")
    val objectid :String,
    @SerializedName("clock")
    val clock :String,
    @SerializedName("ns")
    val ns :String,
    @SerializedName("r_eventid")
    val rEventid :String,
    @SerializedName("r_clock")
    val rClock :String,
    @SerializedName("r_ns")
    val rNs :String,
    @SerializedName("correlationid")
    val correlationid :String,
    @SerializedName("userid")
    val userid :String,
    @SerializedName("name")
    val name :String,
    @SerializedName("acknowledged")
    val acknowledged :String,
    @SerializedName("opdata")
    val opdata :String,
    @SerializedName("suppressed")
    val suppressed :String,
    @SerializedName("severity")
    val severity :String,
    //@SerializedName("acknowledges")
    //val acknowledges :List<Acknowledges>,
    //@SerializedName("tags")
    //val tags :List<Tag>,
    //@SerializedName("suppression_data")
    //val suppressionData :List<Suppression>,
)
data class Acknowledges(
    @SerializedName("acknowledgeid")
    val acknowledgeid :String,
    @SerializedName("userid")
    val userid :String,
    @SerializedName("eventid")
    val eventid :String,
    @SerializedName("clock")
    val clock :String,
    @SerializedName("message")
    val message :String,
    @SerializedName("action")
    val action :String,
    @SerializedName("old_severity")
    val oldSeverity :String,
    @SerializedName("new_severity")
    val newSeverity :String,
)
data class Tag(
    @SerializedName("tag")
    val tag :String,
    @SerializedName("value")
    val value :String,
)
data class Suppression(
    @SerializedName("maintenanceid")
    val maintenanceid:String,
    @SerializedName("suppress_until")
    val suppressUntil:String,
)
