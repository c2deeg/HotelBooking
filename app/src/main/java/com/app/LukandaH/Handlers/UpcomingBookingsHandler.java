package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.SocialLoginModels.SocialLoginExample;
import com.app.LukandaH.Models.UpcomingBookingsModels.UpcomingBookingsExample;

public interface UpcomingBookingsHandler {
    public void onSuccess(UpcomingBookingsExample upcomingBookingsExample);

    public void onError(String message);
}
