package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.adapter.AdapterLifeOfMuhammad;
import com.example.lifeofprophetapp.db.DBManagerLifeOfProphet;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;

public class LifeOfMuhammadActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerViewLifeofProphet;
    ImageView ivBack;
    TextView tvTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_of_muhammad);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        tvTittle=(TextView)findViewById(R.id.tvTittle);
        tvTittle.setText("Life Of Prophet Muhammad(PBUH)");
        ivBack.setOnClickListener(this);
        initAds();
        recyclerViewLifeofProphet=(RecyclerView)findViewById(R.id.recyclerViewLifeofProphet);
        recyclerViewLifeofProphet.setHasFixedSize(false);
        recyclerViewLifeofProphet.setLayoutManager(new LinearLayoutManager(this));
        DBManagerLifeOfProphet db=new DBManagerLifeOfProphet(this);


        try {
            db.createDataBase();
            db.openDataBase();
            db.getQueryAll();

            AdapterLifeOfMuhammad adapter=new AdapterLifeOfMuhammad(this,db.getQueryAll());
            recyclerViewLifeofProphet.setAdapter(adapter);

            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivBack:

                onBackPressed();
                break;

        }
    }
    void  initAds()
    {
        FrameLayout adContainerView;
        AdView adView;
        adContainerView =findViewById(R.id.fadplaceholder);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });

        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getResources().getString(R.string.admob_id));
        adContainerView.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);

        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }
    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        WindowManager windowmanager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics outMetrics = new DisplayMetrics();
        assert windowmanager != null;
        windowmanager.getDefaultDisplay().getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
}
