package appS3027403.dorapallysai.eventbooking.events

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import appS3027403.dorapallysai.eventbooking.R
import appS3027403.dorapallysai.eventbooking.UserDetails
import appS3027403.dorapallysai.eventbooking.events.Selectevent.event
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit

class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventDetails()
        }
    }
}

@Composable
fun EventDetails() {
    val currentActivity = LocalContext.current as Activity
    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Blue
                )
                .padding(vertical = 6.dp, horizontal = 16.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        currentActivity.finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "back"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Event Details",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = event.eventimage),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(12.dp, 0.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = event.eventname,
                color = Color.Blue,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                fontWeight = FontWeight.Bold,

                )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.baseline_description_24),
                    contentDescription = "Description"
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = event.eventdescription,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp)

                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = "Pin",
                )
                Spacer(modifier = Modifier.width(4.dp))


                Text(
                    text = event.eventlocation,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                )

            }

            if (event.eventlocation != "Online") {
                Text(
                    text = "See Location",
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    modifier = Modifier
                        .padding(start = 40.dp, top = 4.dp)
                        .clickable {
                            openCampusLocation(currentActivity)
                        }
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                    contentDescription = "Location",
                )
                Spacer(modifier = Modifier.width(4.dp))


                Text(
                    text = event.eventdate,
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp)


                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            )

            {
                Image(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "Time",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = event.eventtime,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    fontSize = 16.sp,
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            BookNowCard(event)

        }
    }
}

@Composable
fun BookNowCard(event: Event) {
    val currentActivity = LocalContext.current

    var name by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var jobTitle by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InputField(label = "Name", textValue = name, onTextChange = { name = it })
            InputField(label = "Company", textValue = company, onTextChange = { company = it })
            InputField(label = "Email Address", textValue = email, onTextChange = { email = it })
            InputField(label = "Job Title", textValue = jobTitle, onTextChange = { jobTitle = it })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isEmpty() || company.isBlank() || email.isBlank() || jobTitle.isBlank()) {
                        Toast.makeText(currentActivity, "Fields Missing", Toast.LENGTH_SHORT).show()
                    } else {
                        event.guestName = name
                        event.companyName = company
                        event.guestEmail = email
                        event.jobTitle = jobTitle

                        Selectevent.bookingEvent=event
                        currentActivity.startActivity(Intent(currentActivity, BookingConfirmationActivity::class.java))
                        (currentActivity as Activity).finish()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)

            ) {
                Text("Continue To Book")

            }
        }
    }
}

@Composable
fun InputField(label: String, textValue: String, onTextChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small)
                .padding(8.dp)
        ) {
            BasicTextField(
                value = textValue,
                onValueChange = onTextChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EventDetails()
}

fun bookEvent(event: Event, currentActivity: Context) {

    val db = FirebaseDatabase.getInstance()
    val ref = db.getReference("MyEventBookings")

    val currentDate = LocalDate.now() // Get today's date
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define the format
    event.dateOfBooking = currentDate.format(formatter) // Format the date


    val orderId = event.eventId.toString()

    val userEmail = UserDetails.getEmail(currentActivity)!!

    ref.child(userEmail.replace(".", ",")).child(orderId).setValue(event)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {

                scheduleEventNotification(event.eventdate, event.eventname, currentActivity)
                (currentActivity as Activity).finish()
                Toast.makeText(currentActivity, "Booked Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    currentActivity,
                    "Booking Failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(
                currentActivity,
                "Booking Failed: ${exception.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
}

fun openCampusLocation(currentActivity: Context) {
    val uri = Uri.parse("geo:0,0?q=Teesside+University")
    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }

    if (intent.resolveActivity(currentActivity.packageManager) != null) {
        currentActivity.startActivity(intent)
    } else {
        Toast.makeText(currentActivity, "Google Maps app not installed.", Toast.LENGTH_SHORT).show()
    }
}

fun scheduleEventNotification(eventDate: String, eventName: String, currentActivity: Context) {

    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val eventDateParsed = dateFormat.parse(eventDate)

    val currentTime = System.currentTimeMillis()

    if (eventDateParsed != null && eventDateParsed.time > currentTime) {
        val timeDelay = eventDateParsed.time - currentTime

        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(timeDelay, TimeUnit.MILLISECONDS) // Set delay based on the event time
            .setInputData(workDataOf("event_name" to eventName)) // Pass event name
            .build()

        WorkManager.getInstance(currentActivity).enqueue(workRequest)
    } else {

        Toast.makeText(currentActivity,"Event Completed",Toast.LENGTH_SHORT).show()
        Log.d("EventNotification", "Event is in the past, no notification scheduled.")
    }
}




