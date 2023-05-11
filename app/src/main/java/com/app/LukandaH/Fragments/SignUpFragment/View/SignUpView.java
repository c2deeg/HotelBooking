package com.app.LukandaH.Fragments.SignUpFragment.View;

import android.app.Activity;

public interface SignUpView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}

