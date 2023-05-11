package com.app.LukandaH.Fragments.EditProfileFragment.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.Toast;

import com.app.LukandaH.Fragments.EditProfileFragment.View.ProfileImageView;
import com.app.LukandaH.Handlers.ProfileHandler;
import com.app.LukandaH.Handlers.ProfileImageHandler;
import com.app.LukandaH.Handlers.ProfileUpdateHandler;
import com.app.LukandaH.Models.ProfileImageModels.ProfileImageExample;
import com.app.LukandaH.Models.ProfileModels.ProfileExample;
import com.app.LukandaH.Models.ProfileUpdateModels.ProfileUpdateExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfilePresenter {
    private ProfileImageView profileImageView;
    private Activity activity;
    private String id;
    private MultipartBody.Part imagePart;
    private String token;
    private String name;
    private String mobileNumber;
    private EditText email;

    public EditProfilePresenter(Activity activity, ProfileImageView profileImageView) {
        this.activity = activity;
        this.profileImageView = profileImageView;
    }

    public void profileImageMethod(Bitmap photo) {
        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream);
            byte[] photoByteArray = stream.toByteArray();
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray);
//            id = RequestBody.create(MediaType.parse("multipart/form-data"),
//                    CSPreferences.readString(activity, Utils.USERID));
            Random random = new Random();
            imagePart = MultipartBody.Part.createFormData("file", "abc" + random.nextInt(1000000) + ".jpg", requestFile);
        }

        id = CSPreferences.readString(activity, Utils.USERID);
        profileImageView.showDialog(activity);
        WebServices.getmInstance().profileImageUploadMethod(imagePart, id, new ProfileImageHandler() {
            @Override
            public void onSuccess(ProfileImageExample profileImageExample) {
                profileImageView.hideDialog();
                if (profileImageExample != null) {
                    if (profileImageExample.getIsSuccess() == true) {
                        profileImageView.showMessage(activity, profileImageExample.getData());
                    } else {
                        profileImageView.showMessage(activity, profileImageExample.getData());
                    }
                } else {
                    profileImageView.showMessage(activity, profileImageExample.getData());
                }
            }

            @Override
            public void onError(String message) {
                profileImageView.hideDialog();
                profileImageView.showMessage(activity, message);
            }
        });
    }

    public void profileMethod() {

        token = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        id = CSPreferences.readString(activity, Utils.USERID);
        profileImageView.showDialog(activity);
        WebServices.getmInstance().profileMethod(token, id, new ProfileHandler() {
            @Override
            public void onSuccess(ProfileExample profileExample, String accessToken) {
                profileImageView.hideDialog();
                if (profileExample != null) {
                    if (profileExample.getIsSuccess() == true) {
                        profileImageView.showMessage(activity, profileExample.getMessage());
                        profileImageView.setData(activity, profileExample.getData());
                    } else {
                        profileImageView.showMessage(activity, profileExample.getMessage());
                    }
                } else {
                    profileImageView.showMessage(activity, profileExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                profileImageView.hideDialog();
                profileImageView.showMessage(activity, message);
            }
        });
    }

    public void profileUpdateMethod(String name, EditText email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;

        token = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        id = CSPreferences.readString(activity, Utils.USERID);
        String deviceToken = CSPreferences.readString(activity, Utils.DEVICETOKEN);
        if (checkValidation()) {
            profileImageView.showDialog(activity);
            WebServices.getmInstance().profileUpdateMethod(id, name.trim(), email.getText().toString().trim(), mobileNumber.trim(), new ProfileUpdateHandler() {
                @Override
                public void onSuccess(ProfileUpdateExample profileUpdateExample, String accessToken) {
                    profileImageView.hideDialog();
                    if (profileUpdateExample != null) {
                        if (profileUpdateExample.getIsSuccess() == true) {
                            activity.finish();
                        } else {
                            Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();
//                        profileImageView.showMessage(activity, profileExample.getMessage());
                        }
                    } else {
//                    profileImageView.showMessage(activity, profileExample.getMessage());
                        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(String message) {
                    profileImageView.hideDialog();
                    profileImageView.showMessage(activity, message);
                }
            });
        } else {
        }
    }

    private boolean checkValidation() {
        String emailStr = email.getText().toString();
//        String mobNumbe = mobileNumber.getText().toString();
        if (name.length() == 1 || name.isEmpty()) {
            Toast.makeText(activity, "Please Enter name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (mobileNumber.isEmpty() || mobileNumber.length() < 10) {
            Toast.makeText(activity, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
            return false;
        } else if(emailStr.isEmpty() || !(emailStr.contains("@")) || !(emailStr.contains(".")) || emailStr.contains(" ")){
            Toast.makeText(activity, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

}