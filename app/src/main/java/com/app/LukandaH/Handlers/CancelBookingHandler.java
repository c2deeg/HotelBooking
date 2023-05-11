package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.CancelBookingModels.CancelBookingExample;
import com.app.LukandaH.Models.ChangeBookingModels.ChangeBookingExample;

public interface CancelBookingHandler {
    public void onSuccess(CancelBookingExample cancelBookingExample);

    public void onError(String message);
}
