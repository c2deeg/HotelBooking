package com.app.LukandaH.Activities.SearchByParametersActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Activities.SearchByParametersActivity.Presenter.SearchByParametersPresenter;
import com.app.LukandaH.Activities.SearchByParametersActivity.View.SearchByParametersView;
import com.app.LukandaH.Adapters.RvSearchByParametersAdapter;
import com.app.LukandaH.Models.RvSearchResultsModelData;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

import java.util.ArrayList;

public class SearchByParametersActivity extends AppCompatActivity implements View.OnClickListener, SearchByParametersView {
    private Activity activity;
    private ImageView img_back;
    private TextView tv_toMainScreen;
    private ImageView img_notification;
    private RecyclerView rv_searchResults;
    private String cityName;
    private String checkIn;
    private String checkOut;
    private String rooms;
    private String adults;
    private SearchByParametersPresenter searchByParametersPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_parameters);
        activity = this;
        searchByParametersPresenter = new SearchByParametersPresenter(activity, this);
        cityName = getIntent().getStringExtra("cityName");
        checkIn = getIntent().getStringExtra("checkIn");
        checkOut = getIntent().getStringExtra("checkOut");
        rooms = getIntent().getStringExtra("rooms");
        adults = getIntent().getStringExtra("adults");

        init();
        listeners();

        searchByParametersPresenter.searchByParametersMethod(cityName,checkIn,checkOut,adults,rooms, rv_searchResults);
    }

    private void init(){
        img_back = findViewById(R.id.img_back);
        tv_toMainScreen = findViewById(R.id.tv_toMainScreen);
        img_notification = findViewById(R.id.img_notification);
        rv_searchResults = findViewById(R.id.rv_searchResults);
    }
    private void listeners() {
        img_back.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == img_back){
            finish();
        } else if(view == tv_toMainScreen){
            Intent intent = new Intent(activity,HomeActivity.class);
            startActivity(intent);
        } else if(view == img_notification){
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity, msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getSupportFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }
}