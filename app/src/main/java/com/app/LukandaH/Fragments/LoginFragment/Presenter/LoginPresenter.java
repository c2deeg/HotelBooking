package com.app.LukandaH.Fragments.LoginFragment.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Fragments.LoginFragment.View.LoginView;
import com.app.LukandaH.Handlers.LoginHandler;
import com.app.LukandaH.Handlers.SocialLoginHandler;
import com.app.LukandaH.Models.LoginModels.LoginExample;
import com.app.LukandaH.Models.SocialLoginModels.SocialLoginExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class LoginPresenter {
    private LoginView loginView;
    private Activity activity;
    private EditText et_email;
    private EditText et_Password;
    private String deviceToken;
    private String socialLoginId;
    private String socialLoginPlatform;
    private String socialLoginName;
    private String socialLoginGender;
//    private String userrole;

    public LoginPresenter(Activity activity, LoginView loginView) {
        this.activity = activity;
        this.loginView = loginView;
    }

    public void loginMethod(EditText et_email, EditText et_Password) {
        this.et_email = et_email;
        this.et_Password = et_Password;
//        this.userrole = userrole;
        deviceToken = CSPreferences.readString(activity, Utils.DEVICETOKEN);
        if (checkValidation()) {
            loginView.showDialog(activity);
            WebServices.getmInstance().loginMethod(et_email.getText().toString().trim(), et_Password.getText().toString().trim(), deviceToken, new LoginHandler() {
                @Override
                public void onSuccess(LoginExample loginExample, String accessToken) {
                    loginView.hideDialog();
                    if (loginExample != null) {
                        if (loginExample.getIsSuccess() == true) {
                            CSPreferences.putString(activity, Utils.LOGINSTATUS, "true");
                            CSPreferences.putString(activity, Utils.LOGINTOKEN, loginExample.getData().getToken());
                            CSPreferences.putString(activity, Utils.USERID, loginExample.getData().getId());
                            CSPreferences.putString(activity, Utils.USERNAME, loginExample.getData().getName());
                            loginView.showMessage(activity, loginExample.getMessage());
                            Intent intent1 = new Intent(activity, HomeActivity.class);
//                        intent1.putExtra("role", userrole);
                            activity.startActivity(intent1);
                        } else {
                            loginView.showMessage(activity, loginExample.getMessage());
                        }
                    } else {
                        loginView.showMessage(activity, loginExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    loginView.hideDialog();
                    loginView.showMessage(activity, message);
                }
            });
        } else {
        }
    }

    public void SocialLoginMethod(String socialLoginId, String socialLoginPlatform, String socialLoginName, String socialLoginGender) {
        this.socialLoginId = socialLoginId;
        this.socialLoginPlatform = socialLoginPlatform;
        this.socialLoginName = socialLoginName;
        this.socialLoginGender = socialLoginGender;
//        deviceToken = CSPreferences.readString(activity, Utils.DEVICETOKEN);
            loginView.showDialog(activity);
            WebServices.getmInstance().socailLoginMethod(socialLoginId, socialLoginPlatform.trim(), socialLoginName, socialLoginGender.trim(), new SocialLoginHandler() {
                @Override
                public void onSuccess(SocialLoginExample socialLoginExample, String accessToken) {
                    loginView.hideDialog();
                    if (socialLoginExample != null) {
                        if (socialLoginExample.getIsSuccess() == true) {
                            CSPreferences.putString(activity, Utils.LOGINSTATUS, "true");
                            CSPreferences.putString(activity, Utils.LOGINTOKEN, accessToken);
                            CSPreferences.putString(activity, Utils.USERID, socialLoginExample.getData().getId());
                            loginView.showMessage(activity, socialLoginExample.getMessage());
                            Intent intent1 = new Intent(activity, HomeActivity.class);
//                        intent1.putExtra("role", userrole);
                            activity.startActivity(intent1);
                        } else {
                            loginView.showMessage(activity, socialLoginExample.getMessage());
                        }
                    } else {
                        loginView.showMessage(activity, socialLoginExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    loginView.hideDialog();
                    loginView.showMessage(activity, message);
                }
            });

    }

    private boolean checkValidation() {
        String email = et_email.getText().toString();
        String password = et_Password.getText().toString();
        if (email.isEmpty()) {
            Utils.showMessage(activity, "email is required");
            return false;
        } else if (password.isEmpty()) {
            Utils.showMessage(activity, "password is required");
            return false;
        } else if (!(email.contains("@")) || !(email.contains(".")) || email.contains(" ")) {
            Utils.showMessage(activity, "Please enter a valid email address");
            return false;
        }
        return true;
    }


}