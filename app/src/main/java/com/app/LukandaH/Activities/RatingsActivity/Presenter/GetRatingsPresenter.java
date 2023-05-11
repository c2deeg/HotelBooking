package com.app.LukandaH.Activities.RatingsActivity.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.RatingsActivity.View.GetRatingsView;
import com.app.LukandaH.Adapters.RvReviewsAdapter;
import com.app.LukandaH.Handlers.GetRatingsHandler;
import com.app.LukandaH.Models.GetRatingsModels.GetRatingsExample;
import com.app.LukandaH.Utils.WebServices;

public class GetRatingsPresenter {
    private Activity activity;
    private GetRatingsView getRatingsView;
    private RecyclerView rv_reviews;
    private RvReviewsAdapter adapter;
    private String hotelId;
    private int pageNo;
    private int pageSize;

    public GetRatingsPresenter(Activity activity, GetRatingsView getRatingsView) {
        this.activity = activity;
        this.getRatingsView = getRatingsView;
    }

    public void getRatingsMethod(String hotelId, int pageNo, int pageSize, RecyclerView rv_reviews){
        this.hotelId = hotelId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.rv_reviews = rv_reviews;
        getRatingsView.showDialog(activity);
        WebServices.getmInstance().getRatingsMethod(hotelId, pageNo, pageSize, new GetRatingsHandler() {
            @Override
            public void onSuccess(GetRatingsExample getRatingsExample) {
                getRatingsView.hideDialog();
                if(getRatingsExample != null){
                    if(getRatingsExample.getIsSuccess() == true){
                        getRatingsView.showMessage(activity, getRatingsExample.getStatusCode().toString());
                        adapter = new RvReviewsAdapter(activity, getRatingsExample.getItems());
                        rv_reviews.setHasFixedSize(true);
                        rv_reviews.setLayoutManager(new LinearLayoutManager(activity));
                        rv_reviews.setAdapter(adapter);
                    } else{
                        getRatingsView.showMessage(activity, getRatingsExample.getStatusCode().toString());
                    }
                } else {
                    getRatingsView.showMessage(activity, getRatingsExample.getStatusCode().toString());
                }
            }

            @Override
            public void onError(String message) {
                getRatingsView.hideDialog();
                getRatingsView.showMessage(activity, message);
            }
        });
    }
}
