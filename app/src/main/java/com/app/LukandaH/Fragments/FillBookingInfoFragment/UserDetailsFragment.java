package com.app.LukandaH.Fragments.FillBookingInfoFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.HotelViewActivity.HotelViewActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.CheckBookingDetailsFragment.UserDetails2Fragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.hbb20.CountryCodePicker;

public class UserDetailsFragment extends Fragment implements View.OnClickListener, IOnBackPressed {
    public static IOnBackPressed backPressed;
    private Activity activity;
    private View view;
    private TextView tv_personal;
    private TextView tv_buisness;
    private TextView tv_nextBtn;
    private String black = "#FF000000";
    private String white = "#FFFFFFFF";
    private EditText et_name;
    private EditText et_email;
    private EditText et_mobNumber;
    private EditText et_primaryGuestName;
    private CountryCodePicker ccpicker;
    private String profie = "personal";
    private ImageView img_back;
    private TextView tv_toMainScreen;
    private ImageView img_notification;
    private SwitchCompat switchSaveUserInfo;
    private String rooms;
    private String adults;
    private String checkIn;
    private String checkOut;
    private String hotelId;
    private String userId;
    private String totalPrice;
    private String hotelPrice;
    private String offer;
    private TextView tv_totalPrice;

    //    private String userrole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_details, container, false);
        activity = getActivity();
        Bundle bundle = getArguments();
        rooms = bundle.getString("rooms");
        adults = bundle.getString("adults");
        checkIn = bundle.getString("checkIn");
        checkOut = bundle.getString("checkOut");
        hotelId = bundle.getString("hotelid");
        totalPrice = bundle.getString("totalPrice");
        hotelPrice = bundle.getString("hotelPrice");
        offer = bundle.getString("offer");
        userId = CSPreferences.readString(activity, Utils.USERID);
//        userrole = getArguments().getString("role");
        init();
        listeners();
        tv_totalPrice.setText("$ "+totalPrice);
        return view;
    }

    private void init() {
        tv_personal = view.findViewById(R.id.tv_personal);
        tv_buisness = view.findViewById(R.id.tv_buisness);
        tv_nextBtn = view.findViewById(R.id.tv_nextBtn);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_mobNumber = view.findViewById(R.id.et_mobNumber);
        et_primaryGuestName = view.findViewById(R.id.et_primaryGuestName);
        ccpicker = view.findViewById(R.id.ccpicker);
        img_back = view.findViewById(R.id.img_back);
        tv_toMainScreen = view.findViewById(R.id.tv_toMainScreen);
        img_notification = view.findViewById(R.id.img_notification);
        switchSaveUserInfo = view.findViewById(R.id.switchSaveUserInfo);
        tv_totalPrice = view.findViewById(R.id.tv_totalPrice);
    }

    private void listeners() {
        tv_personal.setOnClickListener(this);
        tv_buisness.setOnClickListener(this);
        tv_nextBtn.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tv_personal) {
            tv_personal.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_black_radius));
            tv_personal.setTextColor(Color.parseColor(white));
            tv_buisness.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_black25_radius));
            tv_buisness.setTextColor(Color.parseColor(black));
            profie = "personal";
        } else if (view == tv_buisness) {
            tv_buisness.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_black_radius));
            tv_buisness.setTextColor(Color.parseColor(white));
            tv_personal.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_black25_radius));
            tv_personal.setTextColor(Color.parseColor(black));
            profie = "business";
        } else if (view == tv_nextBtn) {
            UserDetails2Fragment fragment = new UserDetails2Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", et_name.getText().toString());
            bundle.putString("email", et_email.getText().toString());
            bundle.putString("mobNumber", ccpicker.getSelectedCountryCode()+et_mobNumber.getText().toString());
            bundle.putString("primaryGuest", et_primaryGuestName.getText().toString().trim());
            bundle.putString("profie", profie);
            bundle.putString("saveUserInfo", String.valueOf(switchSaveUserInfo.isChecked()));
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
        } else if(view == img_back) {
            Intent intent = new Intent(activity, HotelViewActivity.class);
            intent.putExtra("hotelid", hotelId);
            startActivity(intent);
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
        Intent intent = new Intent(activity, HotelViewActivity.class);
        intent.putExtra("hotelId", hotelId);
        startActivity(intent);
    }
}