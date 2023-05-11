package com.app.LukandaH.Fragments.ForgotPasswordFragment.View;

import android.app.Activity;

public interface ForgotPasswordView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
