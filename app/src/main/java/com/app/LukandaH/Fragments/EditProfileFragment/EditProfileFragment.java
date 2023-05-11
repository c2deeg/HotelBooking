package com.app.LukandaH.Fragments.EditProfileFragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.ChangePasswordFragment.ChangePasswordFragment;
import com.app.LukandaH.Fragments.EditProfileFragment.Presenter.EditProfilePresenter;
import com.app.LukandaH.Fragments.EditProfileFragment.View.ProfileImageView;
import com.app.LukandaH.Models.ProfileModels.ProfileData;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment implements View.OnClickListener, ProfileImageView {
    EditProfilePresenter profileImagePresenter;
    private Activity activity;
    private View view;
    private Spinner spinner_gender;
    private ImageView img_back;
    private TextView tv_username;
    private TextView tv_name;
    private EditText et_firstName;
    private EditText et_lastName;
    private CircleImageView civ_profile;
    private ImageView img_editProfile;
    private String[] gender = {"Male", "Female"};
    private int SELECT_PICTURE = 200;
    private Bitmap bitmap;
    private Uri selectedImageUri;
    private ProfileData getprofiledata;
    private EditText et_mobNumber;
    private Button btn_saveChanges;
    private LinearLayout ll_changePassword;
    private ImageView img_notification;
    private String fullName;
    private String enteredFullName;
    private String enteredMovileNumber;
    private String mobileNumber;
    private EditText et_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        activity = getActivity();
        profileImagePresenter = new EditProfilePresenter(activity, this);
        init();
        listeners();

        profileImagePresenter.profileMethod();
        ArrayAdapter genderAdapter = new ArrayAdapter(activity, R.layout.spinner_gender_item, R.id.tv_text, gender);
//        genderAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner_gender.getBackground().setColorFilter(getResources().getColor(R.color.skyblue), PorterDuff.Mode.SRC_ATOP);
        spinner_gender.setAdapter(genderAdapter);
        return view;
    }

    private void init() {
        spinner_gender = view.findViewById(R.id.spinner_gender);
        img_back = view.findViewById(R.id.img_back);
        tv_username = view.findViewById(R.id.tv_username);
        tv_name = view.findViewById(R.id.tv_name);
        civ_profile = view.findViewById(R.id.civ_profile);
        et_firstName = view.findViewById(R.id.et_firstName);
        et_lastName = view.findViewById(R.id.et_lastName);
        img_editProfile = view.findViewById(R.id.img_editProfile);
        et_mobNumber = view.findViewById(R.id.et_mobNumber);
        btn_saveChanges = view.findViewById(R.id.btn_saveChanges);
        ll_changePassword = view.findViewById(R.id.ll_changePassword);
        img_notification = view.findViewById(R.id.img_notification);
        et_email = view.findViewById(R.id.et_email);
    }
    private void listeners() {
        img_back.setOnClickListener(this);
        img_editProfile.setOnClickListener(this);
        btn_saveChanges.setOnClickListener(this);
        ll_changePassword.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == img_back) {
            activity.finish();
        } else if (view == img_editProfile) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        } else if (view == btn_saveChanges) {
            enteredMovileNumber = et_mobNumber.getText().toString();
            String firstName = et_firstName.getText().toString();
            String lastname = et_lastName.getText().toString();
            enteredFullName = firstName + " " + lastname;
            if(enteredFullName.equals(fullName) && enteredMovileNumber.equals(mobileNumber)){
                Toast.makeText(activity, "Please make some changes", Toast.LENGTH_SHORT).show();
            }
            else{
                profileImagePresenter.profileUpdateMethod(enteredFullName, et_email, enteredMovileNumber);
            }
        } else if (view == ll_changePassword) {
            Utils.changeFragment(activity, new ChangePasswordFragment());
        } else if (view == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    String path = getPathFromURI(selectedImageUri);
//                    Log.d(TAG, "onActivityimg" + path);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImageUri);
                        profileImagePresenter.profileImageMethod(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    civ_profile.setImageURI(selectedImageUri);
//                    Log.d(TAG, "onActivityu" + selectedImageUri);
                }
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
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
        this.getprofiledata = data;
        String name = getprofiledata.getName();
        tv_name.setText(name);
        String lcname = name.replace(" ", "");
        tv_username.setText("@" + lcname.trim().toLowerCase());
        String ppImage = getprofiledata.getAvatar();
        CSPreferences.putString(activity, Utils.ImageBaseURL, ppImage);
        if (!(getprofiledata.getAvatar().isEmpty())) {
            Picasso.get().load(getprofiledata.getAvatar()).placeholder(R.drawable.account_dp_profile).into(civ_profile);
        } else {
            civ_profile.setImageResource(R.drawable.account_dp_profile);
            //here we set profile picture
        }
        fullName = getprofiledata.getName();
        if (fullName.contains(" ")) {
            String splitString[] = fullName.split(" ");
            String firstName = splitString[0];
            String lastName = splitString[1];
            et_firstName.setText(firstName);
            et_lastName.setText(lastName);
        } else {
            et_firstName.setHint(fullName);
        }
        et_email.setHint(getprofiledata.getEmail());
        et_mobNumber.setHint(getprofiledata.getPhoneNo());
        mobileNumber = getprofiledata.getPhoneNo();
    }
}