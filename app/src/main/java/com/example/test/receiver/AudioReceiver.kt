package com.example.test.receiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.core.content.ContextCompat
import com.example.test.service.ForegroundService
import com.example.test.service.MusicService
import com.example.test.service.singleTonExample


@Suppress("DEPRECATION")
class AudioReceiver : BroadcastReceiver() {

    companion object {


    }

    var index: Int = 0
    override fun onReceive(context: Context?, intent: Intent) {
        val actionName = intent?.getStringExtra("myAction")
        val intent2 = Intent(context, ForegroundService::class.java)
        val serviceIntent = Intent(context, ForegroundService::class.java)

        var singletonexample: singleTonExample? = null
        singletonexample = singleTonExample.getInstance()
        singletonexample.init(context)
        var ref: MediaPlayer? = singleTonExample.getSingletonMedia()
        if (intent?.action != null) {
            when (intent.action) {
                ForegroundService.ACTION_PREVIOUS -> {
                    intent2.putExtra("myAction", intent.action)
                    context?.let {
                        ContextCompat.startForegroundService(it, intent2)
                    }
                }
                ForegroundService.ACTION_NEXT -> {
                    intent2.putExtra("myAction", intent.action)
                    context?.let {
                        ContextCompat.startForegroundService(it, intent2)
                    }
                }
                ForegroundService.ACTION_PLAY -> {
                    if (ForegroundService.boolStatesMediaPlayer) {
                        intent2.putExtra("myAction", intent.action)
                        context?.startService(intent2)
                        context?.let {
                            ContextCompat.startForegroundService(it, intent2)
                        }
//                        ref = MediaPlayer.create(context, R.raw.duck_mania)
//                        ref.start()
                        ForegroundService.boolStatesMediaPlayer = false
                        ForegroundService.boolState = false
                    }
                    else{
                        val serviceIntent = Intent(context, ForegroundService::class.java)
                        context?.stopService(serviceIntent)
                        intent2.putExtra("myAction", intent.action)
                        ForegroundService.boolState = true
                        context?.let {
                            ContextCompat.startForegroundService(it, intent2)
                        }
                        ForegroundService.boolStatesMediaPlayer = true
                    }

                }
            }
        }
    }
}





