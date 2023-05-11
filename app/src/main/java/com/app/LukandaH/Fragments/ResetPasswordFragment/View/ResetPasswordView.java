package com.app.LukandaH.Fragments.ResetPasswordFragment.View;

import android.app.Activity;

import com.app.LukandaH.Models.ProfileModels.ProfileData;

public interface ResetPasswordView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
