package com.app.LukandaH.Activities.SearchByParametersActivity.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.SearchByParametersActivity.View.SearchByParametersView;
import com.app.LukandaH.Adapters.RvSearchByParametersAdapter;
import com.app.LukandaH.Handlers.SearchByParamtersHandler;
import com.app.LukandaH.Models.SearchByParametersModels.SearchByParametersExample;
import com.app.LukandaH.Utils.WebServices;

public class SearchByParametersPresenter {
    private final Activity activity;
    private final SearchByParametersView searchByParametersView;
    private String city;
    private String checkIn;
    private String checkOut;
    private String adults;
    private String rooms;
    private RecyclerView rv_searchResults;
    private RvSearchByParametersAdapter adapter;

    public SearchByParametersPresenter(Activity activity, SearchByParametersView searchByParametersView) {
        this.activity = activity;
        this.searchByParametersView = searchByParametersView;
    }

    public void searchByParametersMethod(String city, String checkIn, String checkOut, String adults, String rooms, RecyclerView rv_searchResults) {
        this.city = city;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.adults = adults;
        this.rooms = rooms;
        this.rv_searchResults = rv_searchResults;

        searchByParametersView.showDialog(activity);
        WebServices.getmInstance().searchByParametersMethod(city, checkIn, checkOut, adults, rooms, new SearchByParamtersHandler() {
            @Override
            public void onSuccess(SearchByParametersExample SearchByParametersExample) {
                searchByParametersView.hideDialog();
                if (SearchByParametersExample != null) {
                    if (SearchByParametersExample.getIsSuccess() == true) {
                        searchByParametersView.showMessage(activity, SearchByParametersExample.getStatusCode().toString());
                        adapter = new RvSearchByParametersAdapter(activity, SearchByParametersExample.getData());
                        rv_searchResults.setHasFixedSize(true);
                        rv_searchResults.setLayoutManager(new LinearLayoutManager(activity));
                        rv_searchResults.setAdapter(adapter);
                    } else {
                        searchByParametersView.showMessage(activity, SearchByParametersExample.getStatusCode().toString());
                    }
                } else {
                    searchByParametersView.showMessage(activity, SearchByParametersExample.getStatusCode().toString());
                }
            }

            @Override
            public void onError(String message) {
                searchByParametersView.hideDialog();
                searchByParametersView.showMessage(activity, message);
            }
        });
    }
}
