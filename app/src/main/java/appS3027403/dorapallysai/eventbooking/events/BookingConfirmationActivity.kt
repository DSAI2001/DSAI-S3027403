package appS3027403.dorapallysai.eventbooking.events

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import appS3027403.dorapallysai.eventbooking.R

class BookingConfirmationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookingConfirmationScreen()
        }
    }
}

@Composable
fun BookingConfirmationScreen() {
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
                text = "Booking Confirmation",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }

        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "Event Name",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )

            Text(
                text = "${Selectevent.bookingEvent.eventname}",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

            Text(
                text = "Location",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )

            Text(
                text = "${Selectevent.bookingEvent.eventlocation}",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

            Text(
                text = "Event Date",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )

            Text(
                text = "${Selectevent.bookingEvent.eventdate}",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

            Text(
                text = "Event Time",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )

            Text(
                text = "${Selectevent.bookingEvent.eventtime}",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

            Text(
                text = "Attendee Name",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )

            Text(
                text = "${Selectevent.bookingEvent.guestName}",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

            Button(
                onClick = {
                    bookEvent(Selectevent.bookingEvent, currentActivity)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)

            ) {
                Text("Book Now")

            }


        }
    }
}