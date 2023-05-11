package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.SignUpModels.SignUpExample;

public interface SignUpHandler {
    public void onSuccess(SignUpExample signUpExample, String accessToken);

    public void onError(String message);
}
