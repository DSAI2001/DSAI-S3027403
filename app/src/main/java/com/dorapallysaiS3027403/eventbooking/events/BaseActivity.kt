package com.dorapallysaiS3027403.eventbooking.events

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dorapallysaiS3027403.eventbooking.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        supportFragmentManager.beginTransaction()
            .add(R.id.main, FindEventsFragment())
            .addToBackStack(null).commit()

    }
}