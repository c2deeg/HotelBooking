package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.FinishedBookingModels.FinishedBookingExample;
import com.app.LukandaH.Models.SearchFilterModels.FilterExample;

public interface FinishedBookingHandler {
    public void onSuccess(FinishedBookingExample finishedBookingExample);

    public void onError(String message);
}
