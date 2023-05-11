package com.app.LukandaH.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.R;

public class BookingSucessfulActivity extends AppCompatActivity {
    Handler handler = new Handler();
    private Activity activity;
//    private String userrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_sucessful);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity = this;
//        userrole = getIntent().getStringExtra("role");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, HomeActivity.class);
//                intent.putExtra("role", userrole);
                startActivity(intent);
            }
        }, 2000);
    }
}