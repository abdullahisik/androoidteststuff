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

        private const val MAX_CLICK_DURATION = 700

        private var mContext: Context? = null

        private var mClicksCnt = 0


    }

    override fun onReceive(context: Context?, intent: Intent) {


    }
}


