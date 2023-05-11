package com.app.LukandaH.Fragments.BookedHotelFragment.View;

import android.app.Activity;

public interface BookHotelView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
