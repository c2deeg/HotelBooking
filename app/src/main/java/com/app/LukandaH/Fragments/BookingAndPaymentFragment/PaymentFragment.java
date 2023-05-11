package com.app.LukandaH.Fragments.BookingAndPaymentFragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.BookingAndPaymentFragment.Presenter.BookingPresenter;
import com.app.LukandaH.Fragments.BookingAndPaymentFragment.View.BookingView;
import com.app.LukandaH.Fragments.CheckBookingDetailsFragment.UserDetails2Fragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.CardParams;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardMultilineWidget;

import java.util.Locale;

public class PaymentFragment extends Fragment implements View.OnClickListener, IOnBackPressed, BookingView {
    public static IOnBackPressed backPressed;
    private Activity activity;
    private View view;
    private TextView tv_payNow;
    private ImageView img_back;
    private TextView tv_toMainScreen;
    private ImageView img_notification;
    private String rooms;
    private String adults;
    private String checkIn;
    private String checkOut;
    private String hotelId;
    private CardMultilineWidget cardMultiLineWidget;
    private String name;
    private String email;
    private String mobNumber;
    private String primaryGuest;
    private String profie;
    private String saveUserInfo;
    private String userId;
    private String totalPrice;
    private String hotelPrice;
    private String offer;
    private boolean saveInfoB;
    private BookingPresenter bookingPresenter;
    private String stripeToken;
        //    private String userrole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment, container, false);
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
//        userrole = getArguments().getString("role");
        saveInfoB = Boolean.parseBoolean(saveUserInfo);

        bookingPresenter = new BookingPresenter(activity, this);
        init();
        listeners();



        return view;
    }

    private void init() {
        tv_payNow = view.findViewById(R.id.tv_payNow);
        img_back = view.findViewById(R.id.img_back);
        tv_toMainScreen = view.findViewById(R.id.tv_toMainScreen);
        img_notification = view.findViewById(R.id.img_notification);
        cardMultiLineWidget = view.findViewById(R.id.cardMultiLineWidget);


    }

    private void listeners() {
        tv_payNow.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tv_payNow) {
            savecard();

            bookingPresenter.bookingMethod(name,email,mobNumber,primaryGuest,profie,saveInfoB,checkIn,checkOut,Integer.parseInt(rooms),Integer.parseInt(adults),userId,hotelId,stripeToken,Integer.parseInt(totalPrice));


//            Intent intent = new Intent(activity, BookingSucessfulActivity.class);
////            intent.putExtra("role", userrole);
//            activity.startActivity(intent);
        } else if(view == tv_toMainScreen){
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        } else if(view == img_notification){
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        } else if(view == img_back){
            Fragment fragment = new UserDetails2Fragment();
            Bundle bundle = new Bundle();
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
        Fragment fragment = new UserDetails2Fragment();
        Bundle bundle = new Bundle();
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
        fragment.setArguments(bundle);
        Utils.changeBookingFragment(activity, fragment);
    }



    private void savecard() {
        CardParams card = cardMultiLineWidget.getCardParams();
        if (card == null) {
            Toast.makeText(getApplicationContext(), "Invalid card", Toast.LENGTH_SHORT).show();
        } else {
            if (!cardMultiLineWidget.getCardNumberEditText().isCardNumberValid()) {
                // Do not continue token creation.
                Toast.makeText(getApplicationContext(), "Invalid card", Toast.LENGTH_SHORT).show();
            } else {
                CreateToken(card);
            }
        }
    }

    private void CreateToken(CardParams card) {
        Stripe stripe = new Stripe(getApplicationContext(), getString(R.string.publishablekey));
        stripe.createCardToken(card, new ApiResultCallback<Token>() {
            @Override
            public void onSuccess(@NonNull Token token) {
//                Toast.makeText(activity,  token.getId().toString(), Toast.LENGTH_SHORT).show();
                Log.e("Stripe Token", token.getId());
//                        Intent intent = new Intent();
//                        intent.putExtra("card", token.getCard().getLast4());
//                        intent.putExtra("stripe_token", token.getId());
//                        intent.putExtra("cardtype", token.getCard().getBrand());
//
//                        setResult(0077, intent);
//                        finish();
                stripeToken = token.getId().toString();
            }

            @Override
            public void onError(@NonNull Exception e) {
                // Show localized error message
                Toast.makeText(getApplicationContext(),
                        e.getLocalizedMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity, msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }
}