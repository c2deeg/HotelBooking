package com.app.LukandaH.Activities.WelcomeScreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.GetStartedActivity;
import com.app.LukandaH.Activities.LoginActivity;
import com.app.LukandaH.Fragments.HomeFragment.HomeFragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity;
    private Button staffAccButton;
    private Button userAccButton;
    boolean doubleBackToExitPressedOnce = false;
    private ScrollView scrollView;
    private LinearLayout ll_noInternet;
    private Button btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        activity = this;
        init();
        listeners();

        if(Utils.isNetworkConnected(activity) == false){
            ll_noInternet.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else{
            ll_noInternet.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        staffAccButton = findViewById(R.id.staffAccButton);
        userAccButton = findViewById(R.id.userAccButton);
        scrollView = findViewById(R.id.scrollView);
        ll_noInternet = findViewById(R.id.ll_noInternet);
        btn_refresh = findViewById(R.id.btn_refresh);
    }

    private void listeners() {
        staffAccButton.setOnClickListener(this);
        userAccButton.setOnClickListener(this);
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
        Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onClick(View view) {
        if (view == staffAccButton) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.putExtra("role", "staff");
            startActivity(intent);
        } else if (view == userAccButton) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.putExtra("role", "user");
            startActivity(intent);
        } else if(view == btn_refresh){
            Intent intent = new Intent(activity, WelcomeActivity.class);
            startActivity(intent);
        }
    }
}