package com.app.LukandaH.Fragments.HomeFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Activities.SearchActivity.SearchActivity;
import com.app.LukandaH.Activities.SearchByParametersActivity.SearchByParametersActivity;
import com.app.LukandaH.Adapters.RvNearMeAdapter;
import com.app.LukandaH.CustomClasses.GPSTracker;
import com.app.LukandaH.Fragments.HomeFragment.Presenter.HomePresenter;
import com.app.LukandaH.Fragments.HomeFragment.View.HomeView;
import com.app.LukandaH.Models.ProfileModels.ProfileData;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeView {
    private String newDate;
    private Activity activity;
    private View view;
    private RecyclerView rv_latestSearch;
    private RecyclerView rv_nearMe;
    private LinearLayout ll_seeAll;
    private TextView tv_checkInDate;
    private TextView tv_checkOutDate;
    private TextView tv_toMainScreen;
    private int mYear;
    private int mMonth;
    private int mdate;
    //    private String userrole;
    private HomePresenter homePresenter;
    private RvNearMeAdapter rvNearMeAdapter;
    private ImageView img_incrGuest;
    private ImageView img_decrGuest;
    private ImageView img_decrRooms;
    private ImageView img_incrRooms;
    private TextView tv_guests;
    private TextView tv_roooms;
    private CircleImageView civ_profile;
    private GPSTracker gpsTracker;
    private double latitude;
    private double longitude;
    private EditText et_search;
    private ImageView img_location;
    private ImageView img_notification;
    private ProgressBar progressBarNearMe;
    private ProgressBar progressBarLocation;
    private Handler handler;
    private String fromDate;
    private int room = 1;
    private int guest = 1;
    private TextView tv_latestSearch;
    private String checkIn;
    private String checkOut;
    private TextView tv_search;

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
//        userrole = getArguments().getString("role");


        handler = new Handler();


        init();
        listeners();

        homePresenter = new HomePresenter(activity, this, rv_nearMe, rv_latestSearch);
        homePresenter.profileMethod();
        homePresenter.latestSearchMethod(tv_latestSearch);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gpsTracker = new GPSTracker(activity);
                if (gpsTracker.canGetLocation()) {
                    gpsTracker.getLocation();
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();
//            Toast.makeText(activity, latitude+", "+ longitude, Toast.LENGTH_SHORT).show();
                }
                progressBarNearMe.getProgress();
                String lng = String.valueOf(longitude);
                String lat = String.valueOf(latitude);
                progressBarNearMe.setVisibility(View.GONE);
                rv_nearMe.setVisibility(View.VISIBLE);
                homePresenter.nearMeHotelsMethod(lng, lat);
            }
        }, 10000);

        Calendar calendar = Calendar.getInstance();
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int m = calendar.get(Calendar.MONTH);
        Month mon = Month.of(m + 1);
        String month = mon.getDisplayName(TextStyle.SHORT, Locale.getDefault());
        String day = String.valueOf(d);
        newDate = day + " " + month;
        tv_checkInDate.setText(newDate);
        tv_checkOutDate.setText(newDate);
        rv_nearMe.setHasFixedSize(true);
        return view;
    }

    private void init() {
        rv_latestSearch = view.findViewById(R.id.rv_latestSearch);
        rv_nearMe = view.findViewById(R.id.rv_nearMe);
        ll_seeAll = view.findViewById(R.id.ll_seeAll);
        tv_checkInDate = view.findViewById(R.id.tv_checkInDate);
        tv_checkOutDate = view.findViewById(R.id.tv_checkOutDate);
        img_incrGuest = view.findViewById(R.id.img_incrGuest);
        img_decrGuest = view.findViewById(R.id.img_decrGuest);
        tv_guests = view.findViewById(R.id.tv_guests);
        img_decrRooms = view.findViewById(R.id.img_decrRooms);
        img_incrRooms = view.findViewById(R.id.img_incrRooms);
        tv_roooms = view.findViewById(R.id.tv_roooms);
        civ_profile = view.findViewById(R.id.civ_profile);
        et_search = view.findViewById(R.id.et_search);
        img_location = view.findViewById(R.id.img_location);
        img_notification = view.findViewById(R.id.img_notification);
        tv_toMainScreen = view.findViewById(R.id.tv_toMainScreen);
        progressBarNearMe = view.findViewById(R.id.progressBarNearMe);
        progressBarLocation = view.findViewById(R.id.progressBarLocation);
        tv_latestSearch = view.findViewById(R.id.tv_latestSearch);
        tv_search = view.findViewById(R.id.tv_search);
    }

    private void listeners() {
        ll_seeAll.setOnClickListener(this);
        tv_checkInDate.setOnClickListener(this);
        tv_checkOutDate.setOnClickListener(this);
        img_incrGuest.setOnClickListener(this);
        img_decrGuest.setOnClickListener(this);
        img_incrRooms.setOnClickListener(this);
        img_decrRooms.setOnClickListener(this);
        img_location.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        tv_search.setOnClickListener(this);
    }
    private void internetCheck() {
        if (Utils.isNetworkConnected(activity) == false) {
            Toast.makeText(activity, "check your/ninternet connection", Toast.LENGTH_SHORT).show();
            progressBarNearMe.setVisibility(View.VISIBLE);
            rv_nearMe.setVisibility(View.GONE);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gpsTracker = new GPSTracker(activity);
                    if (gpsTracker.canGetLocation()) {
                        gpsTracker.getLocation();
                        latitude = gpsTracker.getLatitude();
                        longitude = gpsTracker.getLongitude();
//            Toast.makeText(activity, latitude+", "+ longitude, Toast.LENGTH_SHORT).show();
                    }
                    progressBarNearMe.getProgress();
                    String lng = String.valueOf(longitude);
                    String lat = String.valueOf(latitude);
                    progressBarNearMe.setVisibility(View.GONE);
                    rv_nearMe.setVisibility(View.VISIBLE);
                    homePresenter.nearMeHotelsMethod(lng, lat);
                }
            }, 10000);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if (view == ll_seeAll) {
//            Bundle bundle = new Bundle();
//            bundle.putString("role",userrole);
//            fragment.setArguments(bundle);
            Intent intent = new Intent(activity, SearchActivity.class);
//            intent.putExtra("role", userrole);
            activity.startActivity(intent);
        } else if (view == tv_checkInDate) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mdate = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    fromDate = dayOfMonth + "/" + month  + "/" + year;
                    Month m = Month.of(month + 1);
                    String monthName = m.getDisplayName(TextStyle.SHORT, Locale.getDefault());
                    tv_checkInDate.setText(dayOfMonth + " " + monthName);
                    checkIn = year + "-" + m + "-" + dayOfMonth;
                }
            }, mYear, mMonth, mdate);
            datePickerDialog.show();
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        } else if (view == tv_checkOutDate) {
            if(fromDate == null){
                Toast.makeText(activity, "please first select Check In date", Toast.LENGTH_SHORT).show();
            } else {
                final Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mdate = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Month m = Month.of(month + 1);
                        String monthName = m.getDisplayName(TextStyle.SHORT, Locale.getDefault());
                        tv_checkOutDate.setText(dayOfMonth + " " + monthName);
                        checkOut = year + "-" + m + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mdate);
                String getfromdate = fromDate.trim();
                String getfrom[] = getfromdate.split("/");
                int inYear = Integer.parseInt(getfrom[2]);
                int inMonth = Integer.parseInt(getfrom[1]);
                int inDay = Integer.parseInt(getfrom[0]);
                calendar.set(inYear, inMonth, inDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            }
        } else if (view == img_incrGuest) {
            String maximumGuests = String.valueOf(maxGuests(room));
            String guests = tv_guests.getText().toString();
            if(guests.equals(maximumGuests)){
                Toast.makeText(activity, "Please increase rooms for more", Toast.LENGTH_SHORT).show();
            } else {
                int guestsInInt = Integer.parseInt(guests);
                guestsInInt += 1;
                guests = String.valueOf(guestsInInt);
                tv_guests.setText(guests);
                guest = guestsInInt;
            }
        } else if (view == img_decrGuest) {
            String guests = tv_guests.getText().toString();
            int guestsInInt = Integer.parseInt(guests);
            if (guestsInInt == 1) {
                Toast.makeText(activity, "Minimum guests not less than 1", Toast.LENGTH_SHORT).show();
                guest = guestsInInt;
            } else {
                guestsInInt -= 1;
                guests = String.valueOf(guestsInInt);
                tv_guests.setText(guests);
                guest = guestsInInt;
            }
        } else if (view == img_decrRooms) {
            String rooms = tv_roooms.getText().toString();
            int roomsInInt = Integer.parseInt(rooms);
            if (roomsInInt == 1) {
                Toast.makeText(activity, "Minimum rooms not less than 1", Toast.LENGTH_SHORT).show();
                room = roomsInInt;
            } else {
                roomsInInt -= 1;
                rooms = String.valueOf(roomsInInt);
                tv_roooms.setText(rooms);
                room = roomsInInt;
            }
            tv_guests.setText(String.valueOf(roomsInInt*2));
        } else if (view == img_incrRooms) {
            String rooms = tv_roooms.getText().toString();
            int roomsInInt = Integer.parseInt(rooms);
            roomsInInt += 1;
            rooms = String.valueOf(roomsInInt);
            tv_roooms.setText(rooms);
            room = roomsInInt;
        } else if (view == img_location) {
            Geocoder gcd = new Geocoder(activity, Locale.getDefault());
            progressBarLocation.setVisibility(View.VISIBLE);
            img_location.setVisibility(View.GONE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Address> addresses = null;
                        addresses = gcd.getFromLocation(latitude, longitude, 1);
                        List<Address> finalAddresses = addresses;
                        img_location.setVisibility(View.VISIBLE);
                        progressBarLocation.setVisibility(View.GONE);
                        if (finalAddresses.size() > 0) {
                            String cityName = finalAddresses.get(0).getLocality();
                            et_search.setText(cityName);
                        } else {
                            Toast.makeText(activity, "couldn't find location", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }, 7000);
        } else if (view == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        } else if (view == tv_toMainScreen) {
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        } else if(view == tv_search){
            if(String.valueOf(room) == null){
                room = 1;
            }
            if(String.valueOf(guest) == null){
                guest = 1;
            } if(et_search.getText().toString() == null || et_search.getText().toString().isEmpty() || checkIn == null || checkOut == null){
                Toast.makeText(activity, "Please fill the fields to continue", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(activity, SearchByParametersActivity.class);
                intent.putExtra("cityName", et_search.getText().toString());
                intent.putExtra("checkIn", checkIn);
                intent.putExtra("checkOut", checkOut);
                intent.putExtra("rooms", String.valueOf(room));
                intent.putExtra("adults", String.valueOf(guest));
                startActivity(intent);
            }
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
//        profileImage = CSPreferences.readString(activity, Utils.ImageBaseURL);
        if (!(data.getAvatar().isEmpty())) {
            Picasso.get().load(data.getAvatar()).placeholder(R.drawable.account_dp).into(civ_profile);
        } else {
            civ_profile.setImageResource(R.drawable.account_dp);
        }
    }
    private int maxGuests(int rooms){
        int maxGuest = 2;
        maxGuest = rooms * 2;
        return maxGuest;
    }
}