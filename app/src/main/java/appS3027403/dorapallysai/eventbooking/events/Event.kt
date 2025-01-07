package appS3027403.dorapallysai.eventbooking.events

data class Event(
    val eventname: String = "",
    val eventdescription: String = "",
    val eventdate: String = "",
    val eventtime: String = "",
    val eventlocation: String = "",
    val eventimage: Int = 0,
    val category: String = "",
    val eventId : Int =0,
    var dateOfBooking : String = "",
    val weeksBooked : String = "",
    var guestName : String = "",
    var guestEmail : String = "",
    var companyName : String = "",
    var jobTitle : String = ""
)
