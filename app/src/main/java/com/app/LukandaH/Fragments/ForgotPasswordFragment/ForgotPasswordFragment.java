package com.app.LukandaH.Fragments.ForgotPasswordFragment;

import android.app.Activity;
import android.os.Bundle;
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
import com.app.LukandaH.Fragments.ForgotPasswordFragment.Presenter.ForgotPasswordPresenter;
import com.app.LukandaH.Fragments.ForgotPasswordFragment.View.ForgotPasswordView;
import com.app.LukandaH.Fragments.LoginFragment.LoginFragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener, IOnBackPressed, ForgotPasswordView {
    public static IOnBackPressed backPressed;
    private Activity activity;
    private View view;
    private Button continueBtn;
    private ImageView img_back;
    private EditText et_email;
    private String userrole;
    private ForgotPasswordPresenter forgotPasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        activity = getActivity();
        userrole = getArguments().getString("role");
        forgotPasswordPresenter = new ForgotPasswordPresenter(activity, this, userrole);
        init();
        listeners();
        return view;
    }

    private void init() {
        continueBtn = view.findViewById(R.id.continueBtn);
        img_back = view.findViewById(R.id.img_back);
        et_email = view.findViewById(R.id.et_email);
    }

    private void listeners() {
        continueBtn.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == continueBtn) {
            forgotPasswordPresenter.forgotPasswordMethod(et_email);
        } else if (view == img_back) {
            Fragment fragment = new LoginFragment();
            Bundle bundle = new Bundle();
            bundle.putString("role", userrole);
            fragment.setArguments(bundle);
            Utils.changeLoginFragment(activity,fragment);

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