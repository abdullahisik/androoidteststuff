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
//
//                        ref?.release()
//                        ref = null
                    }
//                    ref?.setOnCompletionListener {
//                        MediaPlayer.OnCompletionListener {
//                            it.reset()
//                            it.stop()
//                            it.release()
//                        }
//                    }
                }
            }
        }
    }
}

//
//        var myArray = arrayOf<Int>(
//            com.example.test.R.raw.song_1,
//            com.example.test.R.raw.song_2,
//            com.example.test.R.raw.song_3
//        )
//
//        var mp: MediaPlayer? = null
//
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
//                mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
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
//                mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
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
//                mp = MediaPlayer.create(context, myArray[index])
//                mp.start()
//            }
//            mp?.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
//                if (!mp.isPlaying) {
//                    mp.release()
//                } else {
//                    mp.reset()
//                    mp.stop()
//                    mp.release()
//                }
//            })
//        }
//
//
//
//        if (intent.action == "ACTION_DUCK") {
//            Toast.makeText(context, "DUCK", Toast.LENGTH_LONG).show()
//            if (mp != null && mp.isPlaying()) {
//                mp.reset()
//                mp.stop()
//                mp.release()
//            } else {
//
//
//
//            }
//            mp = MediaPlayer.create(context, R.raw.duck_mania)
//            mp.start()
//            mp?.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
//                if (!mp.isPlaying) {
//                    mp.release()
//                } else {
//                    mp.reset()
//                    mp.stop()
//                    mp.release()
//
//                }
//            })
//
//        }
//    }

    fun clickPlayer() {






    }
    fun nextMusic() {




    }

    fun previousMusic() {


    }



