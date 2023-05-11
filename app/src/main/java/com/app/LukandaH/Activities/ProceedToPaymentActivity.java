package com.app.LukandaH.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Fragments.FillBookingInfoFragment.UserDetailsFragment;
import com.app.LukandaH.Fragments.BookingAndPaymentFragment.PaymentFragment;
import com.app.LukandaH.Fragments.CheckBookingDetailsFragment.UserDetails2Fragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class ProceedToPaymentActivity extends AppCompatActivity {
    private Activity activity;
    private FrameLayout fl_pToPayment;
    private String rooms;
    private String adults;
    private String checkIn;
    private String checkOut;
    private String hotelId;
    private String totalPrice;
    private String hotelPrice;
    private String offer;

    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed_to_payment);
//        userrole = getIntent().getStringExtra("role");
        activity = this;
        hotelId = getIntent().getStringExtra("hotelId");
        rooms = getIntent().getStringExtra("rooms");
        adults = getIntent().getStringExtra("adults");
        checkIn = getIntent().getStringExtra("checkIn");
        checkOut = getIntent().getStringExtra("checkOut");
        totalPrice = getIntent().getStringExtra("totalPrice");
        hotelPrice = getIntent().getStringExtra("hotelPrice");
        offer = getIntent().getStringExtra("offer");

        init();

        Fragment fragment = new UserDetailsFragment();
        Bundle bundle = new Bundle();
//        bundle.putString("role", userrole);
        bundle.putString("rooms", rooms);
        bundle.putString("adults", adults);
        bundle.putString("checkOut", checkOut);
        bundle.putString("checkIn", checkIn);
        bundle.putString("hotelid", hotelId);
        bundle.putString("totalPrice", totalPrice);
        bundle.putString("hotelPrice", hotelPrice);
        bundle.putString("offer", offer);
        fragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction().add(R.id.fl_pToPayment, fragment).commit();
        Utils.changeBookingFragment(activity, fragment);
    }

    private void init() {
        fl_pToPayment = findViewById(R.id.fl_pToPayment);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameContainer);
        if (UserDetailsFragment.backPressed != null) {
            UserDetailsFragment.backPressed.onBackPressed();
        } else if (UserDetails2Fragment.backPressed != null) {
            UserDetails2Fragment.backPressed.onBackPressed();
        } else if (PaymentFragment.backPressed != null) {
            PaymentFragment.backPressed.onBackPressed();
        }
//        finishAffinity();
    }

}