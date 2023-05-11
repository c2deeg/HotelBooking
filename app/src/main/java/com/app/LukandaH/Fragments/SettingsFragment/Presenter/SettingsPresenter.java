package com.app.LukandaH.Fragments.SettingsFragment.Presenter;

import android.app.Activity;

import com.app.LukandaH.Fragments.SettingsFragment.View.SettingsView;
import com.app.LukandaH.Handlers.ProfileHandler;
import com.app.LukandaH.Models.ProfileModels.ProfileExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class SettingsPresenter {
    private SettingsView settingsView;
    private Activity activity;
    private String token;
    private String id;

    public SettingsPresenter(Activity activity, SettingsView settingsView) {
        this.activity = activity;
        this.settingsView = settingsView;
    }

    public void profileMethod() {
        token = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        id = CSPreferences.readString(activity, Utils.USERID);
        settingsView.showDialog(activity);
        WebServices.getmInstance().profileMethod(token, id, new ProfileHandler() {
            @Override
            public void onSuccess(ProfileExample profileExample, String accessToken) {
                settingsView.hideDialog();
                if (profileExample != null) {
                    if (profileExample.getIsSuccess() == true) {
                        settingsView.showMessage(activity, profileExample.getMessage());
                        settingsView.setData(activity, profileExample.getData());
                    } else {
                        settingsView.showMessage(activity, profileExample.getMessage());
                    }
                } else {
                    settingsView.showMessage(activity, profileExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                settingsView.hideDialog();
                settingsView.showMessage(activity, message);
            }
        });
    }
}