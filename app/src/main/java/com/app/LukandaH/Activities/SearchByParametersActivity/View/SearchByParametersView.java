package com.app.LukandaH.Activities.SearchByParametersActivity.View;

import android.app.Activity;

public interface SearchByParametersView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
