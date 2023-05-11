package com.app.LukandaH.Fragments.BookingAndPaymentFragment.Presenter;

import android.app.Activity;
import android.content.Intent;

import com.app.LukandaH.Activities.BookingSucessfulActivity;
import com.app.LukandaH.Fragments.BookingAndPaymentFragment.View.BookingView;
import com.app.LukandaH.Handlers.BookingHandler;
import com.app.LukandaH.Models.BookingModels.BookingHotelExample;
import com.app.LukandaH.Utils.WebServices;

public class BookingPresenter {
    private final Activity activity;
    private final BookingView fillBookingInfoView;
    private String et_name;
    private String et_email;
    private String mobNumber;
    private String et_primaryGuestName;
    private String travelPurpose;
    private boolean saveuserinfo;
    private String checkIn;
    private String checkout;
    private int rooms;
    private int adults;
    private String userId;
    private String hotelid;
    private String stripeToken;
    private int amount;


    public BookingPresenter(Activity activity, BookingView fillBookingInfoView) {
        this.activity = activity;
        this.fillBookingInfoView = fillBookingInfoView;
    }

    public void bookingMethod(String et_name, String et_email, String mobNumber, String et_primaryGuestName,
                              String travelPurpose, boolean saveuserinfo, String checkIn, String checkout, int rooms,
                              int adults, String userId, String hotelid, String stripeToken, int amount) {
        this.et_name = et_name;
        this.et_email = et_email;
        this.mobNumber = mobNumber;
        this.et_primaryGuestName = et_primaryGuestName;
        this.travelPurpose = travelPurpose;
        this.saveuserinfo = saveuserinfo;
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.rooms = rooms;
        this.adults = adults;
        this.userId = userId;
        this.hotelid = hotelid;
        this.stripeToken = stripeToken;
        this.amount = amount;

        fillBookingInfoView.showDialog(activity);
        WebServices.getmInstance().bookingMethod(et_name.trim(), et_email.trim(),
                mobNumber, et_primaryGuestName.trim(), travelPurpose.trim(), saveuserinfo, checkIn, checkout,
                rooms, adults, userId, hotelid, stripeToken, amount, new BookingHandler() {
                    @Override
                    public void onSuccess(BookingHotelExample bookingHotelExample) {
                        fillBookingInfoView.hideDialog();
                        if (bookingHotelExample != null) {
                            if (bookingHotelExample.getIsSuccess() == true) {
                                fillBookingInfoView.showMessage(activity, bookingHotelExample.getMessage());
                                Intent intent = new Intent(activity, BookingSucessfulActivity.class);
//            intent.putExtra("role", userrole);
                                activity.startActivity(intent);
                            } else {
                                fillBookingInfoView.showMessage(activity, bookingHotelExample.getMessage());
                            }
                        } else {
                            fillBookingInfoView.showMessage(activity, bookingHotelExample.getMessage());
                        }
                    }

                    @Override
                    public void onError(String message) {
                        fillBookingInfoView.hideDialog();
                        fillBookingInfoView.showMessage(activity, message);
                    }
                });
    }
}