package com.example.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import java.util.logging.Handler


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
        }
    }
}


