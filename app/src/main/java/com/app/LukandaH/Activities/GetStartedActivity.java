package com.app.LukandaH.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.LukandaH.Activities.WelcomeScreen.WelcomeActivity;
import com.app.LukandaH.R;

public class GetStartedActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startButton;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        activity = this;
        init();
        listeners();
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        statusCheck();
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private void init() {
        startButton = findViewById(R.id.startButton);
    }

    private void listeners() {
        startButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onClick(View view) {
        if (view == startButton) {
            Intent intent = new Intent(activity, WelcomeActivity.class);
            startActivity(intent);
        }
    }
}