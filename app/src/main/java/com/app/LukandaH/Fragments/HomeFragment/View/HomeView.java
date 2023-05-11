package com.app.LukandaH.Fragments.HomeFragment.View;

import android.app.Activity;

import com.app.LukandaH.Models.ProfileModels.ProfileData;

public interface HomeView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();

    public void setData(Activity activity, ProfileData data);
}
