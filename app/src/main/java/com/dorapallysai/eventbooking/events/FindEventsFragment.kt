package com.dorapallysai.eventbooking.events

import android.os.Bundle
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.dorapallysai.eventbooking.R
import com.dorapallysai.eventbooking.ui.theme.EventBookingTheme


class FindEventsFragment : Fragment(R.layout.fragment_find_events) {
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.composeFindEvents).setContent {
            EventBookingTheme {
                SelectionEvent(::navigateToBookSlotFragment, ::onProfileClicked)
            }
        }
    }

    private fun onProfileClicked() {
    }

    private fun navigateToBookSlotFragment(event: Event) {

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
        }

        Spacer(modifier = Modifier.height(6.dp))

        CategoriesDropDown(
            categories = listOf(
                "All",
                "Music",
                "Art",
                "Technology",
                "Food",
                "Wellness",
                "Entertainment"
            ),
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

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
            eventname = "Music Concert",
            eventdescription = "A night full of live music and fun.",
            eventdate = "05-12-2024",
            eventtime = "5:00 PM",
            eventlocation = "Downtown Arena",
            eventimage = R.drawable.music_concert,
            category = "Music"
        ),
        Event(
            eventname = "Art Exhibition",
            eventdescription = "Exhibition featuring contemporary artists.",
            eventdate = "10-12-2024",
            eventtime = "10:00 PM",
            eventlocation = "City Art Gallery",
            eventimage = R.drawable.art_expo,
            category = "Art"
        ),
        Event(
            eventname = "Tech Conference",
            eventdescription = "Annual tech conference with keynotes and workshops.",
            eventdate = "15-12-2024",
            eventtime = "09:00 PM",
            eventlocation = "Convention Center",
            eventimage = R.drawable.tech_conf,
            category = "Technology"
        ),
        Event(
            eventname = "Food Festival",
            eventdescription = "A weekend of food trucks and local delicacies.",
            eventdate = "22-12-2024",
            eventtime = "12:00 PM",
            eventlocation = "Central Park",
            eventimage = R.drawable.food_festival,
            category = "Food"
        ),
        Event(
            eventname = "Charity Marathon",
            eventdescription = "A marathon to raise funds for local charities.",
            eventdate = "08-01-2025",
            eventtime = "08:00 AM",
            eventlocation = "City Stadium",
            eventimage = R.drawable.charity_marathon,
            category = "Wellness"
        ),
        Event(
            eventname = "Book Fair",
            eventdescription = "Annual fair featuring various book publishers.",
            eventdate = "10-01-2025",
            eventtime = "11:00 AM",
            eventlocation = "Convention Hall",
            eventimage = R.drawable.book_fair,
            category = "Entertainment"
        ),
        Event(
            eventname = "Film Festival",
            eventdescription = "A celebration of independent films from around the world.",
            eventdate = "05-01-2025",
            eventtime = "2:00 PM",
            eventlocation = "Downtown Cinema",
            eventimage = R.drawable.film_festival,
            category = "Entertainment"
        ),
        Event(
            eventname = "Science Expo",
            eventdescription = "Science exhibitions and workshops for enthusiasts.",
            eventdate = "15-01-2025",
            eventtime = "09:30 AM",
            eventlocation = "Expo Center",
            eventimage = R.drawable.science_expo,
            category = "Technology"
        ),
        Event(
            eventname = "Cooking Workshop",
            eventdescription = "Learn to cook from renowned chefs.",
            eventdate = "12-02-2025",
            eventtime = "04:00 PM",
            eventlocation = "Community Kitchen",
            eventimage = R.drawable.cooking_workshop,
            category = "Food"
        ),
        Event(
            eventname = "Yoga Retreat",
            eventdescription = "A day of relaxation and yoga.",
            eventdate = "20-02-2025",
            eventtime = "07:00 PM",
            eventlocation = "Beachside Retreat",
            eventimage = R.drawable.yoga_retreat,
            category = "Wellness"
        ),
        Event(
            eventname = "Startup Pitch Day",
            eventdescription = "Pitch ideas to investors.",
            eventdate = "18-01-2025",
            eventtime = "01:00 PM",
            eventlocation = "Innovation Hub",
            eventimage = R.drawable.startup_pitch,
            category = "Technology"
        ),
        Event(
            eventname = "Dance Workshop",
            eventdescription = "Learn salsa from experienced instructors.",
            eventdate = "15-02-2025",
            eventtime = "06:00 PM",
            eventlocation = "Dance Studio",
            eventimage = R.drawable.dance_workshop,
            category = "Music"
        ),
        Event(
            eventname = "Health & Wellness Fair",
            eventdescription = "A day focused on health and well-being.",
            eventdate = "10-02-2025",
            eventtime = "10:00 AM",
            eventlocation = "City Park",
            eventimage = R.drawable.health_wellness,
            category = "Wellness"
        ),
        Event(
            eventname = "Wine Tasting Event",
            eventdescription = "Sample wines from around the world.",
            eventdate = "01-02-2025",
            eventtime = "03:00 PM",
            eventlocation = "Winery",
            eventimage = R.drawable.wine_tasting,
            category = "Food"
        ),
        Event(
            eventname = "Christmas Market",
            eventdescription = "Holiday market with festive stalls.",
            eventdate = "20-01-2025",
            eventtime = "05:00 PM",
            eventlocation = "Town Square",
            eventimage = R.drawable.christmas_market,
            category = "Entertainment"
        ),
        Event(
            eventname = "New Year's Eve Party",
            eventdescription = "Celebrate the New Year with music and dance.",
            eventdate = "31-01-2025",
            eventtime = "10:00 PM",
            eventlocation = "Rooftop Lounge",
            eventimage = R.drawable.newyear_poster,
            category = "Music"
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
                    fontSize = 24.sp,
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









