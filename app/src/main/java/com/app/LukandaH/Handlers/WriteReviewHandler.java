package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.UpcomingBookingsModels.UpcomingBookingsExample;
import com.app.LukandaH.Models.WriteReviewModels.WriteReviewExample;

public interface WriteReviewHandler {
    public void onSuccess(WriteReviewExample writeReviewExample);

    public void onError(String message);
}
