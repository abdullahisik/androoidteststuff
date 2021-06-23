package com.example.test.service

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat


class MusicService : Service(){
    //creating a mediaplayer object
    private var player: MediaPlayer? = null

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    var songArray = arrayOf<Int>(
          com.example.test.R.raw.song_1,
          com.example.test.R.raw.song_2,
          com.example.test.R.raw.song_3,
          com.example.test.R.raw.duck_mania
    )
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val intent2 = Intent(baseContext, ForegroundService::class.java)
        if(ForegroundService.indexSong > 3) ForegroundService.indexSong = 0
        else if(ForegroundService.indexSong < 0) ForegroundService.indexSong = 3
        player = MediaPlayer.create(this, songArray[ForegroundService.indexSong])
        player!!.isLooping = false
        player!!.start()
        player!!.setOnCompletionListener(OnCompletionListener { mp ->
            try {
                intent2.putExtra("myAction", intent?.action)
                baseContext?.startService(intent2)
                baseContext?.let {
                    ContextCompat.startForegroundService(it, intent2)
                }
//                        ref = MediaPlayer.create(context, R.raw.duck_mania)
//                        ref.start()
                ForegroundService.boolStatesMediaPlayer = false
                ForegroundService.boolState = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player!!.pause()
        player = null

    }
}