package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.GetHotelsModels.GetMeHotelsExample;

public interface GetHotelsHandler {
    public void onSuccess(GetMeHotelsExample getMeHotelsExample, String accessToken);

    public void onError(String message);
}
