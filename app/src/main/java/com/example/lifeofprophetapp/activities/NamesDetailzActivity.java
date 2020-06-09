package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeofprophetapp.R;
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

public class NamesDetailzActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvBenefits, tvArabic, tvEnglishName;
    String id;
    NamesDataModel dataModel;
    ImageView ivPlay;
    TextToSpeech tts;
    ArrayList<Integer> audioList;
    String audioPosition;
    ImageView ivshare,ivBack;
    TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_detailz);

        init();
        initAds();

    }

    void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        audioPosition=intent.getStringExtra("pos");
        audioList = new ArrayList<>();
        DBManager99Names db = new DBManager99Names(this);
        try {
            dataModel = new NamesDataModel();
            db.createDataBase();
            db.openDataBase();
            dataModel = db.getQueryById(id);
            db.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        tvBenefits = (TextView) findViewById(R.id.tvBenefits);
        tvArabic = (TextView) findViewById(R.id.tvArabicName);
        tvEnglishName = (TextView) findViewById(R.id.tvEnglishName);
        tvArabic.setText(dataModel.getArabicName());
        tvBenefits.setText(dataModel.getNameBenefitsEng());
        tvEnglishName.setText(dataModel.getNameEnglish());
        ivPlay = (ImageView) findViewById(R.id.ivplay);
        ivshare=(ImageView)findViewById(R.id.ivshare);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        tvTitle=(TextView)findViewById(R.id.tvTittle);
        tvTitle.setText(tvArabic.getText().toString());
        ivBack.setOnClickListener(this);
        ivshare.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        setAudioFiles();
        int pos=  Integer.parseInt(audioPosition)+1;
        MediaPlayer mediaPlayer = MediaPlayer.create(NamesDetailzActivity.this, audioList.get(pos));
        mediaPlayer.start();


    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if (tts != null) {

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    private void setAudioFiles() {
        audioList = new ArrayList<>();
        audioList.add(R.raw.m_1);
        audioList.add(R.raw.m_1);
        audioList.add(R.raw.m_2);
        audioList.add(R.raw.m_3);
        audioList.add(R.raw.m_4);
        audioList.add(R.raw.m_5);
        audioList.add(R.raw.m_6);
        audioList.add(R.raw.m_7);
        audioList.add(R.raw.m_8);
        audioList.add(R.raw.m_9);
        audioList.add(R.raw.m_10);
        audioList.add(R.raw.m_11);
        audioList.add(R.raw.m_12);
        audioList.add(R.raw.m_13);
        audioList.add(R.raw.m_14);
        audioList.add(R.raw.m_15);
        audioList.add(R.raw.m_16);
        audioList.add(R.raw.m_17);
        audioList.add(R.raw.m_18);
        audioList.add(R.raw.m_19);
        audioList.add(R.raw.m_20);
        audioList.add(R.raw.m_21);
        audioList.add(R.raw.m_22);
        audioList.add(R.raw.m_23);
        audioList.add(R.raw.m_24);
        audioList.add(R.raw.m_25);
        audioList.add(R.raw.m_26);
        audioList.add(R.raw.m_27);
        audioList.add(R.raw.m_28);
        audioList.add(R.raw.m_29);
        audioList.add(R.raw.m_30);
        audioList.add(R.raw.m_31);
        audioList.add(R.raw.m_32);
        audioList.add(R.raw.m_33);
        audioList.add(R.raw.m_34);
        audioList.add(R.raw.m_35);
        audioList.add(R.raw.m_36);
        audioList.add(R.raw.m_37);
        audioList.add(R.raw.m_38);
        audioList.add(R.raw.m_39);
        audioList.add(R.raw.m_40);
        audioList.add(R.raw.m_41);
        audioList.add(R.raw.m_42);
        audioList.add(R.raw.m_43);
        audioList.add(R.raw.m_44);
        audioList.add(R.raw.m_45);
        audioList.add(R.raw.m_46);
        audioList.add(R.raw.m_47);
        audioList.add(R.raw.m_48);
        audioList.add(R.raw.m_49);
        audioList.add(R.raw.m_50);
        audioList.add(R.raw.m_51);
        audioList.add(R.raw.m_52);
        audioList.add(R.raw.m_53);
        audioList.add(R.raw.m_54);
        audioList.add(R.raw.m_55);
        audioList.add(R.raw.m_56);
        audioList.add(R.raw.m_57);
        audioList.add(R.raw.m_58);
        audioList.add(R.raw.m_59);
        audioList.add(R.raw.m_60);
        audioList.add(R.raw.m_61);
        audioList.add(R.raw.m_62);
        audioList.add(R.raw.m_63);
        audioList.add(R.raw.m_64);
        audioList.add(R.raw.m_65);
        audioList.add(R.raw.m_66);
        audioList.add(R.raw.m_67);
        audioList.add(R.raw.m_68);
        audioList.add(R.raw.m_69);
        audioList.add(R.raw.m_70);
        audioList.add(R.raw.m_71);
        audioList.add(R.raw.m_72);
        audioList.add(R.raw.m_73);
        audioList.add(R.raw.m_74);
        audioList.add(R.raw.m_75);
        audioList.add(R.raw.m_76);
        audioList.add(R.raw.m_77);
        audioList.add(R.raw.m_78);
        audioList.add(R.raw.m_79);
        audioList.add(R.raw.m_80);
        audioList.add(R.raw.m_81);
        audioList.add(R.raw.m_82);
        audioList.add(R.raw.m_83);
        audioList.add(R.raw.m_84);
        audioList.add(R.raw.m_85);
        audioList.add(R.raw.m_86);
        audioList.add(R.raw.m_87);
        audioList.add(R.raw.m_88);
        audioList.add(R.raw.m_89);
        audioList.add(R.raw.m_90);
        audioList.add(R.raw.m_91);
        audioList.add(R.raw.m_92);
        audioList.add(R.raw.m_93);
        audioList.add(R.raw.m_94);
        audioList.add(R.raw.m_95);
        audioList.add(R.raw.m_96);
        audioList.add(R.raw.m_97);
        audioList.add(R.raw.m_98);
        audioList.add(R.raw.m_99);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivplay:

                Log.d("*get",audioPosition);
                int pos=  Integer.parseInt(audioPosition)+1;
                MediaPlayer mediaPlayer = MediaPlayer.create(NamesDetailzActivity.this, audioList.get(pos));
                mediaPlayer.start();

                ivPlay.setImageResource(R.drawable.pause);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ivPlay.setImageResource(R.drawable.ic_play);
                    }
                },2000);

                break;
            case R.id.ivshare:
                String shareBody =tvArabic.getText().toString()+"\n"+tvEnglishName.getText().toString()+"\n"+tvBenefits.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Name"));
                break;
            case  R.id.ivBack:

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
