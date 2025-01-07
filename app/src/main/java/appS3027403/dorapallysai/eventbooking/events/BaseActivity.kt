package appS3027403.dorapallysai.eventbooking.events

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import appS3027403.dorapallysai.eventbooking.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        supportFragmentManager.beginTransaction()
            .add(R.id.main, FindEventsFragment())
            .addToBackStack(null).commit()

    }
}