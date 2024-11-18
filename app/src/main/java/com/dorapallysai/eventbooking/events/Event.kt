package com.dorapallysai.eventbooking.events

data class Event(
    val eventname: String = "",
    val eventdescription: String = "",
    val eventdate: String = "",
    val eventtime: String = "",
    val eventlocation: String = "",
    val eventimage: Int = 0,
    val category: String = ""
)
