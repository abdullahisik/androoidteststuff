package com.example.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.test.service.MusicService


class AudioReceiver : BroadcastReceiver() {

    companion object {

    }

    override fun onReceive(context: Context?, intent: Intent) {
        val extras = intent.extras
        if (intent.action == "ACTION_PREVIOUS") {
            Toast.makeText(context, "previous", Toast.LENGTH_LONG).show()
        }
        if (intent.action == "ACTION_NEXT") {
            Toast.makeText(context, "next", Toast.LENGTH_LONG).show()
        }
        if (intent.action == "ACTION_PLAY") {
            Toast.makeText(context, "play", Toast.LENGTH_LONG).show()
            val startIntent = Intent(context,MusicService::class.java)
            context?.startService(startIntent)
        }
    }
}


