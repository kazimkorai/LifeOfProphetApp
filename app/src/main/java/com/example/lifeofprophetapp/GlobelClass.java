package com.example.lifeofprophetapp;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.example.lifeofprophetapp.ads.InterstitialAdsSingleton;


public class GlobelClass extends MultiDexApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        InterstitialAdsSingleton.init(this);
    }
}
