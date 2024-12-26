package com.dorapallysaiS3027403.eventbooking.events

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorapallysaiS3027403.eventbooking.R
import com.dorapallysaiS3027403.eventbooking.UserDetails
import com.dorapallysaiS3027403.eventbooking.events.Selectevent.event
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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
    val context = LocalContext.current as Activity
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
                        context.finish()
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
                    contentDescription = "Location",
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


            /*
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Tickets",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically

                )

                {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "-")

                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "10",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.width(12.dp))


                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "+")


                    }

                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Book Now")

                }
            }
            Spacer(modifier = Modifier.weight(1f))
            */

        }
    }
}

@Composable
fun BookNowCard(event: Event) {
    val context = LocalContext.current

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
                    if(name.isEmpty() || company.isBlank() || email.isBlank() || jobTitle.isBlank())
                    {
                        Toast.makeText(context,"Fields Missing",Toast.LENGTH_SHORT).show()
                    }else{
                        event.guestName = name
                        event.companyName = company
                        event.guestEmail = email
                        event.jobTitle = jobTitle

                        bookEvent(event,context)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)

            ) {
                Text("Book Now")

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

private fun bookEvent(event: Event, context: Context) {

    val db = FirebaseDatabase.getInstance()
    val ref = db.getReference("MyEventBookings")

    val currentDate = LocalDate.now() // Get today's date
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Define the format
    event.dateOfBooking=currentDate.format(formatter) // Format the date


    val orderId = event.eventId.toString()

    val userEmail = UserDetails.getEmail(context)!!

    ref.child(userEmail.replace(".", ",")).child(orderId).setValue(event)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {

                (context as Activity).finish()
                Toast.makeText(context, "Booked Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "Booking Failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(
                context,
                "Booking Failed: ${exception.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
}
