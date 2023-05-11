package com.app.LukandaH.Activities.WriteReviewActivity.Presenter;

import android.app.Activity;
import android.widget.EditText;

import com.app.LukandaH.Activities.WriteReviewActivity.View.WriteReviewView;
import com.app.LukandaH.Handlers.WriteReviewHandler;
import com.app.LukandaH.Models.WriteReviewModels.WriteReviewExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class WriteReviewPresenter {
    private final Activity activity;
    private final WriteReviewView writeReviewView;
    private String hotelId;
    private int rating;
    private EditText review;

    public WriteReviewPresenter(Activity activity, WriteReviewView writeReviewView) {
        this.activity = activity;
        this.writeReviewView = writeReviewView;
    }

    public void writeReviewMethod(String hotelId, int rating, EditText review) {
        this.hotelId = hotelId;
        this.rating = rating;
        this.review = review;
        String userId = CSPreferences.readString(activity, Utils.USERID);
        writeReviewView.showDialog(activity);
        WebServices.getmInstance().writeReviewMethod(userId, hotelId, rating, review.getText().toString().trim(), new WriteReviewHandler() {
            @Override
            public void onSuccess(WriteReviewExample writeReviewExample) {
                writeReviewView.hideDialog();
                if (writeReviewExample != null) {
                    if (writeReviewExample.getIsSuccess() == true) {
                        writeReviewView.showMessage(activity, writeReviewExample.getMessage());
                        activity.finish();
                    } else {
                        writeReviewView.showMessage(activity, writeReviewExample.getMessage());
                    }
                } else {
                    writeReviewView.showMessage(activity, writeReviewExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                writeReviewView.hideDialog();
                writeReviewView.showMessage(activity, message);
            }
        });
    }
}
