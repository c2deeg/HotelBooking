package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ProfileModels.ProfileExample;

public interface ProfileHandler {
    public void onSuccess(ProfileExample profileExample, String accessToken);

    public void onError(String message);
}
