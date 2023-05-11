package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.SignUpModels.SignUpExample;
import com.app.LukandaH.Models.SocialLoginModels.SocialLoginExample;

public interface SocialLoginHandler {
    public void onSuccess(SocialLoginExample socialLoginExample, String accessToken);

    public void onError(String message);
}
