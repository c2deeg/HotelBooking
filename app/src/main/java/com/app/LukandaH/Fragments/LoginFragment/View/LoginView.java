package com.app.LukandaH.Fragments.LoginFragment.View;

import android.app.Activity;

public interface LoginView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
