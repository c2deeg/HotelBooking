package com.app.LukandaH.Fragments.HomeFragment.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Adapters.RvLatestSearchAdapter;
import com.app.LukandaH.Adapters.RvNearMeAdapter;
import com.app.LukandaH.Fragments.HomeFragment.View.HomeView;
import com.app.LukandaH.Fragments.ProfileFragment.View.ProfileView;
import com.app.LukandaH.Handlers.LatestSearchHandler;
import com.app.LukandaH.Handlers.NearMeHotelsHandler;
import com.app.LukandaH.Handlers.ProfileHandler;
import com.app.LukandaH.Models.LatestSearchModels.LatestSearchExample;
import com.app.LukandaH.Models.NearMeHotelsModel.NearMeHotelsExample;
import com.app.LukandaH.Models.ProfileModels.ProfileExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

import java.util.Locale;

public class HomePresenter {
    //    private String userrole;
    private RvNearMeAdapter rvNearMeAdapter;
    private RecyclerView rv_nearMe;
    private Activity activity;
    private HomeView homeView;
    private String token;
    private String id;
    private RecyclerView rv_latestSearch;
    private RvLatestSearchAdapter adapter;
    private String lng;
    private String lat;
    private TextView tv_latestSearch;

    public HomePresenter(Activity activity, HomeView homeView, RecyclerView rv_nearMe, RecyclerView rv_latestSearch) {
        this.activity = activity;
        this.homeView = homeView;
        this.rv_nearMe = rv_nearMe;
//        this.rvNearMeAdapter = rvNearMeAdapter;
//        this.profileView = profileView;
        this.rv_latestSearch = rv_latestSearch;
//        this.userrole = userrole;
    }

    public void nearMeHotelsMethod(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
//        homeView.showDialog(activity);
        WebServices.getmInstance().nearMeHotelsMethod(lng.trim(), lat.trim(), new NearMeHotelsHandler() {
            @Override
            public void onSuccess(NearMeHotelsExample nearMeHotelsExample) {
                homeView.hideDialog();
                if (nearMeHotelsExample != null) {
                    if (nearMeHotelsExample.getIsSuccess() == true) {
                        rvNearMeAdapter = new RvNearMeAdapter(activity, nearMeHotelsExample.getData());
                        rv_nearMe.setLayoutManager(new LinearLayoutManager(activity));
                        rv_nearMe.setAdapter(rvNearMeAdapter);
                    }
                }
            }

            @Override
            public void onError(String message) {
                homeView.hideDialog();
                homeView.showMessage(activity, message);
            }
        });
    }

    public void latestSearchMethod(TextView tv_latestSearch) {
        this.tv_latestSearch = tv_latestSearch;
        String userId = CSPreferences.readString(activity, Utils.USERID);
//        homeView.showDialog(activity);
        WebServices.getmInstance().latestSearchMethod(userId, new LatestSearchHandler() {
            @Override
            public void onSuccess(LatestSearchExample latestSearchExample) {
                homeView.hideDialog();
                if (latestSearchExample != null) {
                    if (latestSearchExample.getIsSuccess() == true) {
                        adapter = new RvLatestSearchAdapter(latestSearchExample.getData(), activity);
                        if(latestSearchExample.getData().size() == 0){
                            tv_latestSearch.setVisibility(View.GONE);
                        } else{
                            tv_latestSearch.setVisibility(View.VISIBLE);
                        }
                        rv_latestSearch.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                        rv_latestSearch.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onError(String message) {
                homeView.hideDialog();
                homeView.showMessage(activity, message);
            }
        });
    }

    public void profileMethod() {
        token = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        id = CSPreferences.readString(activity, Utils.USERID);
        homeView.showDialog(activity);
        WebServices.getmInstance().profileMethod(token, id, new ProfileHandler() {
            @Override
            public void onSuccess(ProfileExample profileExample, String accessToken) {
                homeView.hideDialog();
                if (profileExample != null) {
                    if (profileExample.getIsSuccess() == true) {
                        homeView.showMessage(activity, profileExample.getMessage());
                        homeView.setData(activity, profileExample.getData());
                    } else {
                        homeView.showMessage(activity, profileExample.getMessage());
                    }
                } else {
                    homeView.showMessage(activity, profileExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                homeView.hideDialog();
                homeView.showMessage(activity, message);
            }
        });
    }
}