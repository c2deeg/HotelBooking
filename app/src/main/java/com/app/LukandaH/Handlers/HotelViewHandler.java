package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.HotelViewModels.HotelViewExample;

public interface HotelViewHandler {
    public void onSuccess(HotelViewExample hotelViewExample, String accessToken);

    public void onError(String message);
}
