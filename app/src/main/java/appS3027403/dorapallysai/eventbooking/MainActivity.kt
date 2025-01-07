package appS3027403.dorapallysai.eventbooking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import appS3027403.dorapallysai.eventbooking.events.BaseActivity
import appS3027403.dorapallysai.eventbooking.ui.theme.EventBookingTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventBookingTheme {
                WelComeScreen()
            }
        }
    }

}

@Composable
fun WelComeScreen() {
    var displaySplash by remember { mutableStateOf(true) }

    val currentActivity = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        delay(3000)
        displaySplash = false


    }
    if (displaySplash) {
        WelComeScreenDesign()

    } else {
        val guestActivity = UserDetails.getUserLoginStatus(currentActivity)

        if (guestActivity) {
            currentActivity.startActivity(Intent(currentActivity, BaseActivity::class.java))
            currentActivity.finish()
        } else {
            currentActivity.startActivity(Intent(currentActivity, LoginActivity::class.java))
            currentActivity.finish()
        }
    }

}


@Composable
fun WelComeScreenDesign() {
    val currentActivity = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.PrimaryDark)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dorapally Sai",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Event Booking",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_booking),
                contentDescription = "Dorapally Sai Event Booking",
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun WelComeScreenDesignPreview() {
    WelComeScreenDesign()
}