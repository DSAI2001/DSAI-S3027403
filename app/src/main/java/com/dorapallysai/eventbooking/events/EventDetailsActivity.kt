package com.dorapallysai.eventbooking.events

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorapallysai.eventbooking.R

class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventDetails(onBackClicked = {})
        }
    }
}

@Composable
fun EventDetails(onBackClicked: () -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),

        contentAlignment = Alignment.TopCenter

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        )

        {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            )
            {

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                        .clickable {
                            onBackClicked.invoke()
                        }
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Event  Details",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.weight(1f))

            }

            Image(
                painter = painterResource(id = R.drawable.event_award),
                contentDescription = "EventImage",

                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.padding(12.dp, 0.dp)
            ) {

                Text(
                    text = "Event Name",
                    color = Color.Blue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_description_24),
                        contentDescription = "Description"
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "Description",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
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
                        text = "New York",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,


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
                        text = "21/10/2024",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,


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
                        contentDescription = "Time",
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "8:00 PM",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                }



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

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EventDetails(onBackClicked = {})
}