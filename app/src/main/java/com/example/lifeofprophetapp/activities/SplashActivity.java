package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.ads.InterstitialAdsSingleton;

import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {


    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lottieAnimationView = findViewById(R.id.animation);
        lottieAnimationView.setImageAssetsFolder("images");

        lottieAnimationView.setAnimation("splash_anim.json");
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, IndexActivity.class);
                startActivity(intent);
                adinterstial();
                finish();

            }
        }, 5000);

    }


    public void adinterstial() {

        if (InterstitialAdsSingleton.getInstance().getAd().isLoaded()) {
            InterstitialAdsSingleton.getInstance().getAd().show();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = SplashActivity.this.getAssets().open("splash_anim.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
