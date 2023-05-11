package com.app.LukandaH.Activities.NotificationsActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Adapters.RvNotificationsAdapter;
import com.app.LukandaH.Models.RvNotificationsModelData;
import com.app.LukandaH.R;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity;
    private ImageView img_back;
    private RecyclerView rv_notifications;
    private ArrayList<RvNotificationsModelData> data;
    private RvNotificationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        activity = this;

        init();
        listeners();

        data = new ArrayList<>();
        data.add(new RvNotificationsModelData("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est.", "11:30 AM"));
        data.add(new RvNotificationsModelData("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est.", "11:30 AM"));
        data.add(new RvNotificationsModelData("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est.", "11:30 AM"));
        data.add(new RvNotificationsModelData("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est.", "11:30 AM"));
        data.add(new RvNotificationsModelData("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est.", "11:30 AM"));


        adapter = new RvNotificationsAdapter(activity, data);
        rv_notifications.setHasFixedSize(true);
        rv_notifications.setLayoutManager(new LinearLayoutManager(activity));
        rv_notifications.setAdapter(adapter);
    }

    private void init() {
        img_back = findViewById(R.id.img_back);
        rv_notifications = findViewById(R.id.rv_notifications);
    }

    private void listeners() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            finish();
        }
    }
}