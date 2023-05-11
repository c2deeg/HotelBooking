package com.app.LukandaH.Fragments.ForgotPasswordFragment.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.app.LukandaH.Fragments.ForgotPasswordFragment.View.ForgotPasswordView;
import com.app.LukandaH.Fragments.OtpFragment.OtpFragment;
import com.app.LukandaH.Handlers.ForgotPasswordHandler;
import com.app.LukandaH.Models.ForgotPasswordModels.ForgotPasswordExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class ForgotPasswordPresenter {
    private Activity activity;
    private ForgotPasswordView forgotPasswordView;
    private EditText et_email;
    private String userrole;


    public ForgotPasswordPresenter(Activity activity, ForgotPasswordView forgotPasswordView, String userrole) {
        this.activity = activity;
        this.forgotPasswordView = forgotPasswordView;
        this.userrole = userrole;
    }

    public void forgotPasswordMethod(EditText et_email){
        this.et_email = et_email;

        if(checkValidation()){
            forgotPasswordView.showDialog(activity);
            WebServices.getmInstance().forgotPasswordMethod(et_email.getText().toString().trim(), new ForgotPasswordHandler() {
                @Override
                public void onSuccess(ForgotPasswordExample forgotPasswordExample) {
                    forgotPasswordView.hideDialog();
                    if(forgotPasswordExample.getIsSuccess() != null){
                        if(forgotPasswordExample.getIsSuccess() == true){
                            CSPreferences.putString(activity, Utils.OTP_TOKEN, forgotPasswordExample.getMessage().getOtpToken().toString());
                            forgotPasswordView.showMessage(activity,"otp sent successfully");
                            Fragment fragment = new OtpFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("role", userrole);
                            bundle.putString("email", et_email.getText().toString());
                            fragment.setArguments(bundle);
                            Utils.changeLoginFragment(activity,fragment);
                        }else {
                            forgotPasswordView.showMessage(activity,forgotPasswordExample.getMessage().toString());
                        }
                    } else {
                        forgotPasswordView.showMessage(activity,forgotPasswordExample.getMessage().toString());
                    }
                }

                @Override
                public void onError(String message) {
                    forgotPasswordView.hideDialog();
                    forgotPasswordView.showMessage(activity, message);
                }
            });
        }
        else{

        }
    }

    private boolean checkValidation(){
        String strEmail = et_email.getText().toString();
        if(strEmail.isEmpty()) {
            Utils.showMessage(activity, "email is required");
            return false;
        }
        else if(!(strEmail.contains("@")) || !(strEmail.contains(".")) || strEmail.contains(" ")){
            Utils.showMessage(activity,"Please enter correctg email address");
            return false;
        }
        return true;
    }
}
