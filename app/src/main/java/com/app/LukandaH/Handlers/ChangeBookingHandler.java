package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ChangeBookingModels.ChangeBookingExample;
import com.app.LukandaH.Models.ChangePasswordModels.ChangePasswordExample;

public interface ChangeBookingHandler {
    public void onSuccess(ChangeBookingExample changeBookingExample);

    public void onError(String message);
}
