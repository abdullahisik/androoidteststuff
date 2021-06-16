package com.example.test.receiver

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
import com.example.test.R
import com.example.test.service.ForegroundService


class AudioReceiver : BroadcastReceiver() {


    var index: Int = 0
    override fun onReceive(context: Context?, intent: Intent) {
        var myArray = arrayOf<Int>(
            com.example.test.R.raw.song_1,
            com.example.test.R.raw.song_2,
            com.example.test.R.raw.song_3
        )

        var mp: MediaPlayer? = null
        if (intent.action == "ACTION_PREVIOUS") {
            Toast.makeText(context, "previous", Toast.LENGTH_LONG).show()
            if (index != 0) {
                if (mp != null && mp.isPlaying()) {
                    mp.reset()
                    mp.stop()
                    mp.release()

                } else {

                }
                mp = MediaPlayer.create(context, myArray[index])
                mp.start()
                mp.setOnCompletionListener(OnCompletionListener { mp ->
                    if (!mp.isPlaying) {
                        mp.release()
                    } else {
                        mp.reset()
                        mp.stop()
                        mp.release()
                    }
                })
            }
        }
        if (intent.action == "ACTION_NEXT") {
            Toast.makeText(context, "next", Toast.LENGTH_LONG).show()
            index += 1
            if (index != 0 && myArray.size >= index) {
                if (mp != null && mp.isPlaying()) {
                    mp.stop()
                    mp.reset()
                    mp.release()
                } else {
                }

                        mp = MediaPlayer.create(context, myArray[index])
                        mp.start()
                        mp.setOnCompletionListener(OnCompletionListener { mp ->
                            if (!mp.isPlaying) {
                                mp.release()
                            } else {
                                mp.reset()
                                mp.stop()
                                mp.release()
                            }
                     })
            }
        }

        if (intent.action == "ACTION_PLAY") {
val notifylayout =  ForegroundService.SampleSingleton.someMethod()
            val bitmap = Bitmap.createBitmap(5, 5, Bitmap.Config.ARGB_8888)
            //set the background color of the bitmap to RED
            //set the background color of the bitmap to RED
            bitmap.eraseColor(Color.RED)
notifylayout.setImageViewBitmap(R.drawable.ic_launcher_foreground,bitmap)
            if (mp != null && mp.isPlaying()) {
                mp.stop()
                mp.release()

            } else {

            }

                    mp = MediaPlayer.create(context, myArray[index])
                    mp.start()
                }
                mp?.setOnCompletionListener(OnCompletionListener { mp ->
                    if (!mp.isPlaying) {
                        mp.release()
                    } else {
                        mp.reset()
                        mp.stop()
                        mp.release()
                    }
                })


        if(intent.action == "ACTION_DUCK"){
            Toast.makeText(context, "DUCK", Toast.LENGTH_LONG).show()
            if (mp != null && mp.isPlaying()) {
                mp.stop()
                mp.release()

            } else {

            }

                    mp = MediaPlayer.create(context,R.raw.duck_mania)
                    mp.start()

                mp?.setOnCompletionListener(OnCompletionListener { mp ->
                    if (!mp.isPlaying) {
                        mp.release()
                    } else {
                        mp.reset()
                        mp.stop()
                        mp.release()
                    }
                })

        }
    }
}


