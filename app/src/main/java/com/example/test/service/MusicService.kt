package com.example.test.service

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.test.MainActivity
import com.example.test.R
import java.io.FileDescriptor
import java.io.IOException


class MusicService : Service(),MediaPlayer.OnCompletionListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener,
    MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener,

    AudioManager.OnAudioFocusChangeListener {


    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = true
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()

            if(pause){
            initMediaPlayer()
MainActivity.boolIconState = false
            }else{

            }
         mediaPlayer.setOnCompletionListener {
          Log.d("TEST","test")
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

    override fun onCompletion(mp: MediaPlayer?) {

    }

    override fun onPrepared(mp: MediaPlayer?) {

    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false

    }

    override fun onSeekComplete(mp: MediaPlayer?) {
    }

    override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false
    }

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
    }

    override fun onAudioFocusChange(focusChange: Int) {


    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        //Set up MediaPlayer event listeners
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.setOnErrorListener(this)
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnBufferingUpdateListener(this)
        mediaPlayer.setOnSeekCompleteListener(this)
        mediaPlayer.setOnInfoListener(this)
        //Reset so that the MediaPlayer is not pointing to another data source
        mediaPlayer.reset()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer.setDataSource(resources.openRawResourceFd(R.raw.song_1))
        } catch (e: IOException) {
            e.printStackTrace()
            stopSelf()
        }
       mediaPlayer.prepareAsync()
    }
}