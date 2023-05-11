package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.GetRatingsModels.GetRatingsExample;
import com.app.LukandaH.Models.LatestSearchModels.LatestSearchExample;

public interface GetRatingsHandler {
    public void onSuccess(GetRatingsExample getRatingsExample);

    public void onError(String message);
}
