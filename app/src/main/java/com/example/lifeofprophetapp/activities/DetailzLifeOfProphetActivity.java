package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.db.DBManagerLifeOfProphet;
import com.example.lifeofprophetapp.pojo.DataModelForLifeOf;
import com.example.lifeofprophetapp.pojo.DataModelImageOfChapter;
import com.example.lifeofprophetapp.utilz.UtilsConstants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class DetailzLifeOfProphetActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvDesc;
    String id;
    ImageView ivQuran,ivBack;
    ArrayList<DataModelImageOfChapter> list=new ArrayList<>();
    TextToSpeech tts;
    TextView tvTittle;

    FrameLayout adContainerView;
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailz_life_of_prophet);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        ivQuran=(ImageView)findViewById(R.id.ivQuran);
        tvTittle=(TextView)findViewById(R.id.tvTittle);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        setImages();
        initAds();
        DBManagerLifeOfProphet dbManagerLifeOfProphet = new DBManagerLifeOfProphet(this);
        try {
            dbManagerLifeOfProphet.createDataBase();
            dbManagerLifeOfProphet.openDataBase();
            DataModelForLifeOf dataModelForLifeOf = dbManagerLifeOfProphet.getQueryByChapterNameAndId(id, UtilsConstants.CHAPTER_NAME);
            tvDesc.setText(dataModelForLifeOf.getDesc());
            dbManagerLifeOfProphet.close();
            Log.d("*chapterImage", dataModelForLifeOf.getChapterSubName());
            String image = dataModelForLifeOf.getChapterSubName();
            tvTittle.setText(image);

            for (int a=0;a<list.size();a++)

            {
                if (list.get(a).getChapterName().equalsIgnoreCase(image))
                {
                    ivQuran.setImageResource(list.get(a).getImage());
                }



            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();


    }


    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
      String  text = tvDesc.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text+"is saved", TextToSpeech.QUEUE_FLUSH, null);
    }

    void setImages() {
         list = new ArrayList<>();
        list.add(new DataModelImageOfChapter("Lineagae and family of Muhammad SAW", R.drawable.lineage_family));
        list.add(new DataModelImageOfChapter("Qusai bin Kilab and Quraish", R.drawable.qusai_bin_kilab));
        list.add(new DataModelImageOfChapter("Forks of Sheba, the oldest  nation of pure Arabs, their ages", R.drawable.forks_of_sheba));
        list.add(new DataModelImageOfChapter("Descendants Of Ismael", R.drawable.descendants_of_ismael));
        list.add(new DataModelImageOfChapter("Arab Tribes, A Detailed Study", R.drawable.arab_tribes_detail_study));
        list.add(new DataModelImageOfChapter("Overview of Arab Tribes", R.drawable.overview_of_arab_tribes));
        list.add(new DataModelImageOfChapter("The Land of Arab, Geographically", R.drawable.the_land_of_gergrapically));
        list.add(new DataModelImageOfChapter("13.1 Say (o Muhammad SAW to mankind)", R.drawable.a_divine_message));
        list.add(new DataModelImageOfChapter("13.2 What a profound divine message of Allah", R.drawable.a_divine_message));
        list.add(new DataModelImageOfChapter(" 3.35 Message and prophethood", R.drawable.message_and_prophethood));
        list.add(new DataModelImageOfChapter("3.34 A Quick review of Muhammad’s SAW biography", R.drawable.a_quick_reviewof_muhammads_biography));
        list.add(new DataModelImageOfChapter("Early Job", R.drawable.early_job));
        list.add(new DataModelImageOfChapter("3.32 Birth and 40 years prior to prophethood",R.drawable.birth_and_40_years_prior_to_prophethood));
        list.add(new DataModelImageOfChapter("3.31 A bit detailed lineage",R.drawable.lineage_family));
        list.add(new DataModelImageOfChapter("3.30 The Passing Away",R.drawable.passing_death));
        list.add(new DataModelImageOfChapter("3.29 Last Words",R.drawable.lastsermon));
        list.add(new DataModelImageOfChapter("3.27 Last Sermon",R.drawable.lastsermon));
        list.add(new DataModelImageOfChapter("3.25 Battle of Tabuk",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.24 Battle of Taif",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.23 Battle of Autas",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.22 Battle of Hunain",R.drawable.battle));
       list.add(new DataModelImageOfChapter("3.22 Battle of Hunain",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.21 The Conquest of Makkah",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.20 Battle of Mutah",R.drawable.battle));
        list.add(new DataModelImageOfChapter(" 3.19 Battle of Khyber",R.drawable.battle));
        list.add(new DataModelImageOfChapter(" 3.17 Battle of Quraydha",R.drawable.battle_of_quraydha));
        list.add(new DataModelImageOfChapter("3.16 Battle of Ahzab",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.15 Battle of Uhud",R.drawable.battle));
        list.add(new DataModelImageOfChapter("3.14 Battle of Badr",R.drawable.baddr_battle));
        list.add(new DataModelImageOfChapter("3.12 Constitution of Madina",R.drawable.charter_of_madina));
        list.add(new DataModelImageOfChapter("3.10 Migration to Madina",R.drawable.early_immigration));
        list.add(new DataModelImageOfChapter("3.6 Migration to Abyssinia",R.drawable.early_immigration));
        list.add(new DataModelImageOfChapter("3.5 Troubles in The Mission",R.drawable.troubles));
        list.add(new DataModelImageOfChapter("3.1 Birth",R.drawable.babyhood));
        list.add(new DataModelImageOfChapter("3.2 Youth",R.drawable.babyhood));

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

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivplay:
                shareText(tvDesc.getText().toString());
                break;

            case R.id.ivPlayName:
               // playAudio();
                break;
            case R.id.ivBack:

                onBackPressed();
                break;
        }
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
