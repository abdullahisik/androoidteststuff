package com.example.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AudioReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            print("çalıştı")

    }
}