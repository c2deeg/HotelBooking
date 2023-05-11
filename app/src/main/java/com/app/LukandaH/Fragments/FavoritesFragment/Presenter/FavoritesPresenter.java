package com.app.LukandaH.Fragments.FavoritesFragment.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Adapters.RvFavouritesAdapter;
import com.app.LukandaH.Fragments.FavoritesFragment.View.FavoritesView;
import com.app.LukandaH.Handlers.GetFavoriteHandler;
import com.app.LukandaH.Handlers.GetRatingsHandler;
import com.app.LukandaH.Models.GetRatingsModels.GetRatingsExample;
import com.app.LukandaH.Models.GetfavoritesModels.GetFavoriteExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class FavoritesPresenter {
    private final Activity activity;
    private final FavoritesView favoritesView;
    private RecyclerView rv_favourites;
    private RvFavouritesAdapter adapter;
    private String hotelId;
    private int pageNo;
    private int pageSize;

    public FavoritesPresenter(Activity activity, FavoritesView favoritesView) {
        this.activity = activity;
        this.favoritesView = favoritesView;
    }

    public void getFavMethod(RecyclerView rv_favourites) {
        this.rv_favourites = rv_favourites;
        String userId = CSPreferences.readString(activity, Utils.USERID);
        favoritesView.showDialog(activity);
        WebServices.getmInstance().getFavoriteMethod(userId, new GetFavoriteHandler() {
            @Override
            public void onSuccess(GetFavoriteExample getFavoriteExample) {
                favoritesView.hideDialog();
                if (getFavoriteExample != null) {
                    if (getFavoriteExample.getIsSuccess() == true) {
                        favoritesView.showMessage(activity, getFavoriteExample.getStatusCode().toString());
                        adapter = new RvFavouritesAdapter(activity, getFavoriteExample.getData());
                        rv_favourites.setHasFixedSize(true);
                        rv_favourites.setLayoutManager(new LinearLayoutManager(activity));
                        rv_favourites.setAdapter(adapter);
                    } else {
                        favoritesView.showMessage(activity, getFavoriteExample.getStatusCode().toString());
                    }
                } else {
                    favoritesView.showMessage(activity, getFavoriteExample.getStatusCode().toString());
                }
            }

            @Override
            public void onError(String message) {
                favoritesView.hideDialog();
                favoritesView.showMessage(activity, message);
            }
        });
    }

    public void getRatingsMethod(String hotelId, int pageNo, int pageSize) {
        this.hotelId = hotelId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;

//        favoritesView.showDialog(activity);
        WebServices.getmInstance().getRatingsMethod(hotelId, pageNo, pageSize, new GetRatingsHandler() {
            @Override
            public void onSuccess(GetRatingsExample getRatingsExample) {
                favoritesView.hideDialog();
                if (getRatingsExample != null) {
                    if (getRatingsExample.getIsSuccess() == true) {
                        favoritesView.showMessage(activity, getRatingsExample.getStatusCode().toString());
                    } else {
                        favoritesView.showMessage(activity, getRatingsExample.getStatusCode().toString());
                    }
                } else {
                    favoritesView.showMessage(activity, getRatingsExample.getStatusCode().toString());
                }
            }

            @Override
            public void onError(String message) {
                favoritesView.showMessage(activity, message);
            }
        });
    }
}