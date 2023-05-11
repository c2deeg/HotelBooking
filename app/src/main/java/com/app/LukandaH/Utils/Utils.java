package com.app.LukandaH.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.app.LukandaH.R;

public class Utils {

    public static final String USERLOGIN = "user_login";
    public static final String USERNAME = "username";
    public static final String USEREMAIL = "useremail";
    public static final String USERPHOTO = "userphoto";
    public static final String LOGINTYPE = "logintype";
    public static final String USERID = "userid";
    public static final String HOTEL_ID = "";
    public static final String LATITUDE = "";
    public static final String LONGITUDE = "";
    public static final String TOKEN = "token";
    public static final String LOGINTOKEN = "logintoken";
    public static final String DEVICETOKEN = "deviceToken";
    public static final String HEADERSTOKEN = "headerstoken";
    public static final String CONTACTNUMBER = "contactNumber";
    public static final String ADDRESS = "address";
    public static final String CHECK_IN_DATE = "checkInDate";
    public static final String CHECK_OUT_DATE = "checkOutDate";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";
    public static final String CATEGORIESID1 = "categoriesid1";
    public static final String CATEGORIESID2 = "categoriesid2";
    public static final String GETFULLADDRESS = "fulladdress";
    public static final String GETADDRESS = "getaddress";
    public static final String GETSTATE = "getstate";
    public static final String GETADDRESSID = "getaddressid";
    public static final String GETCITY = "getcity";
    public static final String GENDERSELECT = "genderSelect";
    public static final String NAME = "name";
    public static final String ABOUTME = "aboutme";
    public static final String HOTEL_BOOKING_ID = "hotelbookingId";
    public static final String LOGINTHROUGH = "Loginthrough";
    public static final String ANOTHERUSERID = "anotheruserid";
    public static final String FORGOTPASSWORDTOKEN = "forgotpasswordtoken";
    public static final String PROFILEIDANOTHERUSER = "profileidanotheruser";
    public static final String ImageBaseURL = "http://13.54.226.124/";
    public static final String BOOLEANVALUE = "booleanvalue";
    public static final String OTP_TOKEN = "otptoken";
    public static final String URL_CHAT_SERVER = "http://3.136.56.91:8001";
    public static final String FACEBOOKTOKEN = "facebooktoken";
    public static final String LOGINSTATUS = "loginstatus";
    private static ProgressDialog progressDialog;
    private Activity activity;
    private String[] text;
    private String[] text2;

    public static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    // for keyboard hide
    public static void hideKeyboardFrom(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showDialogMethod(Activity activity, FragmentManager supportFragmentManager) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Wait while loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public static void hideDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public static void showMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    //camra
    public static boolean hasFeatureCamera(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public static void changeFragment(Context context, androidx.fragment.app.Fragment fragment) {

        androidx.fragment.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_editProfileContainer, fragment).addToBackStack(null).commitAllowingStateLoss();

    }
    public static void changeLoginFragment(Context context, androidx.fragment.app.Fragment fragment) {

        androidx.fragment.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).addToBackStack(null).commitAllowingStateLoss();

    }

    public static void changeBookingFragment(Context context, androidx.fragment.app.Fragment fragment) {

        androidx.fragment.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_pToPayment, fragment).addToBackStack(null).commitAllowingStateLoss();

    }



//
//    public static void changeTabFragment(Context context, androidx.fragment.app.Fragment fragment) {
//
//        androidx.fragment.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commitAllowingStateLoss();
//
//    }

//    public static void subscriptionDialog(Context context, String[] text, String[] text1) {
//        DialogPagerAdapter pagerAdapter = new DialogPagerAdapter(context, text, text1);
//
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.customdialog);
//
//
//        ViewPager viewPager = (ViewPager) dialog.findViewById(R.id.viewpager);
//        CircleIndicator indicator = (CircleIndicator) dialog.findViewById(R.id.indicator);
//        viewPager.setAdapter(pagerAdapter);
//        indicator.setViewPager(viewPager);
//
//        ImageView img_onemonth = (ImageView) dialog.findViewById(R.id.img_onemonth);
//        img_onemonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        ImageView img_sixmonth = (ImageView) dialog.findViewById(R.id.img_sixmonth);
//        img_sixmonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        ImageView imageView = (ImageView) dialog.findViewById(R.id.img_twelvemonth);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.btn_continue);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//
//    }

}
