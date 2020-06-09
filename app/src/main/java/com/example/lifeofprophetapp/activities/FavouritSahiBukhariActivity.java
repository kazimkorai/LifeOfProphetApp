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
import android.widget.LinearLayout;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.adapter.AdapterNestedSahihBukhariFvrt;
import com.example.lifeofprophetapp.db.DBManagerSahihBukhari;
import com.example.lifeofprophetapp.pojo.DataModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.util.ArrayList;

public class FavouritSahiBukhariActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener {

    RecyclerView recyclerview_fvrt_sahi_bukhari;
    AdapterNestedSahihBukhariFvrt adapter;
    ArrayList<DataModel> listItems;
    ImageView ivDelete,ivBack;
    LinearLayout linearLayout;
    ImageView ivNofvrt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourit_sahi_bukhari);
        listItems = new ArrayList<>();
        ivDelete=(ImageView)findViewById(R.id.ivPlayName);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivNofvrt=(ImageView)findViewById(R.id.ivNofvrt);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);

        recyclerview_fvrt_sahi_bukhari = (RecyclerView) findViewById(R.id.recyclerview_fvrt_sahi_bukhari);
        recyclerview_fvrt_sahi_bukhari.setHasFixedSize(false);
        recyclerview_fvrt_sahi_bukhari.setLayoutManager(new LinearLayoutManager(this));
        ivDelete.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        initAds();
        DBManagerSahihBukhari db = new DBManagerSahihBukhari(this);

        try {
            db.createDataBase();
            db.openDataBase();
            listItems = db.getBookmarkQueryAll();
            Log.d("*listItems",listItems.size()+"");
            adapter=new AdapterNestedSahihBukhariFvrt(this,listItems);
            recyclerview_fvrt_sahi_bukhari.setAdapter(adapter);
            if (listItems.size()>0)
            {
                linearLayout.setVisibility(View.VISIBLE);
                ivNofvrt.setVisibility(View.INVISIBLE);
            }
            else {
                linearLayout.setVisibility(View.INVISIBLE);
                ivNofvrt.setVisibility(View.VISIBLE);
            }

            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case  R.id.ivPlayName:


                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
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
