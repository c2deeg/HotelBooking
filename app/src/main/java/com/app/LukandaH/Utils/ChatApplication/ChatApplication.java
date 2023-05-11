package com.app.LukandaH.Utils.ChatApplication;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class ChatApplication extends Application {
    private final String TAG = "ChatApplication";
    private Socket mSocket;
    public static Activity mActivity;
    private MyNetworkReceiver mNetworkReceiver;

    {
        try {
            mSocket = IO.socket("http://34.231.88.85:8001");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public com.github.nkzawa.socketio.client.Socket getmSocket() {
        return mSocket;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mNetworkReceiver = new MyNetworkReceiver();

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
//                Log.d(TAG,mActivity.getLocalClassName());
//                mActivity=activity;
//                registerBroadcastForNaugot();

            }

            @Override
            public void onActivityPaused(Activity activity) {
                mActivity = null;
                //     unregisterReceiver(mNetworkReceiver);

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private void registerBroadcastForNaugot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        }
    }


}
