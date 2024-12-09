package com.dorapallysaiS3027403.eventbooking.events

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorapallysaiS3027403.eventbooking.R
import com.dorapallysaiS3027403.eventbooking.events.Selectevent.event

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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EventDetails()
}