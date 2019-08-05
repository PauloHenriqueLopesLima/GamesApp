package com.example.gamesapp;

import android.app.Application;

import com.example.gamesapp.model.Game;
import com.facebook.stetho.Stetho;

public class GamesApplication extends Application {

    @Override
    public void onCreate() {
        Stetho.initializeWithDefaults(this);
    }
}
