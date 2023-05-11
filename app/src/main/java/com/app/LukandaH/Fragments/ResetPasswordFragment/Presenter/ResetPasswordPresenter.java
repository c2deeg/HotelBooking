package com.app.LukandaH.Fragments.ResetPasswordFragment.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.app.LukandaH.Fragments.LoginFragment.LoginFragment;
import com.app.LukandaH.Fragments.ResetPasswordFragment.View.ResetPasswordView;
import com.app.LukandaH.Handlers.ResetPasswordHandler;
import com.app.LukandaH.Models.ResetPasswordModels.ResetPasswordExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class ResetPasswordPresenter {
    private final Activity activity;
    private final ResetPasswordView resetPasswordView;
    private EditText et_newPassword;
    private EditText et_confirmNewPassword;
    private final String userrole;
    private String email;

    public ResetPasswordPresenter(Activity activity, ResetPasswordView resetPasswordView, String userrole) {
        this.activity = activity;
        this.resetPasswordView = resetPasswordView;
        this.userrole = userrole;
    }

    public void resetPasswordMethod(EditText et_newPassword, EditText et_confirmNewPassword, String email) {
        this.et_newPassword = et_newPassword;
        this.et_confirmNewPassword = et_confirmNewPassword;
        this.email = email;
        String otpToken = CSPreferences.readString(activity, Utils.OTP_TOKEN);

        if (checkValidation()) {
            resetPasswordView.showDialog(activity);
            WebServices.getmInstance().resetPasswordMethod(otpToken, email, et_newPassword.getText().toString(), new ResetPasswordHandler() {
                @Override
                public void onSuccess(ResetPasswordExample resetPasswordExample) {
                    resetPasswordView.hideDialog();
                    if (resetPasswordExample != null) {
                        if (resetPasswordExample.getIsSuccess() == true) {
                            resetPasswordView.showMessage(activity, resetPasswordExample.getMessage());
                            Fragment fragment = new LoginFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("role", userrole);
                            fragment.setArguments(bundle);
                            Utils.changeLoginFragment(activity, fragment);
                        } else {
                            resetPasswordView.showMessage(activity, resetPasswordExample.getMessage());
                        }
                    } else {
                        resetPasswordView.showMessage(activity, resetPasswordExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    resetPasswordView.hideDialog();
                    resetPasswordView.showMessage(activity, message);
                }
            });
        } else {
        }
    }

    private boolean checkValidation() {
        String newPassword = et_newPassword.getText().toString();
        String confirmNewPassword = et_confirmNewPassword.getText().toString();
        if (newPassword.isEmpty() && confirmNewPassword.isEmpty()) {
            Utils.showMessage(activity, "please fill required fields");
            return false;
        } else if (!(newPassword.equals(confirmNewPassword))) {
            Utils.showMessage(activity, "new password and confirm\nnew password not matched");
            return false;
        }
        return true;
    }
}
