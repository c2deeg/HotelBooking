package com.app.LukandaH.Fragments.OtpFragment.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.app.LukandaH.Fragments.OtpFragment.OtpFragment;
import com.app.LukandaH.Fragments.OtpFragment.View.OtpVerifyView;
import com.app.LukandaH.Fragments.ResetPasswordFragment.ResetPasswordFragment;
import com.app.LukandaH.Handlers.ForgotPasswordHandler;
import com.app.LukandaH.Handlers.OtpVerifyHandler;
import com.app.LukandaH.Models.ForgotPasswordModels.ForgotPasswordExample;
import com.app.LukandaH.Models.OtpVerifyModels.OtpVerifyExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class OtpVerifyPersenter {
    private Activity activity;
    private OtpVerifyView otpVerifyView;
    private String otp;
    private String userrole;
    private String email;
    private String et_email;

    public OtpVerifyPersenter(Activity activity, OtpVerifyView otpVerifyView, String userrole, String email) {
        this.activity = activity;
        this.otpVerifyView = otpVerifyView;
        this.userrole = userrole;
        this.email = email;
    }
    public void otpVerifyMethod(String otp){
        this.otp = otp;
        String otpToken = CSPreferences.readString(activity, Utils.OTP_TOKEN);
        otpVerifyView.showDialog(activity);
        WebServices.getmInstance().otpVerifyMethod(otp.trim(), otpToken, new OtpVerifyHandler() {
            @Override
            public void onSuccess(OtpVerifyExample otpVerifyExample) {
                otpVerifyView.hideDialog();
                if(otpVerifyExample != null){
                    if(otpVerifyExample.getIsSuccess() == true){
                        otpVerifyView.showMessage(activity,otpVerifyExample.getMessage().getMessage());

                        Fragment fragment = new ResetPasswordFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("role", userrole);
                        bundle.putString("email", email);
                        fragment.setArguments(bundle);
                        Utils.changeLoginFragment(activity,fragment);
                    } else{
                        otpVerifyView.showMessage(activity,otpVerifyExample.getMessage().getMessage());
                    }
                } else{
                    otpVerifyView.showMessage(activity,otpVerifyExample.getMessage().getMessage());
                }
            }

            @Override
            public void onError(String message) {
                otpVerifyView.hideDialog();
                otpVerifyView.showMessage(activity, message);
            }
        });
    }

    public void otpResendMethod(String et_email) {
        this.et_email = et_email;
        otpVerifyView.showDialog(activity);
        WebServices.getmInstance().forgotPasswordMethod(et_email.trim(), new ForgotPasswordHandler() {
            @Override
            public void onSuccess(ForgotPasswordExample forgotPasswordExample) {
                otpVerifyView.hideDialog();
                if (forgotPasswordExample.getIsSuccess() != null) {
                    if (forgotPasswordExample.getIsSuccess() == true) {
                        CSPreferences.putString(activity, Utils.OTP_TOKEN, forgotPasswordExample.getMessage().getOtpToken().toString());
                        otpVerifyView.showMessage(activity, "otp resend successfully");
//                    Fragment fragment = new OtpFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("role", userrole);
//                    bundle.putString("email", et_email.getText().toString());
//                    fragment.setArguments(bundle);
//                    Utils.changeLoginFragment(activity,fragment);
                    } else {
                        otpVerifyView.showMessage(activity, forgotPasswordExample.getMessage().toString());
                    }
                } else {
                    otpVerifyView.showMessage(activity, forgotPasswordExample.getMessage().toString());
                }
            }

            @Override
            public void onError(String message) {
                otpVerifyView.hideDialog();
                otpVerifyView.showMessage(activity, message);
            }
        });
    }
}
