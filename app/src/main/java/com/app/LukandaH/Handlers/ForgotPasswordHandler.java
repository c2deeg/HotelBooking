package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ForgotPasswordModels.ForgotPasswordExample;
import com.app.LukandaH.Models.SearchFilterModels.FilterExample;

public interface ForgotPasswordHandler {
    public void onSuccess(ForgotPasswordExample forgotPasswordExample);

    public void onError(String message);
}
