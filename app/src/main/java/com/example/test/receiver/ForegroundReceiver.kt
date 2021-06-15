package com.example.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.test.foreground.ForegroundService


class ForegroundReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        print("triggered")
        if (Intent.ACTION_BOOT_COMPLETED == intent!!.action) {
            val serviceIntent = Intent(context, ForegroundService::class.java)
            serviceIntent.putExtra("inputExtra", "test")
            context?.let { ContextCompat.startForegroundService(it, serviceIntent)

            }
        }
    }
}