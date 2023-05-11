package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ProfileImageModels.ProfileImageExample;

public interface ProfileImageHandler {
    public void onSuccess(ProfileImageExample profileImageExample);

    public void onError(String message);
}
