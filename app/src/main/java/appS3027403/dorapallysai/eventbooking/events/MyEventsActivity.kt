package appS3027403.dorapallysai.eventbooking.events

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appS3027403.dorapallysai.eventbooking.R
import appS3027403.dorapallysai.eventbooking.UserDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyEventsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookings()
        }
    }
}

@Composable
fun MyBookings() {
    val currentActivity = LocalContext.current as Activity

    val userEmail = UserDetails.getEmail(currentActivity)!!

    var bookedEventsList by remember { mutableStateOf(listOf<Event>()) }

    // Fetch orders
    LaunchedEffect(userEmail) {
        getBookedEvents(userEmail) { events ->
            bookedEventsList = events
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                        (currentActivity as Activity).finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "back"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "My Booked Events",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }


        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {

            items(bookedEventsList.size) { index ->
                BookedEventItem(
                    bookedEventsList[index]
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}


@Composable
fun BookedEventItem(event: Event)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp), // Add horizontal padding
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
    )
    {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), // Padding inside the card
            verticalAlignment = Alignment.Top // Align content to the top
        )

        {
            // Event Image
            Image(
                painter = painterResource(id = event.eventimage), // Replace with your image resource
                contentDescription = "Event Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)) // Optional rounded corners
                    .border(1.dp, MaterialTheme.colorScheme.primary),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text

            // Event Text Details
            Column(
                modifier = Modifier.align(Alignment.Top),
                verticalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = event.eventname,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Booked on: ${event.dateOfBooking}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(8.dp)) // Space between text and icons

                // Date and Time Icons Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,


                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24), // Replace with your calendar icon
                        contentDescription = "Date Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = event.eventdate,

                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.width(16.dp)) // Space between date and time

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_access_time_24), // Replace with your clock icon
                        contentDescription = "Time Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = event.eventtime,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

fun getBookedEvents(userEmail: String, callback: (List<Event>) -> Unit) {
    // Convert email to valid Firebase key format
    val emailKey = userEmail.replace(".", ",")

    // Reference to the Donations branch
    val databaseReference = FirebaseDatabase.getInstance().getReference("MyEventBookings/$emailKey")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val eventsList = mutableListOf<Event>()
            for (donationSnapshot in snapshot.children) {
                val donation = donationSnapshot.getValue(Event::class.java)
                donation?.let { eventsList.add(it) }
            }
            callback(eventsList)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Error: ${error.message}")
            callback(emptyList()) // Return an empty list in case of error
        }
    })
}