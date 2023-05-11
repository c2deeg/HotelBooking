package com.app.LukandaH.Fragments.SettingsFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.SettingsFragment.Presenter.SettingsPresenter;
import com.app.LukandaH.Fragments.SettingsFragment.View.SettingsView;
import com.app.LukandaH.Models.ProfileModels.ProfileData;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingsFragment extends Fragment implements View.OnClickListener, SettingsView {
    private Activity activity;
    private View view;
    private ImageView img_back;
    private CircleImageView civ_profile;
    private TextView tv_name;
    private TextView tv_username;
    private SettingsPresenter settingsPresenter;
    private ProfileData profileData;
    private LinearLayout ll_notifications;
    private ImageView img_notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        activity = getActivity();
        settingsPresenter = new SettingsPresenter(activity, this);
        init();
        listeners();
        settingsPresenter.profileMethod();
        return view;
    }

    private void init() {
        img_back = view.findViewById(R.id.img_back);
        civ_profile = view.findViewById(R.id.civ_profile);
        tv_name = view.findViewById(R.id.tv_name);
        tv_username = view.findViewById(R.id.tv_username);
        ll_notifications = view.findViewById(R.id.ll_notifications);
        img_notification = view.findViewById(R.id.img_notification);
    }

    private void listeners() {
        img_back.setOnClickListener(this);
        ll_notifications.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            activity.finish();
        } else if (v == ll_notifications) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        } else if (v == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showMessage(Activity activity, String msg) {
//        Utils.showMessage(activity, msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void setData(Activity activity, ProfileData data) {
        this.profileData = data;
        String name = profileData.getName().toString();
        if (name.contains(" ")) {
            String splitString[] = name.split(" ");
            String firstName = splitString[0];
            String lastName = splitString[1];
            String fullName = firstName.concat(lastName);
            tv_username.setText("@" + fullName);
        } else {
            tv_username.setText(name);
        }
        tv_name.setText(name);
        String ppImage = profileData.getAvatar();
        CSPreferences.putString(activity, Utils.ImageBaseURL, ppImage);
        if (!(profileData.getAvatar().isEmpty())) {
            Picasso.get().load(profileData.getAvatar()).placeholder(R.drawable.account_dp_profile).into(civ_profile);
        } else {
            civ_profile.setImageResource(R.drawable.account_dp_profile);
        }
    }
}