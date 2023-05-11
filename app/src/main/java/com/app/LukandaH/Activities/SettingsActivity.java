package com.app.LukandaH.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.Fragments.SettingsFragment.SettingsFragment;
import com.app.LukandaH.R;

public class SettingsActivity extends AppCompatActivity {
    private Activity activity;
    private FrameLayout fl_settingsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        activity = this;

        init();

        SettingsFragment fragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_settingsContainer, fragment).commit();
    }

    private void init() {
        fl_settingsContainer = findViewById(R.id.fl_settingsContainer);
    }
}