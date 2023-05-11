package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ChangePasswordModels.ChangePasswordExample;

public interface ChangePasswordHandler {
    public void onSuccess(ChangePasswordExample changePasswordExample, String accessToken);

    public void onError(String message);
}
