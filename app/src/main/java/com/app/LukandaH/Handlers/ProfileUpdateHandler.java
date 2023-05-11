package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ProfileUpdateModels.ProfileUpdateExample;

public interface ProfileUpdateHandler {
    public void onSuccess(ProfileUpdateExample profileUpdateExample, String accessToken);

    public void onError(String message);
}

