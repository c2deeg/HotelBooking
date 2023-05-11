package com.app.LukandaH.Fragments.ChangePasswordFragment.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Fragments.ChangePasswordFragment.View.ChangePasswordView;
import com.app.LukandaH.Handlers.ChangePasswordHandler;
import com.app.LukandaH.Models.ChangePasswordModels.ChangePasswordExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class ChangePasswordPresenter {
    private Activity activity;
    private ChangePasswordView changePasswordView;
    private EditText currentPasword;
    private EditText newPasword;
    private EditText conNewPasword;
    private String loginToken;
    private String id;

    public ChangePasswordPresenter(Activity activity, ChangePasswordView changePasswordView) {
        this.activity = activity;
        this.changePasswordView = changePasswordView;
    }

    public void changePasswordMethod(EditText currentPasword, EditText newPasword, EditText conNewPasword) {
        this.currentPasword = currentPasword;
        this.newPasword = newPasword;
        this.conNewPasword = conNewPasword;

        loginToken = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        id = CSPreferences.readString(activity, Utils.USERID);

        if (checkValidation()) {
            changePasswordView.showDialog(activity);
            WebServices.getmInstance().changePasswordMethod(loginToken, id, currentPasword.getText().toString().trim(), conNewPasword.getText().toString().trim(), new ChangePasswordHandler() {
                @Override
                public void onSuccess(ChangePasswordExample changePasswordExample, String accessToken) {
                    changePasswordView.hideDialog();
                    if (changePasswordExample != null) {
                        if (changePasswordExample.getIsSuccess() == true) {
                            changePasswordView.showMessage(activity, changePasswordExample.getMessage());
                            Intent intent = new Intent(activity, HomeActivity.class);
                            activity.startActivity(intent);
                        } else {
                            changePasswordView.showMessage(activity, changePasswordExample.getMessage());
                        }
                    } else {
                        changePasswordView.showMessage(activity, changePasswordExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    changePasswordView.hideDialog();
                    changePasswordView.showMessage(activity, message);
                }
            });
        } else {
        }
    }

    private boolean checkValidation() {
        String pswd = newPasword.getText().toString();
        String conPswd = conNewPasword.getText().toString();
        if (currentPasword.getText().length() == 0) {
//            Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (pswd.length() == 0) {
            Toast.makeText(activity, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (conPswd.trim().length() == 0) {
            return false;
        } else if (!(pswd.equals(conPswd))) {
            Toast.makeText(activity, "new Password and Confirm password \n\t\t\t\t\t\tmismatched", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
