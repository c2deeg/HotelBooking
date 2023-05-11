package com.app.LukandaH.Activities.RatingsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.HotelViewActivity.HotelViewActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Activities.RatingsActivity.Presenter.GetRatingsPresenter;
import com.app.LukandaH.Activities.RatingsActivity.View.GetRatingsView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener, GetRatingsView {
    private Activity activity;
    private RecyclerView rv_reviews;
    private ImageView img_notification;
    private GetRatingsPresenter getRatingsPresenter;
    private String hotelId;
    private TextView tv_toMainScreen;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        activity = this;
        hotelId = getIntent().getStringExtra("hotelid");
        getRatingsPresenter = new GetRatingsPresenter(activity, this);
        init();
        listeners();
        getRatingsPresenter.getRatingsMethod(hotelId,1,100,rv_reviews);
//        reviewPresenter.hotelViewMethod(hotelId);
    }

    private void init() {
        rv_reviews = findViewById(R.id.rv_reviews);
        img_notification = findViewById(R.id.img_notification);
        tv_toMainScreen = findViewById(R.id.tv_toMainScreen);
        img_back = findViewById(R.id.img_back);
    }

    private void listeners() {
        img_notification.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }else if(v == tv_toMainScreen){
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        } else if(v == img_back){
            Intent intent = new Intent(activity, HotelViewActivity.class);
            intent.putExtra("hotelid", hotelId);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(activity, HotelViewActivity.class);
        intent.putExtra("hotelid", hotelId);
        startActivity(intent);
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void showMessage(Activity activity, String message) {
//        Utils.showMessage(activity, message);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getSupportFragmentManager());
    }
}