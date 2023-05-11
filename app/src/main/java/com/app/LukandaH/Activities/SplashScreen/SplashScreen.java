package com.app.LukandaH.Activities.SplashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.Activities.GetStartedActivity;
import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreen extends AppCompatActivity {
    Handler handler = new Handler();
    private Activity activity;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        activity = this;
        handleNotificationData();
        printHashKey(activity);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    token = task.getResult().getToken();
                    CSPreferences.putString(activity, Utils.DEVICETOKEN, token);
                    Log.d("fcmToken", "FcmToken is: " + token);
//                    checkLocationPermission();
                } else {
                }
            }
        });

       /* FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (!task.isSuccessful()) {
                    Log.e("", "Failed to get the token.");
                    return;
                }

                String token = task.getResult();

                Log.e("Token", "Token : " + token);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("", "Failed to get the token : " + e.getLocalizedMessage());
            }
        });*/

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String status = CSPreferences.readString(activity, Utils.LOGINSTATUS);
                if (status.equalsIgnoreCase("true")) {
                    Intent intent = new Intent(activity, HomeActivity.class);
//                    intent.putExtra("role", "staff");
//                    intent.putExtra("role", "user");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(activity, GetStartedActivity.class);
                    startActivity(intent);
                }
            }
        }, 3500);
    }

    public void getToken(View view) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.e("", "Failed to get the token.");
                    return;
                }
                //get the token from task
                String token = task.getResult();
                Log.d("", "Token : " + token);
//                tvToken.setText(token);
                Toast.makeText(activity, token, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("", "Failed to get the token : " + e.getLocalizedMessage());
            }
        });
    }

    /**
     * method to handle the data content on clicking of notification if both notification and data payload are sent
     */
    private void handleNotificationData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("data1")) {
                Log.d("", "Data1 : " + bundle.getString("data1"));
            }
            if (bundle.containsKey("data2")) {
                Log.d("", "Data2 : " + bundle.getString("data2"));
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("", "On New Intent called");
    }

    public void subsSports(View view) {
        subscribeToTopic("sports");
    }

    public void unsubsSports(View view) {
        unsubscribeToTopic("sports");
    }

    public void subsEnt(View view) {
        subscribeToTopic("entertainment");
    }

    public void unsubsEnt(View view) {
        unsubscribeToTopic("entertainment");
    }

    /*
     * method to subscribe to topic
     *
     * @param topic to which subscribe
     */
    private void subscribeToTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(activity, "Subscribed to " + topic, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Failed to subscribe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * method to unsubscribe to topic
     *
     * @param topic to which unsubscribe
     */
    private void unsubscribeToTopic(String topic) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(activity, "UnSubscribed to " + topic, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Failed to unsubscribe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("TAG", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("TAG", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("TAG", "printHashKey()", e);
        }
    }
}