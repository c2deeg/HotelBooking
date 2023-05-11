package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ProfileUpdateModels.ProfileUpdateExample;
import com.app.LukandaH.Models.ResetPasswordModels.ResetPasswordExample;

public interface ResetPasswordHandler {
    public void onSuccess(ResetPasswordExample resetPasswordExample);

    public void onError(String message);
}
