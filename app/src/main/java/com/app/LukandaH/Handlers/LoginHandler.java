package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.LoginModels.LoginExample;

public interface LoginHandler {
    public void onSuccess(LoginExample loginExample, String accessToken);

    public void onError(String message);
}
