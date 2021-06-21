package com.example.test.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class singleTonExample {
    static MediaPlayer ref;
    private static singleTonExample ourInstance = new singleTonExample();
    private Context appContext;
    private singleTonExample() { }
    public static Context get() {
        return getInstance().getContext();
    }
    public static synchronized singleTonExample getInstance() {
        return ourInstance;
    }
    public void init(Context context) {
        if (appContext == null) {
            this.appContext = context;
        }
    }
    private Context getContext() {
        return appContext;
    }
    public static MediaPlayer getSingletonMedia() {
        if (ref == null)
        // it's ok, we can call this constructor
        ref = MediaPlayer.create(get(), R.raw.duck_mania);
        return ref;
    }
}