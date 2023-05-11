package com.app.LukandaH.Utils;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class MyLukandah extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
        new WebServices();
    }
}
