package com.dorapallysaiS3027403.eventbooking.events

import android.app.Activity
import android.content.Intent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorapallysaiS3027403.eventbooking.R
import com.dorapallysaiS3027403.eventbooking.UserDetails

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()

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
                        (context as Activity).finish()
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

        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Name : ",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp)

            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = UserDetails.getName(context)!!,
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp)

            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Email : ",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp)

            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = UserDetails.getEmail(context)!!,
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp)

            )

        }

        Button(
            onClick = {
                context.startActivity(Intent(context, MyEventsActivity::class.java))

            },
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "My Event",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Button(
            onClick = {

            },
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "Contact Event Support",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProfileScreen()


}