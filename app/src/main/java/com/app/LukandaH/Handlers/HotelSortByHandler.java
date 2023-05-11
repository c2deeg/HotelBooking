package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ChangeBookingModels.ChangeBookingExample;
import com.app.LukandaH.Models.HotelsSortByModels.HotelSortByExample;

public interface HotelSortByHandler {
    public void onSuccess(HotelSortByExample hotelSortByExample);

    public void onError(String message);
}
