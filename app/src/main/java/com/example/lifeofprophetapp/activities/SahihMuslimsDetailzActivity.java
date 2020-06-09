package com.example.lifeofprophetapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifeofprophetapp.R;
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
import java.util.Locale;

public class SahihMuslimsDetailzActivity extends AppCompatActivity implements View.OnClickListener {


    private String id;
    private DBManagerSahihMuslims db;
    private TextView tvArabic,tvEng;
    ImageView ivBack,ivPlay;
    TextView tvTittle;
    boolean isPlaying=false;
    TextToSpeech tts;
    ImageView ivBookmark;
    DataModel dataModel;
    ImageView ivshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahih_muslim_detailz);
        tvArabic = (TextView) findViewById(R.id.tvArabic);
        tvEng=(TextView)findViewById(R.id.tvEng);
        tvTittle=(TextView)findViewById(R.id.tvTittle);
        ivPlay=(ImageView)findViewById(R.id.ivPlayName);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivBookmark=(ImageView)findViewById(R.id.ivbookmark);
        ivshare=(ImageView)findViewById(R.id.ivplay);
        ivshare.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivPlay.setVisibility(View.VISIBLE);

        initAds();
        try {
            Intent intent = getIntent();
            db = new DBManagerSahihMuslims(this);
            id = intent.getStringExtra("id");
            db.createDataBase();
            db.openDataBase();
             dataModel = db.getByChapterNameSahiMuslim(id, UtilsConstants.CHAPTER_NAME);
            tvTittle.setText(UtilsConstants.CHAPTER_NAME);

            tvArabic.setText(dataModel.getHadithArabic());
            tvEng.setText(dataModel.getHadithEnglish());
        } catch (IOException e) {

            Log.d("*exception",e.getMessage()+"");
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

            case R.id.ivPlayName:

                if (isPlaying)
                {
                    tts.stop();
                    isPlaying=false;
                    ivPlay.setImageResource(R.drawable.ic_play);
                }
                else {
                    ivPlay.setImageResource(R.drawable.pause);
                    isPlaying=true;
                    playAudio();
                }
                break;
            case  R.id.ivbookmark:

                Toast.makeText(this, "BookMarked", Toast.LENGTH_SHORT).show();

                DBManagerSahihMuslims db=new DBManagerSahihMuslims(this);
                try {
                    db.createDataBase();
                    db.openDataBase();
                    db.setBookMarks(UtilsConstants.CHAPTER_NAME,dataModel.getId());
                    Toast.makeText(this, "BookMarked", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.ivplay:

                shareText(tvEng.getText().toString()+"\n"+tvArabic.getText().toString());
                break;
        }
    }

    void shareText(String shareText)
    {
        String shareBody = shareText;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share Usig"));
    }
    void  playAudio()
    {
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(new Locale("eng"));
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.d("error", "This Language is not supported");
                    }
                    else{
                        ConvertTextToSpeech();
                    }
                }
                else
                    Log.d("error", "Initilization Failed!");
            }
        });

    }
    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        String  text = tvEng.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text+"is saved", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {

            tts.stop();
        }
        catch (Exception e)
        {

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
