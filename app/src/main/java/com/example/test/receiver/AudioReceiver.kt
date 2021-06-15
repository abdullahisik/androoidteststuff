package com.example.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class AudioReceiver : BroadcastReceiver()  {

    override fun onReceive(context: Context?, intent: Intent) {
        val extra = intent.extras
        if(extra != null) {

            Toast.makeText(context,extra.toString(),Toast.LENGTH_LONG).show()



        }
    }

}
