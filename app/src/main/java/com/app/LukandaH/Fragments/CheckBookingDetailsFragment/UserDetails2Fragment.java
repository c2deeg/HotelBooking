package com.app.LukandaH.Fragments.CheckBookingDetailsFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.FillBookingInfoFragment.UserDetailsFragment;
import com.app.LukandaH.Fragments.BookingAndPaymentFragment.PaymentFragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;


public class UserDetails2Fragment extends Fragment implements View.OnClickListener, IOnBackPressed {
    public static IOnBackPressed  backPressed;
    private TextView tv_nextBtn;
    private Activity activity;
    private View view;
    //    private String userrole;
    private ImageView img_back;
    private TextView tv_toMainScreen;
    private ImageView img_notification;
    private String rooms;
    private String adults;
    private String checkIn;
    private String checkOut;
    private String hotelId;
    private TextView tv_inDate;
    private TextView tv_noOfdayNight;
    private TextView tv_outDate;
    private TextView tv_noOfRoomsAndNights;
    private TextView tv_roomChargesBeforeDiscount;
    private TextView tv_offer;
    private TextView tv_priceAfterDiscounts;
    private TextView tv_payableAmount;
    private String hotelPrice;
    private String offer;
    private String totalPrice;
    int noOfDays = 1;
    private String name;
    private String email;
    private String mobNumber;
    private String primaryGuest;
    private String profie;
    private String saveUserInfo;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_details2, container, false);
        activity = getActivity();
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        email = bundle.getString("email");
        mobNumber = bundle.getString("mobNumber");
        primaryGuest = bundle.getString("primaryGuest");
        profie = bundle.getString("profie");
        saveUserInfo = bundle.getString("saveUserInfo");
        checkIn = bundle.getString("checkIn");
        checkOut = bundle.getString("checkOut");
        rooms = bundle.getString("rooms");
        adults = bundle.getString("adults");
        userId = bundle.getString("userId");
        hotelId = bundle.getString("hotelId");
        totalPrice = bundle.getString("totalPrice");
        hotelPrice = bundle.getString("hotelPrice");
        offer = bundle.getString("offer");

//        Toast.makeText(activity, rooms +"   "+ adults, Toast.LENGTH_LONG).show();
//        userrole = getArguments().getString("role");
        init();
        listeners();
        setDatesAndDays();
        setPriceData();
        return view;
    }

    private void init() {
        tv_nextBtn = view.findViewById(R.id.tv_nextBtn);
        img_back = view.findViewById(R.id.img_back);
        tv_toMainScreen = view.findViewById(R.id.tv_toMainScreen);
        img_notification = view.findViewById(R.id.img_notification);
        tv_inDate = view.findViewById(R.id.tv_inDate);
        tv_noOfdayNight = view.findViewById(R.id.tv_noOfdayNight);
        tv_outDate = view.findViewById(R.id.tv_outDate);
        tv_noOfRoomsAndNights = view.findViewById(R.id.tv_noOfRoomsAndNights);
        tv_roomChargesBeforeDiscount = view.findViewById(R.id.tv_roomChargesBeforeDiscount);
        tv_offer = view.findViewById(R.id.tv_offer);
        tv_priceAfterDiscounts = view.findViewById(R.id.tv_priceAfterDiscounts);
        tv_payableAmount = view.findViewById(R.id.tv_payableAmount);
    }

    private void listeners() {
        tv_nextBtn.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    private void setDatesAndDays(){
        String[] checkInlist = checkIn.split("-");
        String checkInYear = checkInlist[0];
        String checkInMonth = checkInlist[1].substring(0,3);
        String checkInDate = checkInlist[2];
        String checkInFinal = checkInDate + " " + checkInMonth + " " + checkInYear;

        String[] checkOutlist = checkOut.split("-");
        String checkOutYear = checkOutlist[0];
        String checkOutMonth = checkOutlist[1].substring(0,3);
        String checkOutDate = checkOutlist[2];
        String checkOutFinal = checkOutDate + " " + checkOutMonth + " " + checkOutYear;
        tv_inDate.setText(checkInFinal);
        tv_outDate.setText(checkOutFinal);
        int checkInDateInt = Integer.parseInt(checkInDate);
        int checkOutDateInt = Integer.parseInt(checkOutDate);
        if(checkInMonth.equals(checkOutMonth)){
            if(checkInDateInt == checkOutDateInt){
                noOfDays = 1;

            } else{
                noOfDays = checkOutDateInt - checkInDateInt;
            }
        } else{
            int daysInCheckInMonth = noOfDaysInMonth(checkInMonth);
            int daysInCheckOutMonth = noOfDaysInMonth(checkOutMonth);
            int outMonthy = daysInCheckOutMonth - checkOutDateInt;
            int daysInCheckOutMonthForBooking = daysInCheckOutMonth - outMonthy;
            int daysInCheckInMonthForBooking = daysInCheckInMonth - checkInDateInt;
            noOfDays = daysInCheckInMonthForBooking + daysInCheckOutMonthForBooking;
        }
        if(noOfDays == 1){
            tv_noOfdayNight.setText(noOfDays +" Night");
        } else{
            tv_noOfdayNight.setText(noOfDays +" Nights");
        }
    }
    @Override
    public void onClick(View view) {
        if (view == tv_nextBtn) {
            Fragment fragment = new PaymentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("email", email);
            bundle.putString("mobNumber", mobNumber);
            bundle.putString("primaryGuest", primaryGuest);
            bundle.putString("profie", profie);
            bundle.putString("saveUserInfo", saveUserInfo);
            bundle.putString("checkIn", checkIn);
            bundle.putString("checkOut", checkOut);
            bundle.putString("rooms", rooms);
            bundle.putString("adults", adults);
            bundle.putString("userId", userId);
            bundle.putString("hotelId", hotelId);
            bundle.putString("totalPrice", totalPrice);
            bundle.putString("hotelPrice", hotelPrice);
            bundle.putString("offer", offer);
            fragment.setArguments(bundle);
            Utils.changeBookingFragment(activity, fragment);
        } else if(view == tv_toMainScreen){
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        } else if(view == img_notification){
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        } else if(view == img_back){
            Fragment fragment = new UserDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("email", email);
            bundle.putString("mobNumber", mobNumber);
            bundle.putString("primaryGuest", primaryGuest);
            bundle.putString("profie", profie);
            bundle.putString("saveUserInfo", saveUserInfo);
            bundle.putString("checkIn", checkIn);
            bundle.putString("checkOut", checkOut);
            bundle.putString("rooms", rooms);
            bundle.putString("adults", adults);
            bundle.putString("userId", userId);
            bundle.putString("hotelId", hotelId);
            bundle.putString("totalPrice", totalPrice);
            bundle.putString("hotelPrice", hotelPrice);
            bundle.putString("offer", offer);
            fragment.setArguments(bundle);
            Utils.changeBookingFragment(activity, fragment);
        }
    }
    @Override
    public void onPause() {
        backPressed = null;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        backPressed = this;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = new UserDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("mobNumber", mobNumber);
        bundle.putString("primaryGuest", primaryGuest);
        bundle.putString("profie", profie);
        bundle.putString("saveUserInfo", saveUserInfo);
        bundle.putString("checkIn", checkIn);
        bundle.putString("checkOut", checkOut);
        bundle.putString("rooms", rooms);
        bundle.putString("adults", adults);
        bundle.putString("userId", userId);
        bundle.putString("hotelId", hotelId);
        bundle.putString("totalPrice", totalPrice);
        bundle.putString("hotelPrice", hotelPrice);
        bundle.putString("offer", offer);
        fragment.setArguments(bundle);
        Utils.changeBookingFragment(activity, fragment);
    }

    private void setPriceData(){
//        (1 room * 1 night)
        String room = "1";
        String typeRoom = "room";
        String nights = "1";
        String typeNights = "night";
        if(rooms.equals("1")){
            room = "1";
            typeRoom = " room";
        } else{
            room = rooms;
            typeRoom = " rooms";
        }
        if(String.valueOf(noOfDays).equals("1")){
            nights = "1";
            typeNights = " night";
        } else{
            nights = String.valueOf(noOfDays);
            typeNights = " nights";
        }
        tv_noOfRoomsAndNights.setText("("+room+typeRoom+" * "+nights+typeNights+")");
        tv_roomChargesBeforeDiscount.setText("$ "+ Integer.parseInt(room)*Integer.parseInt(hotelPrice)*Integer.parseInt(nights));
        tv_offer.setText(offer);
        tv_priceAfterDiscounts.setText("$ "+totalPrice);
        tv_payableAmount.setText("$ "+totalPrice);
    }

    private int noOfDaysInMonth(String month){
        int days = 30;
        if(month.equals("JAN")){
            return days = 30;
        } else if(month.equals("FEB")){
            return days = 28;
        }else if(month.equals("MAR")){
            return days = 31;
        }else if(month.equals("APR")){
            return days = 30;
        }else if(month.equals("MAY")){
            return days = 31;
        }else if(month.equals("JUN")){
            return days = 30;
        }else if(month.equals("JUL")){
            return days = 31;
        }else if(month.equals("AUG")){
            return days = 31;
        }else if(month.equals("SEP")){
            return days = 30;
        }else if(month.equals("OCT")){
            return days = 31;
        }else if(month.equals("NOV")){
            return days = 30;
        }else if(month.equals("DEC")){
            return days = 31;
        }
        return days;
    }
}