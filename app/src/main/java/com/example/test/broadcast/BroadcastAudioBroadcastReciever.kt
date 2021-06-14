package com.example.test.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BroadcastAudioBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        if (action.equals("com.example.app.ACTION_PLAY", ignoreCase = true)) {
print("play song aga")

        }
    }
}