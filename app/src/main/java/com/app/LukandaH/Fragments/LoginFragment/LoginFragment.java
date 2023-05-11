package com.app.LukandaH.Fragments.LoginFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Activities.WelcomeScreen.WelcomeActivity;
import com.app.LukandaH.Fragments.ForgotPasswordFragment.ForgotPasswordFragment;
import com.app.LukandaH.Fragments.LoginFragment.Presenter.LoginPresenter;
import com.app.LukandaH.Fragments.LoginFragment.View.LoginView;
import com.app.LukandaH.Fragments.SignUpFragment.SignUpFragment;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;


public class LoginFragment extends Fragment implements View.OnClickListener, IOnBackPressed, LoginView {
    public static IOnBackPressed backPressed;
    private final int RC_SIGN_IN = 1;
    private final String black35 = "#59000000";
    private final String black = "#FF000000";
    private Activity activity;
    private View view;
    private ImageView img_pass_show;
    private EditText et_email;
    private EditText et_password;
    private TextView tv_createAcc;
    private TextView tv_forgotPass;
    private boolean flag = true;
    private TextView tv_holderType;
    private Button btn_login;
    private String userrole;
    private LoginPresenter loginPresenter;
    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(@Nullable AccessToken accessToken, @Nullable AccessToken accessToken1) {
            if (accessToken1 == null) {
                Toast.makeText(activity, "logout", Toast.LENGTH_SHORT).show();
            } else {
                loaUserProfile(accessToken1);
            }
        }
    };
    private Button gogoleLoginButton;
    private Button facebookLoginButton;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    private LoginButton login_button;
    private LoginBehavior loginBehavior;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        activity = getActivity();
        Bundle bundle = getArguments();
        userrole = bundle.getString("role");
        loginPresenter = new LoginPresenter(activity, this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
//        updateUI(account);
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        AppEventsLogger.activateApp(activity.getApplication());
        callbackManager = CallbackManager.Factory.create();

        init();
        listeners();
        if (userrole.equals("staff")) {
            tv_holderType.setText("Staff");
        } else if (userrole.equals("user")) {
            tv_holderType.setText("User");
        }

        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                CSPreferences.putString(activity, Utils.LOGINTHROUGH, "facebook");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });
        return view;
    }

    private void init() {
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        img_pass_show = view.findViewById(R.id.img_pass_show);
        tv_createAcc = view.findViewById(R.id.tv_createAcc);
        tv_forgotPass = view.findViewById(R.id.tv_forgotPass);
        tv_holderType = view.findViewById(R.id.tv_holderType);
        btn_login = view.findViewById(R.id.btn_login);
        gogoleLoginButton = view.findViewById(R.id.gogoleLoginButton);
        facebookLoginButton = view.findViewById(R.id.facebookLoginButton);
        login_button = view.findViewById(R.id.login_button);
    }

    private void listeners() {
        img_pass_show.setOnClickListener(this);
        tv_createAcc.setOnClickListener(this);
        tv_forgotPass.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        gogoleLoginButton.setOnClickListener(this);
        facebookLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == img_pass_show) {
            if (flag) {
//                passEye.setColorFilter(Color.parseColor("#EECB4F"));
                et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                img_pass_show.setBackgroundDrawable(getResources().getDrawable(R.drawable.eye_open));
                img_pass_show.setImageResource(R.drawable.eye_open);
                et_password.setSelection(et_password.getText().length());
//                img_pass_show.setColorFilter(Color.parseColor(black));
            } else {
//                passEye.setColorFilter(Color.parseColor("#ffffff"));
                et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                img_pass_show.setColorFilter(Color.parseColor(black35));
                img_pass_show.setImageResource(R.drawable.eye_closed);
                et_password.setSelection(et_password.getText().length());
            }
            flag = !flag;
        } else if (view == tv_createAcc) {
            Fragment fragment = new SignUpFragment();
            Bundle bundle = new Bundle();
            bundle.putString("role", userrole);
            fragment.setArguments(bundle);
            Utils.changeLoginFragment(activity, fragment);
        } else if (view == tv_forgotPass) {
            Fragment fragment = new ForgotPasswordFragment();
            Bundle bundle = new Bundle();
            bundle.putString("role", userrole);
            fragment.setArguments(bundle);
            Utils.changeLoginFragment(activity, fragment);
        } else if (view == btn_login) {
            loginPresenter.loginMethod(et_email, et_password);
            CSPreferences.putString(activity, Utils.LOGINTHROUGH, "email and password");
        } else if (view == gogoleLoginButton) {
            googleSignIn();
        } else if (view == facebookLoginButton) {
            login_button.performClick();
        }
    }

    private void loaUserProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, ((jsonObject, graphResponse) -> {
            if (jsonObject != null) {
                try {
//                    String email = jsonObject.getString("email");
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
//                    String firstName = jsonObject.getString("firstName");
//                    Toast.makeText(activity, email, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(activity, id, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(activity, name, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(activity, firstName, Toast.LENGTH_SHORT).show();
                    loginPresenter.SocialLoginMethod(id, "facebook", name, "male");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
            if (acct != null) {
                String personName = acct.getDisplayName();
//                String personGivenName = acct.getGivenName();
//                String personFamilyName = acct.getFamilyName();
//                String personEmail = acct.getEmail();
                String personId = acct.getId();
//                Uri personPhoto = acct.getPhotoUrl();

                loginPresenter.SocialLoginMethod(personId, "google", personName, "male");
                CSPreferences.putString(activity, Utils.LOGINTHROUGH, "google");

            }
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
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
        Intent intent = new Intent(activity, WelcomeActivity.class);
        activity.startActivity(intent);
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
