package com.app.LukandaH.Fragments.BookedHotelFragment.Presenter;

import android.app.Activity;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Adapters.RvFinishedBookingsAdapter;
import com.app.LukandaH.Adapters.RvUpcomingHotelsAdapter;
import com.app.LukandaH.Fragments.BookedHotelFragment.View.BookHotelView;
import com.app.LukandaH.Handlers.CancelBookingHandler;
import com.app.LukandaH.Handlers.ChangeBookingHandler;
import com.app.LukandaH.Handlers.FinishedBookingHandler;
import com.app.LukandaH.Handlers.UpcomingBookingsHandler;
import com.app.LukandaH.Models.CancelBookingModels.CancelBookingExample;
import com.app.LukandaH.Models.ChangeBookingModels.ChangeBookingExample;
import com.app.LukandaH.Models.FinishedBookingModels.FinishedBookingExample;
import com.app.LukandaH.Models.UpcomingBookingsModels.UpcomingBookingsExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class BookHotelPresenter {
    private Activity activity;
    private BookHotelView bookHotelView;
    private RecyclerView rv_UpcomingBookings;
    private RecyclerView rv_finishedBookings;
    private RvUpcomingHotelsAdapter rvUpcomingHotelsAdapter;
    private String checkIn;
    private String checkOut;
    private int rooms;
    private int adults;
    private String hotelBookingId;
    BookHotelPresenter bookHotelPresenter;
    private RvFinishedBookingsAdapter rvFinishedBookingsAdapter;

    public BookHotelPresenter(Activity activity, BookHotelView bookHotelView) {
        this.activity = activity;
        this.bookHotelView = bookHotelView;
    }

    public void upcomingBookingsMethod(RecyclerView rv_UpcomingBookings){
        bookHotelPresenter = new BookHotelPresenter(activity,bookHotelView);
        this.rv_UpcomingBookings = rv_UpcomingBookings;
        String userId = CSPreferences.readString(activity, Utils.USERID);
        bookHotelView.showDialog(activity);
        WebServices.getmInstance().upcomingBookingsMethod(userId, new UpcomingBookingsHandler() {
            @Override
            public void onSuccess(UpcomingBookingsExample upcomingBookingsExample) {
                bookHotelView.hideDialog();
                if(upcomingBookingsExample != null){
                    if(upcomingBookingsExample.getIsSuccess() == true){
                        rvUpcomingHotelsAdapter = new RvUpcomingHotelsAdapter(activity, upcomingBookingsExample.getData(),bookHotelPresenter);
                        rv_UpcomingBookings.setHasFixedSize(true);
                        rv_UpcomingBookings.setLayoutManager(new LinearLayoutManager(activity));
                        rv_UpcomingBookings.setAdapter(rvUpcomingHotelsAdapter);
                    }else{}
                }else{
                }
            }

            @Override
            public void onError(String message) {
                bookHotelView.hideDialog();
                bookHotelView.showMessage(activity, message);
            }
        });
    }

    public void changeBookingMethod(String hotelBookingId, String checkIn, String checkOut, int rooms, int adults){
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.rooms = rooms;
        this.adults = adults;
        this.hotelBookingId = hotelBookingId;

        bookHotelView.showDialog(activity);
        WebServices.getmInstance().changeBookingMethod(hotelBookingId, checkIn, checkOut, rooms, adults, new ChangeBookingHandler() {
            @Override
            public void onSuccess(ChangeBookingExample changeBookingExample) {
                bookHotelView.hideDialog();
                if(changeBookingExample != null){
                    if(changeBookingExample.getIsSuccess() == true){
                        Toast.makeText(activity, changeBookingExample.getStatusCode().toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity,  changeBookingExample.getStatusCode().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else{
                    bookHotelView.showMessage(activity,changeBookingExample.getStatusCode().toString());
                }
            }

            @Override
            public void onError(String message) {
                bookHotelView.hideDialog();
                bookHotelView.showMessage(activity, message);
            }
        });
    }

    // Cancel Booking Method
    public void cancelBooking(String hotelBookingId){
        this.hotelBookingId = hotelBookingId;
        bookHotelView.showDialog(activity);
        WebServices.getmInstance().cancelBookingMethod(hotelBookingId, new CancelBookingHandler() {
            @Override
            public void onSuccess(CancelBookingExample cancelBookingExample) {
                bookHotelView.hideDialog();
                if(cancelBookingExample != null){
                    if(cancelBookingExample.getIsSuccess() == true){
                        bookHotelView.showMessage(activity,cancelBookingExample.getStatusCode().toString());
                    } else{
                        bookHotelView.showMessage(activity,cancelBookingExample.getStatusCode().toString());
                    }
                } else{
                    bookHotelView.showMessage(activity,cancelBookingExample.getStatusCode().toString());
                }
            }

            @Override
            public void onError(String message) {
                bookHotelView.hideDialog();
                bookHotelView.showMessage(activity,message);
            }
        });
    }

    // finished Booking Method
    public void finishedBookingMethod(RecyclerView rv_finishedBookings){
        this.rv_finishedBookings = rv_finishedBookings;
        String userId = CSPreferences.readString(activity, Utils.USERID);

//        bookHotelView.showDialog(activity);
        WebServices.getmInstance().finishedBookingMethod(userId, new FinishedBookingHandler() {
            @Override
            public void onSuccess(FinishedBookingExample finishedBookingExample) {
                bookHotelView.hideDialog();
                if(finishedBookingExample != null) {
                    if(finishedBookingExample.getIsSuccess() == true){
//                        bookHotelView.showMessage(activity, finishedBookingExample.getMessage());
                        rvFinishedBookingsAdapter = new RvFinishedBookingsAdapter(activity, finishedBookingExample.getData());
                        rv_finishedBookings.setHasFixedSize(true);
                        rv_finishedBookings.setLayoutManager(new LinearLayoutManager(activity));
                        rv_finishedBookings.setAdapter(rvFinishedBookingsAdapter);
                    } else{
                        bookHotelView.showMessage(activity, finishedBookingExample.getMessage());
                    }
                } else{
                    bookHotelView.showMessage(activity, finishedBookingExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                bookHotelView.hideDialog();
                bookHotelView.showMessage(activity, message);
            }
        });
    }
}
