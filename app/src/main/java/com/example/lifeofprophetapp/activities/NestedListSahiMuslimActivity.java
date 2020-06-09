package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.adapter.AdapterNestedSahihBukhari;
import com.example.lifeofprophetapp.adapter.AdapterNestedSahihMuslim;
import com.example.lifeofprophetapp.db.DBManagerSahihBukhari;
import com.example.lifeofprophetapp.db.DBManagerSahihMuslims;
import com.example.lifeofprophetapp.pojo.DataModel;
import com.example.lifeofprophetapp.utilz.UtilsConstants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.util.ArrayList;

public class NestedListSahiMuslimActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<DataModel> listItems;
    RecyclerView recyclerview_nested_sahiMuslims;
    String chapterName;
    DBManagerSahihMuslims db;
    ImageView ivBack;
    TextView tvTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_sahi_muslim);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        tvTittle=(TextView)findViewById(R.id.tvTittle);
        tvTittle.setText("List Of Hadith");
         db=new DBManagerSahihMuslims(this);
        recyclerview_nested_sahiMuslims=(RecyclerView)findViewById(R.id.recyclerview_nested_sahiMuslim);
        recyclerview_nested_sahiMuslims.setHasFixedSize(false);
        recyclerview_nested_sahiMuslims.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        Intent intent = getIntent();
        chapterName = intent.getStringExtra("chapterName");
        initAds();
        try {
            db.createDataBase();
            db.openDataBase();
     AsynchTaskclass asynchObject = new AsynchTaskclass();
            asynchObject.execute();

        } catch (IOException e) {
            Log.d("*exce",e.getMessage()+"");
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.ivBack:
                onBackPressed();
                break;
        }
    }

    private   class AsynchTaskclass extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            listItems = db.getQueryByChapterName(chapterName);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("*getListItem",listItems.get(0).toString());
            AdapterNestedSahihMuslim adapter=new AdapterNestedSahihMuslim(NestedListSahiMuslimActivity.this,listItems);
            recyclerview_nested_sahiMuslims.setAdapter(adapter);
            db.close();
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
