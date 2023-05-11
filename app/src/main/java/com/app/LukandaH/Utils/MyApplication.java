package com.app.LukandaH.Utils;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
        new WebServices();
    }
}
