package com.app.LukandaH.Activities.WriteReviewActivity.View;

import android.app.Activity;

public interface WriteReviewView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
