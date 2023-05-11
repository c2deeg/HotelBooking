package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.OtpVerifyModels.OtpVerifyExample;
import com.app.LukandaH.Models.SearchFilterModels.FilterExample;

public interface OtpVerifyHandler {
    public void onSuccess(OtpVerifyExample otpVerifyExample);

    public void onError(String message);
}
