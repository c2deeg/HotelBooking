package com.app.LukandaH.Fragments.ProfileFragment.Presenter;

import android.app.Activity;

import com.app.LukandaH.Fragments.ProfileFragment.View.ProfileView;
import com.app.LukandaH.Handlers.ProfileHandler;
import com.app.LukandaH.Models.ProfileModels.ProfileExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class ProfilePresenter {
    private ProfileView profileView;
    private Activity activity;
    private String token;
    private String id;

    public ProfilePresenter(Activity activity, ProfileView profileView) {
        this.activity = activity;
        this.profileView = profileView;
    }

    public void profileMethod() {
        token = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        id = CSPreferences.readString(activity, Utils.USERID);
        profileView.showDialog(activity);
        WebServices.getmInstance().profileMethod(token, id, new ProfileHandler() {
            @Override
            public void onSuccess(ProfileExample profileExample, String accessToken) {
                profileView.hideDialog();
                if (profileExample != null) {
                    if (profileExample.getIsSuccess() == true) {
                        profileView.showMessage(activity, profileExample.getMessage());
                        profileView.setData(activity, profileExample.getData());
                    } else {
                        profileView.showMessage(activity, profileExample.getMessage());
                    }
                } else {
                    profileView.showMessage(activity, profileExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                profileView.hideDialog();
                profileView.showMessage(activity, message);
            }
        });
    }
}
