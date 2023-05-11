package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.BookingModels.BookingHotelExample;
import com.app.LukandaH.Models.FillBookingInfoModels.FillBookingInfoExample;
import com.app.LukandaH.Models.SearchFilterModels.FilterExample;

public interface BookingHandler {
    public void onSuccess(BookingHotelExample bookingHotelExample);

    public void onError(String message);
}
