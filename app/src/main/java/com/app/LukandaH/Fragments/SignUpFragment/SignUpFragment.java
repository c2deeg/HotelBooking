package com.app.LukandaH.Fragments.SignUpFragment;

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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Fragments.LoginFragment.LoginFragment;
import com.app.LukandaH.Fragments.SignUpFragment.Presenter.SignUpPresenter;
import com.app.LukandaH.Fragments.SignUpFragment.View.SignUpView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;


public class SignUpFragment extends Fragment implements View.OnClickListener, IOnBackPressed, SignUpView {
    public static IOnBackPressed backPressed;
    FragmentManager fragmentManager;
    private Activity activity;
    private View view;
    private TextView tv_backToLogin;
    private Boolean flag1 = true;
    private Boolean flag2 = true;
    private Button signUpBtn;
    private EditText et_signUpPassword;
    private EditText et_conPassword;
    private ImageView img_pass_show;
    private ImageView img_con_pass_show;
    private TextView tv_holderType;
    private String userrole;
    private EditText et_email;
    private EditText et_userName;
    private SignUpPresenter signUpPresenter;
    private String phoneNo = "9876543210";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        activity = getActivity();
        fragmentManager = getFragmentManager();
        userrole = getArguments().getString("role");
        signUpPresenter = new SignUpPresenter(activity, this, fragmentManager);
        init();
        listeners();
        if (userrole.equals("staff")) {
            tv_holderType.setText("Staff");
        } else if (userrole.equals("user")) {
            tv_holderType.setText("User");
        }
        return view;
    }

    private void init() {
        tv_backToLogin = view.findViewById(R.id.tv_backToLogin);
        et_signUpPassword = view.findViewById(R.id.et_signUpPassword);
        et_conPassword = view.findViewById(R.id.et_conPassword);
        img_pass_show = view.findViewById(R.id.img_pass_show);
        img_con_pass_show = view.findViewById(R.id.img_con_pass_show);
        tv_holderType = view.findViewById(R.id.tv_holderType);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        et_email = view.findViewById(R.id.et_email);
        et_userName = view.findViewById(R.id.et_userName);
    }

    private void listeners() {
        tv_backToLogin.setOnClickListener(this);
        img_pass_show.setOnClickListener(this);
        img_con_pass_show.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tv_backToLogin) {
            Fragment fragment = new LoginFragment();
            Bundle bundle = new Bundle();
            bundle.putString("role", userrole);
            fragment.setArguments(bundle);
            Utils.changeLoginFragment(activity,fragment);
        } else if (view == img_pass_show) {
            if (flag1) {
                et_signUpPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                img_pass_show.setImageResource(R.drawable.eye_open);
                et_signUpPassword.setSelection(et_signUpPassword.getText().length());
            } else {
                et_signUpPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                img_pass_show.setImageResource(R.drawable.eye_closed);
                et_signUpPassword.setSelection(et_signUpPassword.getText().length());
            }
            flag1 = !flag1;
        } else if (view == img_con_pass_show) {
            if (flag2) {
                et_conPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                img_con_pass_show.setImageResource(R.drawable.eye_open);
                et_conPassword.setSelection(et_conPassword.getText().length());
            } else {
                et_conPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                img_con_pass_show.setImageResource(R.drawable.eye_closed);
                et_conPassword.setSelection(et_conPassword.getText().length());
            }
            flag2 = !flag2;
        } else if (view == signUpBtn) {
            signUpPresenter.loginMethod(et_userName, et_email, phoneNo, et_signUpPassword, et_conPassword, userrole);
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
        Fragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString("role", userrole);
        fragment.setArguments(bundle);
        Utils.changeLoginFragment(activity,fragment);
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