package com.app.LukandaH.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.Fragments.ForgotPasswordFragment.ForgotPasswordFragment;
import com.app.LukandaH.Fragments.LoginFragment.LoginFragment;
import com.app.LukandaH.Fragments.OtpFragment.OtpFragment;
import com.app.LukandaH.Fragments.ResetPasswordFragment.ResetPasswordFragment;
import com.app.LukandaH.Fragments.SignUpFragment.SignUpFragment;
import com.app.LukandaH.R;

public class LoginActivity extends AppCompatActivity {
    private Activity activity;
    private FrameLayout frameContainer;
    private String userrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        activity = this;
        Intent intent = getIntent();
        userrole = intent.getStringExtra("role");
//        Log.d("check", userrole);

        init();

        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString("role", userrole);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, fragment).commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameContainer);
        if (SignUpFragment.backPressed != null) {
            SignUpFragment.backPressed.onBackPressed();
        } else if (LoginFragment.backPressed != null) {
            LoginFragment.backPressed.onBackPressed();
        } else if (ForgotPasswordFragment.backPressed != null) {
            ForgotPasswordFragment.backPressed.onBackPressed();
        } else if (OtpFragment.backPressed != null) {
            OtpFragment.backPressed.onBackPressed();
        } else if (ResetPasswordFragment.backPressed != null) {
            ResetPasswordFragment.backPressed.onBackPressed();
        }
//        finishAffinity();
    }

    private void init() {
        frameContainer = findViewById(R.id.frameContainer);
    }
}