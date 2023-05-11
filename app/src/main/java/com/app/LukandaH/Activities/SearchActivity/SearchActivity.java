package com.app.LukandaH.Activities.SearchActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Activities.SearchActivity.Presenter.SearchPresenter;
import com.app.LukandaH.Activities.SearchActivity.View.SearchView;
import com.app.LukandaH.Adapters.RvSearchListAdapter;
import com.app.LukandaH.CustomClasses.GPSTracker;
import com.app.LukandaH.Models.HotelsSortByModels.HotelSortByDatum;
import com.app.LukandaH.Models.SearchFilterModels.FilterDatum;
import com.app.LukandaH.Models.SearchModels.SearchDatum;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mohammedalaa.seekbar.DoubleValueSeekBarView;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, SearchView, TextView.OnEditorActionListener {
    boolean flagParking = true;
    boolean flagWifi = true;
    boolean flagPet = true;
    boolean flagKitchen = true;
    boolean flagParkingSend = false;
    boolean flagWifiSend = false;
    boolean flagPetSend = false;
    boolean flagKitchenSend = false;
    boolean flag1 = true;
    boolean flag2 = true;
    boolean flag3 = true;
    boolean flag4 = true;
    boolean flag5 = true;
    GPSTracker gpsTracker;
    int distanceKm;
    private Activity activity;
    private RecyclerView rv_searchResult;
    private ImageView img_filter;
    private ImageView img_sort;
    //    private String userrole;
//    private NearMeHotelsSearchPresenter nearMeHotelsSearchPresenter;
    private RvSearchListAdapter nearMeAdapter;
    //    private FilterPresenter filterPresenter;
    private LocationManager locationManager;
    private double latitude;
    private double longitude;
    private String paymentMode = "offline";
    private int rating = 0;
    private EditText et_search;
    private SearchPresenter searchPresenter;
    private ImageView img_notification;
    private TextView tv_toMainScreen;
    private String sortData;
    private boolean flagpopular = true;
    private boolean isPopular;
    private boolean flagPriceL = true;
    private boolean flagPriceH = true;
    private String price = "lowestFirst";
    private String getUserRating = "1";

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        userrole = getIntent().getStringExtra("role");
        activity = this;
        searchPresenter = new SearchPresenter(activity, this);
        gpsTracker = new GPSTracker(activity);
        if (gpsTracker.canGetLocation()) {
            gpsTracker.getLocation();
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
//            Toast.makeText(activity, latitude+", "+ longitude, Toast.LENGTH_SHORT).show();
        }
        init();
        listeners();
        searchPresenter.getHotelsMethod(rv_searchResult);
    }

    private void init() {
        rv_searchResult = findViewById(R.id.rv_searchResult);
        img_filter = findViewById(R.id.img_filter);
        img_sort = findViewById(R.id.img_sort);
        et_search = findViewById(R.id.et_search);
        img_notification = findViewById(R.id.img_notification);
        tv_toMainScreen = findViewById(R.id.tv_toMainScreen);
    }

    private void listeners() {
        img_filter.setOnClickListener(this);
        img_sort.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        et_search.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == img_filter) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity, R.style.SheetDialog);
            bottomSheetDialog.setContentView(R.layout.bottomsheet_search_filter);
            LinearLayout ll_rating1 = bottomSheetDialog.findViewById(R.id.ll_rating1);
            LinearLayout ll_rating2 = bottomSheetDialog.findViewById(R.id.ll_rating2);
            LinearLayout ll_rating3 = bottomSheetDialog.findViewById(R.id.ll_rating3);
            LinearLayout ll_rating4 = bottomSheetDialog.findViewById(R.id.ll_rating4);
            LinearLayout ll_rating5 = bottomSheetDialog.findViewById(R.id.ll_rating5);
            ImageView img_star1 = bottomSheetDialog.findViewById(R.id.img_star1);
            ImageView img_star2 = bottomSheetDialog.findViewById(R.id.img_star2);
            ImageView img_star3 = bottomSheetDialog.findViewById(R.id.img_star3);
            ImageView img_star4 = bottomSheetDialog.findViewById(R.id.img_star4);
            ImageView img_star5 = bottomSheetDialog.findViewById(R.id.img_star5);
            TextView tv_1km = bottomSheetDialog.findViewById(R.id.tv_1km);
            TextView tv_2km = bottomSheetDialog.findViewById(R.id.tv_2km);
            TextView tv_5km = bottomSheetDialog.findViewById(R.id.tv_5km);
            TextView tv_payAtHotel = bottomSheetDialog.findViewById(R.id.tv_payAtHotel);
            TextView tv_reset = bottomSheetDialog.findViewById(R.id.tv_reset);
            ImageView img_parking = bottomSheetDialog.findViewById(R.id.img_parking);
            ImageView img_wifi = bottomSheetDialog.findViewById(R.id.img_wifi);
            ImageView img_pet = bottomSheetDialog.findViewById(R.id.img_pet);
            ImageView img_kitchen = bottomSheetDialog.findViewById(R.id.img_kitchen);
            DoubleValueSeekBarView ratingBar = bottomSheetDialog.findViewById(R.id.ratingBar);
            SwitchCompat switchCompat = bottomSheetDialog.findViewById(R.id.switchCompat);
            Button btn_showResult = bottomSheetDialog.findViewById(R.id.btn_showResult);
            String yellow = "#FFA132";
            String graiesh5 = "#67686D";
            tv_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img_star1.setColorFilter(Color.parseColor(graiesh5));
                    img_star2.setColorFilter(Color.parseColor(graiesh5));
                    img_star3.setColorFilter(Color.parseColor(graiesh5));
                    img_star4.setColorFilter(Color.parseColor(graiesh5));
                    img_star5.setColorFilter(Color.parseColor(graiesh5));
                    switchCompat.setChecked(false);
                    ratingBar.setCurrentMinValue(50);
                    ratingBar.setCurrentMaxValue(350);
                    img_parking.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                    img_wifi.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                    img_pet.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                    img_kitchen.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                    tv_1km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_2km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_5km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_payAtHotel.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    flagParkingSend = false;
                    flagWifiSend = false;
                    flagPetSend = false;
                    flagKitchenSend = false;
                    rating = 0;
                }
            });
            tv_payAtHotel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_payAtHotel.setBackground(getResources().getDrawable(R.drawable.solid_skyblue_radius18));
                    paymentMode = "offline";
                }
            });
            tv_1km.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_1km.setBackground(getResources().getDrawable(R.drawable.solid_skyblue_radius18));
                    tv_2km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_5km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    distanceKm = 1;
                }
            });

            tv_2km.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_1km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_2km.setBackground(getResources().getDrawable(R.drawable.solid_skyblue_radius18));
                    tv_5km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    distanceKm = 2;
                }
            });
            tv_5km.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_1km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_2km.setBackground(getResources().getDrawable(R.drawable.solid_black25_radius));
                    tv_5km.setBackground(getResources().getDrawable(R.drawable.solid_skyblue_radius18));
                    distanceKm = 5;
                }
            });

            ll_rating1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img_star1.setColorFilter(Color.parseColor(yellow));
                    img_star2.setColorFilter(Color.parseColor(graiesh5));
                    img_star3.setColorFilter(Color.parseColor(graiesh5));
                    img_star4.setColorFilter(Color.parseColor(graiesh5));
                    img_star5.setColorFilter(Color.parseColor(graiesh5));
                    rating = 1;
//                    Toast.makeText(SearchActivity.this, distanceKm+"", Toast.LENGTH_SHORT).show();
                }
            });
            ll_rating2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    img_star1.setColorFilter(Color.parseColor(yellow));
                    img_star2.setColorFilter(Color.parseColor(yellow));
                    img_star3.setColorFilter(Color.parseColor(graiesh5));
                    img_star4.setColorFilter(Color.parseColor(graiesh5));
                    img_star5.setColorFilter(Color.parseColor(graiesh5));
                    rating = 2;
                }
            });
            ll_rating3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img_star1.setColorFilter(Color.parseColor(yellow));
                    img_star2.setColorFilter(Color.parseColor(yellow));
                    img_star3.setColorFilter(Color.parseColor(yellow));
                    img_star4.setColorFilter(Color.parseColor(graiesh5));
                    img_star5.setColorFilter(Color.parseColor(graiesh5));
                    rating = 3;
                }
            });
            ll_rating4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img_star1.setColorFilter(Color.parseColor(yellow));
                    img_star2.setColorFilter(Color.parseColor(yellow));
                    img_star3.setColorFilter(Color.parseColor(yellow));
                    img_star4.setColorFilter(Color.parseColor(yellow));
                    img_star5.setColorFilter(Color.parseColor(graiesh5));
                    rating = 4;
                }
            });
            ll_rating5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img_star1.setColorFilter(Color.parseColor(yellow));
                    img_star2.setColorFilter(Color.parseColor(yellow));
                    img_star3.setColorFilter(Color.parseColor(yellow));
                    img_star4.setColorFilter(Color.parseColor(yellow));
                    img_star5.setColorFilter(Color.parseColor(yellow));
                    rating = 5;
                }
            });

            img_parking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flagParking) {
                        img_parking.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        flagParkingSend = true;
                    } else {
                        img_parking.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        flagParkingSend = false;
                    }
                    flagParking = !flagParking;
                }
            });

            img_wifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flagWifi) {
                        img_wifi.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        flagWifiSend = true;
                    } else if (flagWifi == false) {
                        img_wifi.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        flagWifiSend = false;
                    }
                    flagWifi = !flagWifi;
                }
            });

            img_pet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flagPet) {
                        img_pet.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        flagPetSend = true;
                    } else {
                        img_pet.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        flagPetSend = false;
                    }
                    flagPet = !flagPet;
                }
            });

            img_kitchen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flagKitchen) {
                        img_kitchen.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        flagKitchenSend = true;
                    } else {
                        img_kitchen.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        flagKitchenSend = false;
                    }
                    flagKitchen = !flagKitchen;
                }
            });
            btn_showResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(activity, ""+ratingBar.getCurrentMinValue()+" "+ratingBar.getCurrentMaxValue(), Toast.LENGTH_SHORT).show();
                    searchPresenter.filterMethod(rating, ratingBar.getCurrentMinValue(), ratingBar.getCurrentMaxValue(),
                            paymentMode, switchCompat.isChecked(), flagKitchenSend, flagParkingSend, flagPetSend, latitude,
                            longitude, distanceKm, rv_searchResult);
                    bottomSheetDialog.hide();
                }
            });
            bottomSheetDialog.show();
        } else if (view == img_sort) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity, R.style.SheetDialog);
            bottomSheetDialog.setContentView(R.layout.bottomsheet_sortby);

            TextView tv_populatrity = bottomSheetDialog.findViewById(R.id.tv_populatrity);
            TextView tv_done = bottomSheetDialog.findViewById(R.id.tv_done);
            Spinner spinner_userratings = bottomSheetDialog.findViewById(R.id.spinner_userratings);
            TextView tv_highestPriceFirst = bottomSheetDialog.findViewById(R.id.tv_highestPriceFirst);
            TextView tv_lowestPriceFirst = bottomSheetDialog.findViewById(R.id.tv_lowestPriceFirst);
            tv_populatrity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(flagpopular){
                        tv_populatrity.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        isPopular = true;
                    } else{
                        tv_populatrity.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        isPopular = false;
                    }
                    flagpopular = !flagpopular;
                }
            });
            tv_highestPriceFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        tv_highestPriceFirst.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        tv_lowestPriceFirst.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        price = "highestFirst";
                }
            });
            tv_lowestPriceFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        tv_highestPriceFirst.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_white_round10dp));
                        tv_lowestPriceFirst.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_skyblue_round10dp));
                        price = "highestFirst";
                }
            });
            //            String[] priceRangeNo = {"Under $200", "Under $500", "Under $700", "Under $1000"};
            String[] userRatingsNo = {"Select rating here","1", "2", "3", "4", "5"};

//            Spinner spinner_priceRange = bottomSheetDialog.findViewById(R.id.spinner_priceRange);
//            ArrayAdapter price = new ArrayAdapter(activity, R.layout.spinneritem, R.id.tv_text, priceRangeNo);
//            spinner_priceRange.setAdapter(price);

            ArrayAdapter rating = new ArrayAdapter(activity, R.layout.spinneritem, R.id.tv_text, userRatingsNo);
//            rating.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner_userratings.setAdapter(rating);
            spinner_userratings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    getUserRating = spinner_userratings.getItemAtPosition(i).toString();
//                    Toast.makeText(SearchActivity.this, getUserRating, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            getUserRating = spinner_userratings.getSelectedItem().toString();
//            Toast.makeText(activity, getUserRating, Toast.LENGTH_SHORT).show();
            tv_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchPresenter.hotelSortByMethod(rv_searchResult);
                    bottomSheetDialog.hide();
                }

            });
            bottomSheetDialog.show();
        } else if (view == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }else if(view == tv_toMainScreen){
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showMessage(Activity activity, String msg) {
//        Utils.showMessage(activity,msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getSupportFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchPresenter.searchMethod(et_search, rv_searchResult);
            performSearch();
            return true;
        }
        return false;
    }

    private void performSearch() {
        et_search.clearFocus();
        InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
    }
}