package com.app.LukandaH.Fragments.ProfileFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.EditProfileActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Activities.SettingsActivity;
import com.app.LukandaH.Activities.WelcomeScreen.WelcomeActivity;
import com.app.LukandaH.Fragments.EditProfileFragment.View.ProfileImageView;
import com.app.LukandaH.Fragments.ProfileFragment.Presenter.ProfilePresenter;
import com.app.LukandaH.Fragments.ProfileFragment.View.ProfileView;
import com.app.LukandaH.Models.ProfileModels.ProfileData;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileView, ProfileImageView {
    ProfilePresenter profilePresenter;
    private View view;
    private Activity activity;
    private LinearLayout ll_settings;
    private LinearLayout ll_signOut;
    private TextView tv_name;
    private TextView tv_username;
    private LinearLayout ll_editProfile;
    //    private String userrole;
    private CircleImageView civ_profile;

    private ImageView img_notification;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        activity = getActivity();
//        userrole = getArguments().getString("role");
//        Log.d("check", userrole);
        profilePresenter = new ProfilePresenter(activity, this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        init();
        listeners();
        profilePresenter.profileMethod();
        return view;
    }

    private void init() {
        ll_settings = view.findViewById(R.id.ll_settings);
        ll_signOut = view.findViewById(R.id.ll_signOut);
        tv_username = view.findViewById(R.id.tv_username);
        tv_name = view.findViewById(R.id.tv_name);
        civ_profile = view.findViewById(R.id.civ_profile);
        ll_editProfile = view.findViewById(R.id.ll_editProfile);
        img_notification = view.findViewById(R.id.img_notification);
    }

    private void listeners() {
        ll_settings.setOnClickListener(this);
        ll_signOut.setOnClickListener(this);
        ll_editProfile.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ll_settings) {
            Intent intent = new Intent(activity, SettingsActivity.class);
//            intent.putExtra("role", userrole);
            startActivity(intent);
        } else if (view == ll_signOut) {
            showDialog();
        } else if (view == ll_editProfile) {

            Intent intent = new Intent(activity, EditProfileActivity.class);
            startActivity(intent);

        } else if (view == img_notification) {
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
        String name = data.getName();
        tv_name.setText(name);
        String lcname = name.replace(" ", "");
        tv_username.setText("@" + lcname.trim().toLowerCase());
        String ppImage = data.getAvatar();
        CSPreferences.putString(activity, Utils.ImageBaseURL, ppImage);
        if (!(data.getAvatar().isEmpty())) {
            Picasso.get().load(data.getAvatar()).placeholder(R.drawable.account_dp_profile).into(civ_profile);
        } else {
            civ_profile.setImageResource(R.drawable.account_dp_profile);
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logoutdialog);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btn_signout = dialog.findViewById(R.id.btn_signout);
        String gender = CSPreferences.readString(activity, Utils.GENDERSELECT);

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSPreferences.putString(activity, Utils.LOGINSTATUS, "false");
                String loginThrough = CSPreferences.readString(activity, Utils.LOGINTHROUGH);
                if (loginThrough.equals("google")) {
                    googleSignOut();
                    googleRevokeAccess();
                } else if (loginThrough.equals("email and password")) {
                    Intent intent1 = new Intent(activity, WelcomeActivity.class);
                    startActivity(intent1);
                } else if (loginThrough.equals("facebook")) {
                    FacebookSdk.sdkInitialize(activity);
                    LoginManager.getInstance().logOut();
                    Intent intent1 = new Intent(activity, WelcomeActivity.class);
                    startActivity(intent1);
                }
            }
        });

        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void googleSignOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent1 = new Intent(activity, WelcomeActivity.class);
                        startActivity(intent1);
                    }
                });
    }

    private void googleRevokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}