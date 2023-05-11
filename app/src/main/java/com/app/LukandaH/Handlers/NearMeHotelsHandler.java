package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.GetHotelsModels.GetMeHotelsExample;
import com.app.LukandaH.Models.NearMeHotelsModel.NearMeHotelsExample;

public interface NearMeHotelsHandler {
    public void onSuccess(NearMeHotelsExample nearMeHotelsExample);

    public void onError(String message);
}
