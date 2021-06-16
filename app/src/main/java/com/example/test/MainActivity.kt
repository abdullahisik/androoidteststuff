package com.example.test


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.test.service.ForegroundService


class MainActivity : AppCompatActivity() {
companion object {


    var boolIconState = false
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonStartService: Button? = findViewById<Button>(R.id.button_start_service)
        val buttonCloseService: Button? = findViewById<Button>(R.id.button_close_service)
        buttonStartService?.setOnClickListener() {
            boolIconState = true
            startservıce()

        }
        buttonCloseService?.setOnClickListener() {
            stopService()
            boolIconState = false
        }
        val layoutId = resources.getIdentifier(
            "layout_simulated_notification", "layout",
            packageName
        )

        this@MainActivity.runOnUiThread(java.lang.Runnable {


        })

    }

    public fun startservıce() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "test")
        ContextCompat.startForegroundService(this, serviceIntent)
    }


    public fun stopService() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }
}