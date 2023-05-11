package com.app.LukandaH.Activities.ChatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.Activities.ChatDetailActivity.ChatDetailActivity;
import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.HotelViewActivity.HotelViewActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.R;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity;
    private LinearLayout ll_liveChat;
    private ImageView img_back;
    private TextView tv_toMainScreen;
    private ImageView img_notification;
    private String hotelid;
//    private String userrole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        activity = this;
        hotelid = getIntent().getStringExtra("hotelid");
//        userrole = getIntent().getStringExtra("role");
        init();
        listeners();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(activity, HotelViewActivity.class);
//        String userrole = getIntent().getStringExtra("role");
        intent.putExtra("hotelid", hotelid);
        startActivity(intent);
    }

    private void init() {
        ll_liveChat = findViewById(R.id.ll_liveChat);
        img_back = findViewById(R.id.img_back);
        tv_toMainScreen = findViewById(R.id.tv_toMainScreen);
        img_notification = findViewById(R.id.img_notification);
    }

    private void listeners() {
        ll_liveChat.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ll_liveChat) {
            Intent intent = new Intent(activity, ChatDetailActivity.class);
            intent.putExtra("hotelid", hotelid);
            startActivity(intent);
        } else if (view == img_back) {
            Intent intent = new Intent(activity, HotelViewActivity.class);
            intent.putExtra("hotelid", hotelid);
            startActivity(intent);
        } else if (view == tv_toMainScreen) {
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        } else if (view == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }
    }
}