package com.app.LukandaH.Fragments.SettingsFragment.View;

import android.app.Activity;

import com.app.LukandaH.Models.ProfileModels.ProfileData;

public interface SettingsView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();

    public void setData(Activity activity, ProfileData data);
}
