package com.app.LukandaH.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Fragments.BookedHotelFragment.BookedHotelsFragment;
import com.app.LukandaH.Fragments.FavoritesFragment.FavouriteFragment;
import com.app.LukandaH.Fragments.HomeFragment.HomeFragment;
import com.app.LukandaH.Fragments.ProfileFragment.ProfileFragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, View.OnClickListener {
    boolean doubleBackToExitPressedOnce = false;
    private Activity activity;
    private FrameLayout homeContainer;
    private BottomNavigationView bottomNavigation;
    private LinearLayout ll_noInternet;
    private Button btn_refresh;
    private LocationManager manager;
//    private String userrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
//        Intent intent = getIntent();
//        userrole = intent.getStringExtra("role");
//        Log.d("check", userrole);
        init();
        internetCheck();
        statusCheck();
        listeners();
    }

    private void internetCheck() {
        if (Utils.isNetworkConnected(activity) == false) {
            homeContainer.setVisibility(View.GONE);
            bottomNavigation.setVisibility(View.GONE);
            ll_noInternet.setVisibility(View.VISIBLE);
        } else {
            Fragment fragment = new HomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("role", userrole);
//        fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, fragment).commit();
            homeContainer.setVisibility(View.VISIBLE);
            bottomNavigation.setVisibility(View.VISIBLE);
            ll_noInternet.setVisibility(View.GONE);
        }
    }

    private void init() {
        homeContainer = findViewById(R.id.homeContainer);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        ll_noInternet = findViewById(R.id.ll_noInternet);
        btn_refresh = findViewById(R.id.btn_refresh);
    }

    private void listeners() {
        bottomNavigation.setOnItemSelectedListener(this);
        btn_refresh.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        bottomNavigation.getMenu().findItem(R.id.home).setChecked(true);
        Fragment fragment = new HomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("role", userrole);
//        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, fragment).commit();
        Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("role", userrole);
//                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, fragment).commit();
                return true;
            case R.id.like:
                fragment = new FavouriteFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, fragment).commit();
                return true;
            case R.id.casee:
                fragment = new BookedHotelsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, fragment).commit();
                return true;
            case R.id.profile:
                fragment = new ProfileFragment();
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("role", userrole);
//                fragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, fragment).commit();
                return true;
        }
        return false;
    }

    public void statusCheck() {
        manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View view) {
        if(view == btn_refresh){
            Intent intent = new Intent(activity,HomeActivity.class);
            startActivity(intent);
        }
    }
}