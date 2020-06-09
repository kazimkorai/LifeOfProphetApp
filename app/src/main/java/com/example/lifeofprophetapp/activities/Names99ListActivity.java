package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.adapter.Adapter99Names;
import com.example.lifeofprophetapp.db.DBManager99Names;
import com.example.lifeofprophetapp.pojo.NamesDataModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.util.ArrayList;

public class Names99ListActivity extends AppCompatActivity {

    RecyclerView recyclerViewNames;
    Adapter99Names adapterName;
    ArrayList<NamesDataModel> listItems;

    ImageView ivBack,ivPlay;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names99_list);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivPlay=(ImageView)findViewById(R.id.ivPlayName);
       // ivPlay.setVisibility(View.VISIBLE);
        tvTitle=(TextView)findViewById(R.id.tvTittle);
        tvTitle.setText("99 Names of Prophet (PBUH)");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        listItems = new ArrayList<>();
        recyclerViewNames = (RecyclerView) findViewById(R.id.recyclerViewNames);
        recyclerViewNames.setLayoutManager(new GridLayoutManager(this,3));
        recyclerViewNames.setHasFixedSize(false);
        DBManager99Names db = new DBManager99Names(this);
        initAds();
        try {
            db.createDataBase();
            db.openDataBase();
            listItems = db.getQueryAll();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapterName=new Adapter99Names(this,listItems);
        recyclerViewNames.setAdapter(adapterName);



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
