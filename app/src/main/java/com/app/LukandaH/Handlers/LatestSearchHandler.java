package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.LatestSearchModels.LatestSearchExample;

public interface LatestSearchHandler {
    public void onSuccess(LatestSearchExample latestSearchExample);

    public void onError(String message);
}
