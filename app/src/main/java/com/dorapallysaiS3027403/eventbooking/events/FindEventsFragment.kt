package com.dorapallysaiS3027403.eventbooking.events

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.dorapallysaiS3027403.eventbooking.LoginActivity
import com.dorapallysaiS3027403.eventbooking.R
import com.dorapallysaiS3027403.eventbooking.UserDetails
import com.dorapallysaiS3027403.eventbooking.ui.theme.EventBookingTheme


class FindEventsFragment : Fragment(R.layout.fragment_find_events) {
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.composeFindEvents).setContent {
            EventBookingTheme {
                SelectionEvent(::gotoEventDetails, ::onProfileClicked)
            }
        }
    }

    private fun onProfileClicked() {

    }

    private fun gotoEventDetails(event: Event) {
        Selectevent.event = event
        startActivity(Intent(requireContext(), EventDetailsActivity::class.java))
    }

}

@Preview(showBackground = true)
@Composable
fun FindEventsFragmentPreview() {
    SelectionEvent(onEventSelected = {}, onProfileClicked = {})
}


@Composable
fun SelectionEvent(onEventSelected: (event: Event) -> Unit, onProfileClicked: () -> Unit) {

    val events = getEvents()
    val context = LocalContext.current as Activity

    var selectedCategory by remember { mutableStateOf("All") }

    val filteredEvents = if (selectedCategory == "All") {
        events
    } else {
        events.filter { it.category == selectedCategory }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_account_circle_36),
                contentDescription = "Profile",
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Events List",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = "Logout",
                modifier = Modifier


                    .clickable {
                        // Navigate to LoginActivity when clicked
                        UserDetails.saveUserLoginStatus(context, false)

                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                         context.finish()
                    }
                    .padding(start = 8.dp) // Optional spacing // Optional spacing


            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        CategoriesDropDown(
            categories = listOf(
                "All",
                "Alumni",
                "Business & Enterprise",
                "Careers Events"
            ),
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        if (filteredEvents.isNotEmpty()) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {

                items(filteredEvents.size) { index ->
                    EventDetails(filteredEvents[index], onEventSelected)
                }
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "We currently do not have any events planned, please check again in the near future for updates.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesDropDown(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) } // State to control dropdown visibility

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        TextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            label = { Text("Filter by Categories") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()

        ) {
            categories.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onCategorySelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}


fun getEvents(): List<Event> {
    return listOf(
        Event(
            eventname = "Online learning - engineering webinar",
            eventdescription = "Further your education and unlock new job prospects with a 100% online engineering qualification including HNCs, HNDs and top-ups.\n" +
                    "\n" +
                    "Join us to discover more about the entry criteria, course content, funding, course start dates and get all your questions answered.",
            eventdate = "04-12-2024",
            eventtime = "4.00pm - 5.00pm",
            eventlocation = "Online",
            eventimage = R.drawable.event,
            category = "Alumni"
        ),
        Event(
            eventname = "Business breakfast - accessing student talent",
            eventdescription = "This event is not for students.\n" +
                    "\n" +
                    "Find out more about how you can benefit from growing student talent at Teesside University. From live projects and internships to work placements and graduate recruitment, we can support you to find the ideal talent to support your business needs.\n" +
                    "\n" +
                    "Hear from Thirteen Group as they highlight the benefits of working with us to recruit interns and hear from the interns as they share their experiences of these opportunities.\n" +
                    "\n" +
                    "We’ll also be joined by Unitemps and Voluntees, who’ll share details of the services they can provide and how they can connect you with motivated and talented students.",
            eventdate = "15-01-2025",
            eventtime = "7.30am - 9.30am",
            eventlocation = "On campus",
            eventimage = R.drawable.event,
            category = "Alumni"
        ),
        Event(
            eventname = "Digital Accounting & Finance Manager Degree Apprenticeship Webinar",
            eventdescription = "Designed to blend digital expertise with practical application for real business impact, join our webinar and discover how our Digital Accounting and Finance Manager Degree Apprenticeship can equip you or your firm with the digital expertise and leadership skills to thrive in a fast-changing world.",
            eventdate = "06-12-2024",
            eventtime = "10.00am - 11.00am",
            eventlocation = "Online",
            eventimage = R.drawable.event,
            category = "Business & Enterprise"
        ),
        Event(
            eventname = "Business breakfast - accessing student talent",
            eventdescription = "This event is not for students.\n" +
                    "\n" +
                    "Find out more about how you can benefit from growing student talent at Teesside University. From live projects and internships to work placements and graduate recruitment, we can support you to find the ideal talent to support your business needs.\n" +
                    "\n" +
                    "Hear from Thirteen Group as they highlight the benefits of working with us to recruit interns and hear from the interns as they share their experiences of these opportunities.\n" +
                    "\n" +
                    "We’ll also be joined by Unitemps and Voluntees, who’ll share details of the services they can provide and how they can connect you with motivated and talented students.",
            eventdate = "15-01-2025",
            eventtime = "7.30am - 9.30am",
            eventlocation = "On campus",
            eventimage = R.drawable.event,
            category = "Business & Enterprise"
        ),
        Event(
            eventname = "Business breakfast - work skills development",
            eventdescription = "This event is not for students.\n" +
                    "\n" +
                    "Find out more about how you can work with us to develop and retain top talent and empower your workforce through apprenticeships and continuing professional development opportunities.",
            eventdate = "12-02-2025",
            eventtime = "7.30am - 10.00am",
            eventlocation = "On campus",
            eventimage = R.drawable.event,
            category = "Business & Enterprise"
        ),
        Event(
            eventname = "Business breakfast - research and innovation",
            eventdescription = "This event is not for students.\n" +
                    "\n" +
                    "Finding the right specialists to help you innovate can be a challenge.\n" +
                    "\n" +
                    "Join us and discover how we can support your business through knowledge transfer partnerships, consultancy and collaborative research.",
            eventdate = "13-06-2025",
            eventtime = "7.30am - 10.00am",
            eventlocation = "On campus",
            eventimage = R.drawable.event,
            category = "Business & Enterprise"
        )
    )
}


@Composable
fun EventDetails(
    event: Event,
    onEventSelected: (event: Event) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onEventSelected.invoke(event) },
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        )

        {
            Image(
                painter = painterResource(id = event.eventimage),
                contentDescription = "Event Poster",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(12.dp, 0.dp)
            ) {

                Text(
                    text = event.eventname,
                    color = Color.Blue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.height(4.dp))

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
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )


                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                        contentDescription = "Event Date",
                    )
                    Spacer(modifier = Modifier.width(4.dp))


                    Text(
                        text = event.eventdate,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))


                    Image(
                        painter = painterResource(id = R.drawable.baseline_access_time_24),
                        contentDescription = "Time",
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = event.eventtime,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                /*
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                )
                {
                        Button(
                            onClick = {
                                val intent = Intent(context, EventDetailsActivity::class.java)
                                context.startActivity(intent)
                            },
                            modifier = Modifier,
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(text = "View")


                        }



                }
                Spacer(modifier = Modifier.height(6.dp))
                */

            }
        }
    }
}









