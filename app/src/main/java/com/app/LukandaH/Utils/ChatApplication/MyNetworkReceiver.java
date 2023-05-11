package com.app.LukandaH.Utils.ChatApplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class MyNetworkReceiver extends BroadcastReceiver {
    private final String TAG = "MyNetworkBroadcast";
    public static boolean isActive = false;


    @Override
    public void onReceive(Context context, Intent intent) {
        isActive = isOnline(context);
        Activity activity = ChatApplication.mActivity;
        Log.d(TAG, activity.getLocalClassName());
        if (!isActive) {

        }

    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();

    }

}
