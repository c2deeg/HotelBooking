package com.app.LukandaH.Fragments.SignUpFragment.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.LukandaH.Fragments.LoginFragment.LoginFragment;
import com.app.LukandaH.Fragments.SignUpFragment.View.SignUpView;
import com.app.LukandaH.Handlers.SignUpHandler;
import com.app.LukandaH.Models.SignUpModels.SignUpExample;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class SignUpPresenter {
    private FragmentManager fragmentManager;
    private SignUpView signUpView;
    private Activity activity;
    private EditText et_name;
    private EditText et_email;
    private EditText et_Password;
    private EditText confirmPassword;
    private String deviceToken;
    private String phoneNo;
    private String userrole;

    public SignUpPresenter(Activity activity, SignUpView signUpView, FragmentManager fragmentManager) {
        this.activity = activity;
        this.signUpView = signUpView;
        this.fragmentManager = fragmentManager;
    }

    public void loginMethod(EditText et_name, EditText et_email, String phoneNo, EditText et_Password, EditText confirmPassword, String userrole) {
        this.et_name = et_name;
        this.et_email = et_email;
        this.phoneNo = phoneNo;
        this.et_Password = et_Password;
        this.confirmPassword = confirmPassword;
        this.userrole = userrole;
//        if (Utils.isNetworkConnected(activity)) {
        deviceToken = CSPreferences.readString(activity, Utils.DEVICETOKEN);

        if (checkValidation()) {
            signUpView.showDialog(activity);
            WebServices.getmInstance().signUpMethod(et_name.getText().toString().trim(), et_email.getText().toString().trim(), phoneNo.trim(), et_Password.getText().toString().trim(), deviceToken, new SignUpHandler() {
                @Override
                public void onSuccess(SignUpExample signUpExample, String accessToken) {
                    signUpView.hideDialog();
                    if (signUpExample != null) {
                        if (signUpExample.getIsSuccess() == true) {
                            signUpView.showMessage(activity, signUpExample.getMessage());
                            Fragment fragment = new LoginFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("role", userrole);
                            fragment.setArguments(bundle);
                            Utils.changeLoginFragment(activity,fragment);
                        } else {
                            signUpView.showMessage(activity, signUpExample.getMessage());
                        }
                    } else {
                        signUpView.showMessage(activity, signUpExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    signUpView.hideDialog();
                    signUpView.showMessage(activity, message);
                }
            });
        } else {
        }
    }

    private boolean checkValidation() {
        String email = et_email.getText().toString();
        String pswd = et_Password.getText().toString();
        String conPswd = confirmPassword.getText().toString();
        String name = et_name.getText().toString();
//        String regex = "^(.+)@(.+)$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(email);
        if (et_name.getText().length() == 0) {
            return false;
        } else if (et_email.getText().length() == 0) {
            Toast.makeText(activity, "Please enter email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(email.contains("@")) || !(email.contains(".")) || email.contains(" ")) {
            Toast.makeText(activity, "please enter correct email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.isEmpty() || pswd.isEmpty() || conPswd.isEmpty() || name.isEmpty()) {
            Toast.makeText(activity, "Enter required details", Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if (matcher.matches() == false) {
//            Toast.makeText(activity, "Please enter correct email address", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        else if (et_Password.getText().length() == 0) {
            Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (confirmPassword.length() == 0) {
            Toast.makeText(activity, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(pswd.equals(conPswd))) {
            Toast.makeText(activity, "Password and Confirm password \n\t\t\t\t\t\t\tmismatched", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(pswd.length() >= 8)) {
            Toast.makeText(activity, "Password should be 8 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}