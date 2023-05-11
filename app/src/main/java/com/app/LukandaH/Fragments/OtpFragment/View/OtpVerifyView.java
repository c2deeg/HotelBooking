package com.app.LukandaH.Fragments.OtpFragment.View;

import android.app.Activity;

public interface OtpVerifyView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
