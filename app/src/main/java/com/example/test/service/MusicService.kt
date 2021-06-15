package com.example.test.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import com.example.test.R


class MusicService : Service() {


    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false
    override fun onCreate() {
        super.onCreate()

            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
            }else{
                mediaPlayer = MediaPlayer.create(applicationContext,R.raw.song_1)
                mediaPlayer.start()
                mediaPlayer.isPlaying
            }
         mediaPlayer.setOnCompletionListener {

           }
        }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {

        return null
    }

    override fun onDestroy() {

    }
}