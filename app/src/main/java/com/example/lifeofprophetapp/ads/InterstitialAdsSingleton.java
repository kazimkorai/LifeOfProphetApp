package com.example.lifeofprophetapp.ads;

import android.content.Context;

import com.example.lifeofprophetapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class InterstitialAdsSingleton {
    private static InterstitialAdsSingleton INSTANCE = null;
    private Context context;
    private InterstitialAd mInterstitialAd;

    private InterstitialAdsSingleton(Context context) {
        this.context = context;
        setupAd();
    }
    public static InterstitialAdsSingleton getInstance() {
        return INSTANCE;
    }

    public static void init(Context context){
        INSTANCE = new InterstitialAdsSingleton(context.getApplicationContext());
    }

    private void setupAd() {
        final AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd = new InterstitialAd(context.getApplicationContext());
        mInterstitialAd.setAdUnitId(context.getString(R.string.admob_interistitial));

        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(adRequest);
                super.onAdClosed();


            }

            @Override
            public void onAdFailedToLoad(int i) {
                mInterstitialAd.loadAd(adRequest);
                super.onAdFailedToLoad(i);
            }

        });
    }

    public InterstitialAd getAd(){
        return mInterstitialAd;
    }


}
