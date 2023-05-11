package com.app.LukandaH.Fragments.EditProfileFragment.View;

import android.app.Activity;

import com.app.LukandaH.Models.ProfileModels.ProfileData;

public interface ProfileImageView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();

    void setData(Activity activity, ProfileData data);
}
