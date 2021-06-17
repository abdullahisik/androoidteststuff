package com.example.test.receiver

import android.app.Service.START_STICKY
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.test.R
import com.example.test.service.ForegroundService
import com.example.test.service.MusicService


class AudioReceiver : BroadcastReceiver() {

    companion object {


    }

    var index: Int = 0
    override fun onReceive(context: Context?, intent: Intent) {


        val actionName = intent?.getStringExtra("myAction")

        val intent2 = Intent(context, MusicService::class.java)
        val serviceIntent = Intent(context, MusicService::class.java)
        if (intent?.action != null) {
            when (intent.action) {
                ForegroundService.ACTION_PREVIOUS -> {
                    intent2.putExtra("myAction", intent.action)
                    context?.startService(intent2)
                }
                ForegroundService.ACTION_NEXT -> {
                    intent2.putExtra("myAction", intent.action)
                    context?.startService(intent2)
                }
                ForegroundService.ACTION_PLAY -> {
                    if(ForegroundService.boolState) {
                        context?.stopService(serviceIntent)
                        intent2.putExtra("myAction", intent.action)
                        context?.startService(intent2)
                        ForegroundService.boolState = false
                    } else {
                        context?.stopService(serviceIntent)
                        intent2.putExtra("myAction", intent.action)
                        context?.startService(intent2)
                        ForegroundService.boolState = true
                    }
                }
            }
        }
//        var myArray = arrayOf<Int>(
//            com.example.test.R.raw.song_1,
//            com.example.test.R.raw.song_2,
//            com.example.test.R.raw.song_3
//        )
//
//        var mp: MediaPlayer? = null
//        if (intent.action == "ACTION_PREVIOUS") {
//            Toast.makeText(context, "previous", Toast.LENGTH_LONG).show()
//            if (index != 0) {
//                if (mp != null && mp.isPlaying()) {
//                    mp.reset()
//                    mp.stop()
//                    mp.release()
//
//                } else {
//
//                }
//                mp = MediaPlayer.create(context, myArray[index])
//                mp.start()
//                mp.setOnCompletionListener(OnCompletionListener { mp ->
//                    if (!mp.isPlaying) {
//                        mp.release()
//                    } else {
//                        mp.reset()
//                        mp.stop()
//                        mp.release()
//                    }
//                })
//            }
//        }
//        if (intent.action == "ACTION_NEXT") {
//            Toast.makeText(context, "next", Toast.LENGTH_LONG).show()
//            index += 1
//            if (index != 0 && myArray.size >= index) {
//                if (mp != null && mp.isPlaying()) {
//                    mp.stop()
//                    mp.reset()
//                    mp.release()
//                } else {
//                }
//
//                mp = MediaPlayer.create(context, myArray[index])
//                mp.start()
//                mp.setOnCompletionListener(OnCompletionListener { mp ->
//                    if (!mp.isPlaying) {
//                        mp.release()
//                    } else {
//                        mp.reset()
//                        mp.stop()
//                        mp.release()
//                    }
//                })
//            }
//        }
//
//        if (intent.action == "ACTION_PLAY") {
//            if (mp != null && mp.isPlaying()) {
//                mp.stop()
//                mp.release()
//
//            } else {
//
//            }
//            mp = MediaPlayer.create(context, myArray[index])
//            mp.start()
//        }
//        mp?.setOnCompletionListener(OnCompletionListener { mp ->
//            if (!mp.isPlaying) {
//                mp.release()
//            } else {
//                mp.reset()
//                mp.stop()
//                mp.release()
//            }
//        })
//
//
//        if (intent.action == "ACTION_DUCK") {
//            Toast.makeText(context, "DUCK", Toast.LENGTH_LONG).show()
//            if (mp != null && mp.isPlaying()) {
//                mp.stop()
//                mp.release()
//
//            } else {
//
//            }
//
//            mp = MediaPlayer.create(context, R.raw.duck_mania)
//            mp.start()
//
//            mp?.setOnCompletionListener(OnCompletionListener { mp ->
//                if (!mp.isPlaying) {
//                    mp.release()
//                } else {
//                    mp.reset()
//                    mp.stop()
//                    mp.release()
//                }
//            })
//
//        }
    }
}


