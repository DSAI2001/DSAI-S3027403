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
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, ProfileActivity::class.java))
                },
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
        ),
        Event(
            eventname = "Campus visit",
            eventdescription = "Take a guided tour of the campus including the library, Students' Union and teaching spaces. Speak to an expert from our student recruitment and / or accommodation team and hear about life at Teesside University from a current student. And we can arrange a one-to-one with an academic about the course you're interested in.\n" +
                    "\n" +
                    "Programme\n" +
                    "\n" +
                    "12.45pm - 1.00pm Arrival and registration\n" +
                    "1.00pm – 1.30pm Welcome presentation\n" +
                    "1.30pm – 2.15pm Campus tour\n" +
                    "2.15pm – 2.45pm Chat to academic staff, accommodation or student recruitment (optional)\n" +
                    "2.45pm – 3.00pm Questions and close\n" +
                    "\n",
            eventdate = "11-12-2024",
            eventtime = "1.00pm - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Campus visits",
            eventdescription = "Take a guided tour of the campus including the library, Students' Union and teaching spaces. Speak to an expert from our student recruitment and / or accommodation team and hear about life at Teesside University from a current student. And we can arrange a one-to-one with an academic about the course you're interested in.\n" +
                    "\n" +
                    "Programme\n" +
                    "\n" +
                    "12.45pm - 1.00pm Arrival and registration\n" +
                    "1.00pm – 1.30pm Welcome presentation\n" +
                    "1.30pm – 2.15pm Campus tour\n" +
                    "2.15pm – 2.45pm Chat to academic staff, accommodation or student recruitment (optional)\n" +
                    "2.45pm – 3.00pm Questions and close\n" +
                    "\n",
            eventdate = "08-01-2025",
            eventtime = "1.00pm - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Undergraduate open day",
            eventdescription = "If you're considering studying at Teesside University, come and visit us – it’s the best way to find out if it’s the right university for you.\n" +
                    "\n" +
                    "At the open day you can find out about the course(s) you’re interested in by chatting to our tutors and students and attending subject-specific talks and facility tours. It’s a great opportunity to get the information you need.\n" +
                    "\n" +
                    "Student finance, the Students' Union and disability services will be on hand to offer advice and support all day. You’ll also have the option to tour our campus, view our accommodation and meet our staff and students.\n" +
                    "\n" +
                    "When you arrive our friendly team of helpers will make sure you find your way. Look out for us in our blue t-shirts and hoodies. Free parking is available.\n" +
                    "\n" +
                    "Family and friends are welcome to join you.\n" +
                    "\n" +
                    "See you soon!",
            eventdate = "18-01-2025",
            eventtime = "9.00am - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Postgraduate open day",
            eventdescription = "If you're considering studying at Teesside University, come and visit us – it’s the best way to find out if it’s the right university for you.\n" +
                    "\n" +
                    "At the open day you can find out about the course(s) you’re interested in by chatting to our tutors and students and attending subject-specific talks and facility tours. It’s a great opportunity to get the information you need.\n" +
                    "\n" +
                    "Student finance, the Students' Union and disability services will be on hand to offer advice and support all day. You’ll also have the option to tour our campus, view our accommodation and meet our staff and students.\n" +
                    "\n" +
                    "When you arrive our friendly team of helpers will make sure you find your way. Look out for us in our blue t-shirts and hoodies. Free parking is available.\n" +
                    "\n" +
                    "Family and friends are welcome to join you.\n" +
                    "\n" +
                    "See you soon!\n" +
                    "\n",
            eventdate = "18-01-2025",
            eventtime = "9.00am - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Doctorate in clinical psychology webinar",
            eventdescription = "Join us online for our Doctorate in clinical psychology webinar.\n" +
                    "\n" +
                    "\n" +
                    "Our webinars provide you with the opportunity to hear directly from key academic staff involved in the delivery of our courses. In addition to an overview of what our programme involves, you will find out about the modules delivered, how the course is assessed, and you can submit your questions for us to answer live.\n" +
                    "\n" +
                    "\n" +
                    "Click the booking link to register for the webinar.",
            eventdate = "23-01-2025",
            eventtime = "4.00pm - 5.15pm",
            eventlocation = "On campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Pencils and pixels - careers in creative and digital arts",
            eventdescription = "Do you want to understand the difference between creating game worlds or adding movement to objects in that world? Or how concept artists design the props and environments for a story while an illustrator brings the story to life?\n" +
                    "\n" +
                    "Through immersive experiences, you are introduced to our range of creative courses, get advice from those working in the industry and understand how to build your portfolio.\n" +
                    "\n",
            eventdate = "05-02-2025",
            eventtime = "9.30am - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Campus visits",
            eventdescription = "Take a guided tour of the campus including the library, Students' Union and teaching spaces. Speak to an expert from our student recruitment and / or accommodation team and hear about life at Teesside University from a current student. And we can arrange a one-to-one with an academic about the course you're interested in.\n" +
                    "\n" +
                    "Programme\n" +
                    "\n" +
                    "12.45pm - 1.00pm Arrival and registration\n" +
                    "1.00pm – 1.30pm Welcome presentation\n" +
                    "1.30pm – 2.15pm Campus tour\n" +
                    "2.15pm – 2.45pm Chat to academic staff, accommodation or student recruitment (optional)\n" +
                    "2.45pm – 3.00pm Questions and close\n" +
                    "\n",
            eventdate = "05-02-2025",
            eventtime = "1.00pm - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Campus visits",
            eventdescription = "Take a guided tour of the campus including the library, Students' Union and teaching spaces. Speak to an expert from our student recruitment and / or accommodation team and hear about life at Teesside University from a current student. And we can arrange a one-to-one with an academic about the course you're interested in.\n" +
                    "\n" +
                    "Programme\n" +
                    "\n" +
                    "12.45pm - 1.00pm Arrival and registration\n" +
                    "1.00pm – 1.30pm Welcome presentation\n" +
                    "1.30pm – 2.15pm Campus tour\n" +
                    "2.15pm – 2.45pm Chat to academic staff, accommodation or student recruitment (optional)\n" +
                    "2.45pm – 3.00pm Questions and close\n" +
                    "\n",
            eventdate = "19-02-2025",
            eventtime = "1.00pm - 3.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Discover science",
            eventdescription = "Our science discovery days give the opportunity to attend two subject sessions from the following:\n" +
                    "> biology\n" +
                    "> biomedical science\n" +
                    "> environmental science\n" +
                    "> food and nutrition\n" +
                    "> geography\n" +
                    "> pharmaceutical science.\n" +
                    "\n" +
                    "Sessions are curriculum linked and could include:\n" +
                    "> antibiotic resistance and the Kirby-Bauer test\n" +
                    "> environmental distribution\n" +
                    "> nuclear magnetic resonance\n" +
                    "> trace metal solutions.",
            eventdate = "05-03-2025",
            eventtime = "10.15am - 2.30pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "English round-table masterclass",
            eventdescription = "Students work directly with academic staff on themes and topics from the A level syllabus. The discussions focus on sources, debate, building skills and confidence and supporting student attainment.\n" +
                    "\n" +
                    "Topics available include:\n" +
                    "> Analysing poetry and prose\n" +
                    "> Developing your authorial voice\n" +
                    "> Exam revision - women in dystopian fiction\n" +
                    "> Exam revision - Dracula in literature.",
            eventdate = "05-03-2025",
            eventtime = "4.00pm - 6.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Life, death and forensic science conference",
            eventdescription = "What can we discover from a skeleton?How does forensic genetics help us solve wildlife crime? What does blood spatter tell us about a crime scene? Through practical sessions, understand how advances in technology are helping those working in the legal and justice system catch the criminals.",
            eventdate = "25-03-2025",
            eventtime = "9.30am - 3.30pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "History and politics round-table masterclass",
            eventdescription = "Students work directly with academic staff on themes and topics from the A level syllabus. The discussions focus on sources, debate, building skills and confidence and supporting student attainment.\n" +
                    "\n" +
                    "Topics available include:\n" +
                    "> British political parties\n" +
                    "> reformation England\n" +
                    "> the French Revolution\n" +
                    "> Russia in the 20th Century\n" +
                    "> the rise of the Nazi’s and Hitler’s Germany\n" +
                    "> the USA - 1972 to present day\n" +
                    "> women and gender in politics.\n" +
                    "\n",
            eventdate = "26-03-2025",
            eventtime = "4.00pm - 6.00pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Supporting progression conference",
            eventdescription = "For post-16 teachers and careers advisers\n" +
                    "\n" +
                    "We bring together a range of experts to provide you with the most up-to-date information and guidance for approaching\n" +
                    "today’s current issues in further and higher education.\n" +
                    "\n" +
                    "April 2024 saw us welcome keynote speakers from UCAS and IDP Connect. Hannah Simnett from Connection Counts delivered a fantastic session on forming connections with your students and Jonny Richardson from GECKO took us on a fascinating journey to understand the impact of AI and ChatGPT in education.\n" +
                    "\n" +
                    "Join us to understand how you can support students in your role.",
            eventdate = "02-04-2025",
            eventtime = "9.15am - 3.30pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Nursing and midwifery conference",
            eventdescription = "Celebrate international nurses day with us.\n" +
                    "\n" +
                    "What does a career in nursing or midwifery look like? Hear from key speakers from a range of clinical practices to gain an insight into their roles, and how they care for their patients. Sessions could include topics such as maternity delivery, cardiac arrest and dementia.",
            eventdate = "07-05-2025",
            eventtime = "9.30am - 3.30pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
        Event(
            eventname = "Why fairness matters - ensuring justice in law and policing",
            eventdescription = "Through practical and engaging sessions, explore topics linked to the curriculum. Debate and discuss many areas of the law and the legal system in the UK and understand the types of topics you might study at university including:\n" +
                    "\n" +
                    "> a fair cop in custody\n" +
                    "> equality of arms: the crucial role of cross-examination\n" +
                    "> fairness in fingerprints – mistakes and\n" +
                    "miscarriages\n" +
                    "> just and effective bail applications\n" +
                    "> unfair trials and unfit defendants.",
            eventdate = "18-06-2025",
            eventtime = "9.30am - 2.30pm",
            eventlocation = "On campus, Middlesbrough Campus",
            eventimage = R.drawable.event,
            category = "Visiting & Open Days"
        ),
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









