package com.app.LukandaH.Fragments.BookingAndPaymentFragment.View;

import android.app.Activity;

public interface BookingView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
