package com.app.LukandaH.Fragments.ChangePasswordFragment;

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

import com.app.LukandaH.Fragments.ChangePasswordFragment.Presenter.ChangePasswordPresenter;
import com.app.LukandaH.Fragments.ChangePasswordFragment.View.ChangePasswordView;
import com.app.LukandaH.Fragments.EditProfileFragment.EditProfileFragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener, ChangePasswordView {
    private Activity activity;
    private View view;
    private ImageView img_back;
    private EditText et_currentPassword;
    private EditText et_newPassword;
    private EditText et_conNewPassword;
    private ImageView img_currentPswdEye;
    private ImageView img_newPswdEye;
    private ImageView img_conNewPswdEye;
    private Button btn_confirmChanges;
    private boolean currentPswdFlag = true;
    private boolean newPswdFlag = true;
    private boolean conNewPswdFlag = true;
    private ChangePasswordPresenter changePasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_password, container, false);
        activity = getActivity();
        changePasswordPresenter = new ChangePasswordPresenter(activity, this);
        init();
        listeners();

        return view;
    }

    private void init() {
        img_back = view.findViewById(R.id.img_back);
        et_currentPassword = view.findViewById(R.id.et_currentPassword);
        et_newPassword = view.findViewById(R.id.et_newPassword);
        et_conNewPassword = view.findViewById(R.id.et_conNewPassword);
        img_currentPswdEye = view.findViewById(R.id.img_currentPswdEye);
        img_newPswdEye = view.findViewById(R.id.img_newPswdEye);
        img_conNewPswdEye = view.findViewById(R.id.img_conNewPswdEye);
        btn_confirmChanges = view.findViewById(R.id.btn_confirmChanges);
    }

    private void listeners() {
        img_back.setOnClickListener(this);
        img_currentPswdEye.setOnClickListener(this);
        img_newPswdEye.setOnClickListener(this);
        img_conNewPswdEye.setOnClickListener(this);
        btn_confirmChanges.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            Utils.changeFragment(activity, new EditProfileFragment());
        } else if (v == img_currentPswdEye) {
            if (currentPswdFlag) {
                et_currentPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                img_pass_show.setBackgroundDrawable(getResources().getDrawable(R.drawable.eye_open));
                img_currentPswdEye.setImageResource(R.drawable.eye_open);
                et_currentPassword.setSelection(et_currentPassword.getText().length());
            } else {
                et_currentPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                img_pass_show.setColorFilter(Color.parseColor(black35));
                img_currentPswdEye.setImageResource(R.drawable.eye_closed);
                et_currentPassword.setSelection(et_currentPassword.getText().length());
            }
            currentPswdFlag = !currentPswdFlag;
        } else if (v == img_newPswdEye) {
            if (newPswdFlag) {
                et_newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                img_pass_show.setBackgroundDrawable(getResources().getDrawable(R.drawable.eye_open));
                img_newPswdEye.setImageResource(R.drawable.eye_open);
                et_newPassword.setSelection(et_newPassword.getText().length());
            } else {
                et_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                img_pass_show.setColorFilter(Color.parseColor(black35));
                img_newPswdEye.setImageResource(R.drawable.eye_closed);
                et_newPassword.setSelection(et_newPassword.getText().length());
            }
            newPswdFlag = !newPswdFlag;
        } else if (v == img_conNewPswdEye) {
            if (conNewPswdFlag) {
                et_conNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                img_pass_show.setBackgroundDrawable(getResources().getDrawable(R.drawable.eye_open));
                img_conNewPswdEye.setImageResource(R.drawable.eye_open);
                et_conNewPassword.setSelection(et_conNewPassword.getText().length());
            } else {
                et_conNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                img_pass_show.setColorFilter(Color.parseColor(black35));
                img_conNewPswdEye.setImageResource(R.drawable.eye_closed);
                et_conNewPassword.setSelection(et_conNewPassword.getText().length());
            }
            conNewPswdFlag = !conNewPswdFlag;
        } else if (v == btn_confirmChanges) {
            changePasswordPresenter.changePasswordMethod(et_currentPassword, et_newPassword, et_conNewPassword);
        }
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