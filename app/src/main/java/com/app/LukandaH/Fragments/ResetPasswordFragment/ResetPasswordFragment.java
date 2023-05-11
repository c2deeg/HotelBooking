package com.app.LukandaH.Fragments.ResetPasswordFragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Fragments.ForgotPasswordFragment.ForgotPasswordFragment;
import com.app.LukandaH.Fragments.LoginFragment.LoginFragment;
import com.app.LukandaH.Fragments.ResetPasswordFragment.Presenter.ResetPasswordPresenter;
import com.app.LukandaH.Fragments.ResetPasswordFragment.View.ResetPasswordView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ResetPasswordFragment extends Fragment implements View.OnClickListener, IOnBackPressed, ResetPasswordView {
    public static IOnBackPressed backPressed;
    private Button otpSubmitBtn;
    private Activity activity;
    private View view;
    private EditText et_newPassword;
    private EditText et_conNewPassword;
    private ImageView img_pass_show;
    private ImageView img_con_pass_show;
    private Boolean flag1 = true;
    private Boolean flag2 = true;
    private String userrole;
    private String email;
    private ResetPasswordPresenter resetPasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        activity = getActivity();
        userrole = getArguments().getString("role");
        email = getArguments().getString("email");
        resetPasswordPresenter = new ResetPasswordPresenter(activity, this, userrole);
        init();
        listeners();
        return view;
    }

    private void init() {
        otpSubmitBtn = view.findViewById(R.id.otpSubmitBtn);
        et_newPassword = view.findViewById(R.id.et_newPassword);
        et_conNewPassword = view.findViewById(R.id.et_conNewPassword);
        img_pass_show = view.findViewById(R.id.img_pass_show);
        img_con_pass_show = view.findViewById(R.id.img_con_pass_show);
    }

    private void listeners() {
        otpSubmitBtn.setOnClickListener(this);
        img_pass_show.setOnClickListener(this);
        img_con_pass_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == otpSubmitBtn) {
            resetPasswordPresenter.resetPasswordMethod(et_newPassword, et_conNewPassword, email);
        } else if (view == img_pass_show) {
            if (flag1) {
//                passEye.setColorFilter(Color.parseColor("#EECB4F"));
                et_newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                img_pass_show.setImageResource(R.drawable.eye_open);
                et_newPassword.setSelection(et_newPassword.getText().length());
            } else {
//                passEye.setColorFilter(Color.parseColor("#ffffff"));
                et_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                img_pass_show.setImageResource(R.drawable.eye_closed);
                et_newPassword.setSelection(et_newPassword.getText().length());
            }
            flag1 = !flag1;
        } else if (view == img_con_pass_show) {
            if (flag2) {
//                passEye.setColorFilter(Color.parseColor("#EECB4F"));
                et_conNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                img_con_pass_show.setImageResource(R.drawable.eye_open);
                et_conNewPassword.setSelection(et_conNewPassword.getText().length());
            } else {
//                passEye.setColorFilter(Color.parseColor("#ffffff"));
                et_conNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                img_con_pass_show.setImageResource(R.drawable.eye_closed);
                et_conNewPassword.setSelection(et_conNewPassword.getText().length());
            }
            flag2 = !flag2;
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
        Fragment fragment = new ForgotPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("role", userrole);
        fragment.setArguments(bundle);
        Utils.changeLoginFragment(activity, fragment);
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