package com.example.test.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class BroadcastAudioBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        print("play song aga")

            Toast.makeText(context,"STUFF",Toast.LENGTH_LONG).show()


    }
}