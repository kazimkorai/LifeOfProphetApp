package com.example.lifeofprophetapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lifeofprophetapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView ivLifeOfMuhammad,ivNames,ivSahiMuslim,ivSahihBukhari;
    ImageView ivDot,ivRateus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        permission();
        init();

    }
    private  void init()
    {
        ivSahiMuslim=(ImageView) findViewById(R.id.ivSahiMuslim);
        ivSahihBukhari=(ImageView) findViewById(R.id.ivSahihBukhari);
        ivLifeOfMuhammad=(ImageView) findViewById(R.id.ivLifeOfMuhmmad);
        ivDot=(ImageView)findViewById(R.id.ivDot);
        ivRateus=(ImageView)findViewById(R.id.ivRateus);
        ivLifeOfMuhammad.setOnClickListener(this);
        ivSahiMuslim.setOnClickListener(this);
        ivSahihBukhari.setOnClickListener(this);
        ivDot.setOnClickListener(this);
        ivRateus.setOnClickListener(this);
        ivNames=(ImageView)findViewById(R.id.ivNames);
        ivNames.setOnClickListener(this);
        initAds();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivSahiMuslim:

                Intent intentSahiMuslim=new Intent(IndexActivity.this, SahihMuslimActivity.class);
                startActivity(intentSahiMuslim);
                break;
            case R.id.ivSahihBukhari:
                Intent intentSahiBukhari=new Intent(IndexActivity.this, SahihBukhariActivity.class);
                startActivity(intentSahiBukhari);
                break;

            case R.id.ivLifeOfMuhmmad:
                Intent intent=new Intent(IndexActivity.this,LifeOfMuhammadActivity.class);
                startActivity(intent);
                break;
            case  R.id.ivDot:
                showMenu();
                break;
            case R.id.ivRateus:


                dailogCustom(false);
                break;
            case  R.id.ivNames:

                Intent intent1=new Intent(IndexActivity.this,Names99ListActivity.class);
                startActivity(intent1);


        }
    }
    void  dailogCustom(final boolean isBack)
    {
        final Dialog dialog = new Dialog(IndexActivity.this);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_dailog_rate_us);

        ImageView ivRateus = dialog.findViewById(R.id.ivRateus);
        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
                if (isBack)
                {
                    finish();
                }

            }
        });

        ivRateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }
    private void showMenu() {

        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(IndexActivity.this, ivDot);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.bookmarks:
                        Intent intent=new Intent(IndexActivity.this,FavouriteActivity.class);
                        startActivity(intent);
                      break;
                    case R.id.share:

                        break;
                    case R.id.rate_us:
                        //rateApp();
                        break;
                    case R.id.privacy_policy:
                        //   privacyPolicy();
                        break;
                }

                return true;
            }
        });
        popup.show();//showing popup menu
    }


    private  void  permission()
    {
        Dexter.withContext(this)
                .withPermissions(
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {


            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }

    @Override
    public void onBackPressed() {

        dailogCustom(true);

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
