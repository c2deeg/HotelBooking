package com.app.LukandaH.Fragments.OtpFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Fragments.ForgotPasswordFragment.ForgotPasswordFragment;
import com.app.LukandaH.Fragments.OtpFragment.Presenter.OtpVerifyPersenter;
import com.app.LukandaH.Fragments.OtpFragment.View.OtpVerifyView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.GenericTextWatcher;
import com.app.LukandaH.Utils.Utils;


public class OtpFragment extends Fragment implements View.OnClickListener, IOnBackPressed, OtpVerifyView {
    public static IOnBackPressed backPressed;
    private Activity activity;
    private View view;
    private EditText et_otpBox1;
    private EditText et_otpBox2;
    private EditText et_otpBox3;
    private EditText et_otpBox4;
    private Button otpSubmitBtn;
    private TextView tv_email;
    private String userrole;
    private ImageView img_back;
    private String email;
    private TextView tv_resendOtp;
    private OtpVerifyPersenter otpVerifyPersenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_otp, container, false);
        activity = getActivity();
        userrole = getArguments().getString("role");
        email = getArguments().getString("email");
        otpVerifyPersenter = new OtpVerifyPersenter(activity, this, userrole, email);
        init();
        listeners();
        tv_email.setText(email);
        return view;
    }

    private void init() {
        et_otpBox1 = view.findViewById(R.id.et_otpBox1);
        tv_email = view.findViewById(R.id.tv_email);
        et_otpBox2 = view.findViewById(R.id.et_otpBox2);
        et_otpBox3 = view.findViewById(R.id.et_otpBox3);
        et_otpBox4 = view.findViewById(R.id.et_otpBox4);
        otpSubmitBtn = view.findViewById(R.id.otpSubmitBtn);
        img_back = view.findViewById(R.id.img_back);
        tv_resendOtp = view.findViewById(R.id.tv_resendOtp);

        EditText[] edit = {et_otpBox1, et_otpBox2, et_otpBox3, et_otpBox4};
        et_otpBox1.addTextChangedListener(new GenericTextWatcher(et_otpBox1, edit));
        et_otpBox2.addTextChangedListener(new GenericTextWatcher(et_otpBox2, edit));
        et_otpBox3.addTextChangedListener(new GenericTextWatcher(et_otpBox3, edit));
        et_otpBox4.addTextChangedListener(new GenericTextWatcher(et_otpBox4, edit));
    }

    private void listeners() {
        otpSubmitBtn.setOnClickListener(this);
        img_back.setOnClickListener(this);
        et_otpBox4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == et_otpBox3.length()) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_otpBox4.getWindowToken(), 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        tv_resendOtp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == otpSubmitBtn) {
            String otp = et_otpBox1.getText().toString() + et_otpBox2.getText().toString() + et_otpBox3.getText().toString() + et_otpBox4.getText().toString();
            otpVerifyPersenter.otpVerifyMethod(otp);
//            Fragment fragment = new ResetPasswordFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("role", userrole);
//            fragment.setArguments(bundle);
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameContainer, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        } else if (view == img_back) {
            Fragment fragment = new ForgotPasswordFragment();
            Bundle bundle = new Bundle();
            bundle.putString("role", userrole);
            fragment.setArguments(bundle);
            Utils.changeLoginFragment(activity, fragment);
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameContainer, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        } else if (view == tv_resendOtp) {
            otpVerifyPersenter.otpResendMethod(email);
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
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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