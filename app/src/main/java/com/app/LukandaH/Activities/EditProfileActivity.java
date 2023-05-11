package com.app.LukandaH.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.Fragments.EditProfileFragment.EditProfileFragment;
import com.app.LukandaH.R;

public class EditProfileActivity extends AppCompatActivity {
    private FrameLayout fl_editProfileContainer;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        activity = this;

        init();

        EditProfileFragment fragment = new EditProfileFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_editProfileContainer, fragment).commit();
    }

    private void init() {
        fl_editProfileContainer = findViewById(R.id.fl_editProfileContainer);
    }
}