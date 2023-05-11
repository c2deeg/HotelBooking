package com.app.LukandaH.Fragments.ChangePasswordFragment.View;

import android.app.Activity;

public interface ChangePasswordView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
