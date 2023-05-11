package com.app.LukandaH.Activities.HotelViewActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.app.LukandaH.Activities.ChatActivity.ChatActivity;
import com.app.LukandaH.Activities.FullScreenImageView.FullScreenImageViewActivity;
import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.HotelViewActivity.Presenter.HotelViewPresenter;
import com.app.LukandaH.Activities.HotelViewActivity.View.HotelViewView;
import com.app.LukandaH.Activities.ProceedToPaymentActivity;
import com.app.LukandaH.Activities.RatingsActivity.ReviewActivity;
import com.app.LukandaH.Adapters.VpHotelImagesAdapter;
import com.app.LukandaH.Models.HotelViewModels.HotelViewData;
import com.app.LukandaH.Models.VpHotelImagesModel;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class HotelViewActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, HotelViewView {
    private boolean flag = true;
    private Activity activity;
    //    private RecyclerView rv_offersDeals;
    private ImageView img_back;
    private ImageView img_chat;
    private ImageView img_like;
    private TextView tv_payment;
    private ViewPager vp_imageVp;
    private ImageView img_next;
    private ImageView img_previous;
    private CircleIndicator circleIndicator;
    private GoogleMap mMap;
    //    private ActivityMapsBinding binding;
    private int currentItem;
    private ArrayList<VpHotelImagesModel> models;
    private HotelViewPresenter hotelViewPresenter;
    private TextView tv_hotelNameHeader;
    private TextView tv_hotelName;
    private TextView tv_price;
    private String cityName;
    private String hotelid;
    private double lat;
    private double longh;
    private TextView tv_chekInDate;
    private TextView tv_chekOutDate;
    private int checkInYear;
    private int checkInMonth;
    private int checkInDay;
    private int checkOutYear;
    private int checkOutMonth;
    private int checkOutDay;
    private LinearLayout ll_showCheckDates;
    private String date;
    private String outDate;
    private String noOfRooms;
    private String noOfGuests;
    private RelativeLayout rl_reviews;
    private TextView tv_reviewscount;
    private TextView tv_rent;
    private TextView tv_viewAllImages;
    private String checkIn;
    private String checkOut;
    private String sendRooms;
    private String sendAdults;
    private int roomsInInt;
    private String fromDate;
    private TextView tv_offers;
    private LinearLayout ll_offer;
    private TextView tv_offerName;
    private LinearLayout ll_parking;
    private LinearLayout ll_bath;
    private LinearLayout ll_bar;
    private LinearLayout ll_gym;
    private LinearLayout ll_wifi;
    private LinearLayout ll_more;
    private TextView tv_aminities;
    private int price;
    private int room = 1;
    private TextView tv_timing;
    private int totalPrice;
    private int roomsInt = 1;
    private int hotelPrice;
    private String offer;
    private int afterOff;
    private String offerAndDeals;
    //    private String userrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        activity = this;
        hotelViewPresenter = new HotelViewPresenter(activity, this);

        hotelid = getIntent().getStringExtra("hotelid");
        lat = getIntent().getDoubleExtra("lat", lat);
        longh = getIntent().getDoubleExtra("longh", longh);

//        userrole = getIntent().getStringExtra("role");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) activity);

        init();
        listeners();
//        Toast.makeText(activity, ""+latlong, Toast.LENGTH_SHORT).show();
        hotelViewPresenter.hotelViewMethod(hotelid);

        models = new ArrayList<>();
        models.add(new VpHotelImagesModel(R.drawable.img20));
        models.add(new VpHotelImagesModel(R.drawable.img20));
        models.add(new VpHotelImagesModel(R.drawable.img20));
        models.add(new VpHotelImagesModel(R.drawable.img20));
        VpHotelImagesAdapter vpHotelImagesAdapter = new VpHotelImagesAdapter(activity, models);
        vp_imageVp.setCurrentItem(1, true);
//        vp_imageVp.setPagingEnabled(true);
        vp_imageVp.setAdapter(vpHotelImagesAdapter);
        viewPagerSwiping();
        circleIndicator.setViewPager(vp_imageVp);
        img_previous.setVisibility(View.INVISIBLE);
    }

    private void init() {
//        rv_offersDeals = findViewById(R.id.rv_offersDeals);
        img_back = findViewById(R.id.img_back);
        img_chat = findViewById(R.id.img_chat);
        tv_payment = findViewById(R.id.tv_payment);
        img_like = findViewById(R.id.img_like);
        vp_imageVp = findViewById(R.id.vp_imageVp);
        circleIndicator = findViewById(R.id.circleIndicator);
        img_next = findViewById(R.id.img_next);
        img_previous = findViewById(R.id.img_previous);
        tv_hotelNameHeader = findViewById(R.id.tv_hotelNameHeader);
        tv_hotelName = findViewById(R.id.tv_hotelName);
        tv_price = findViewById(R.id.tv_price);
        tv_chekInDate = findViewById(R.id.tv_chekInDate);
        tv_chekOutDate = findViewById(R.id.tv_chekOutDate);
        ll_showCheckDates = findViewById(R.id.ll_showCheckDates);
        rl_reviews = findViewById(R.id.rl_reviews);
        tv_reviewscount = findViewById(R.id.tv_reviewscount);
        tv_rent = findViewById(R.id.tv_rent);
        tv_viewAllImages = findViewById(R.id.tv_viewAllImages);
        tv_offers = findViewById(R.id.tv_offers);
        ll_offer = findViewById(R.id.ll_offer);
        tv_offerName = findViewById(R.id.tv_offerName);
        ll_parking = findViewById(R.id.ll_parking);
        ll_bath = findViewById(R.id.ll_bath);
        ll_bar = findViewById(R.id.ll_bar);
        ll_gym = findViewById(R.id.ll_gym);
        ll_wifi = findViewById(R.id.ll_wifi);
        ll_more = findViewById(R.id.ll_more);
        tv_aminities = findViewById(R.id.tv_aminities);
        tv_timing = findViewById(R.id.tv_timing);
    }

    private void listeners() {
        img_back.setOnClickListener(this);
        img_chat.setOnClickListener(this);
        tv_payment.setOnClickListener(this);
        img_next.setOnClickListener(this);
        img_previous.setOnClickListener(this);
        ll_showCheckDates.setOnClickListener(this);
        rl_reviews.setOnClickListener(this);
        tv_viewAllImages.setOnClickListener(this);
        img_like.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(activity, HomeActivity.class);
//        intent.putExtra("role", userrole);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng;

//        if(lat && longh != 0) {
        Geocoder gcd = new Geocoder(activity, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, longh, 1);
            if (addresses.size() > 0) {
                String citiName = addresses.get(0).getLocality();
                cityName = citiName;
                latLng = new LatLng(lat, longh);
                mMap.addMarker(new MarkerOptions().position(latLng).title(cityName));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            } else {
                cityName = "MSPL";
                latLng = new LatLng(30.7371, 76.6807);
                mMap.addMarker(new MarkerOptions().position(latLng).title(cityName));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == img_back) {
            finish();
//            Intent intent = new Intent(activity, HomeActivity.class);
//            startActivity(intent);
        } else if (view == img_chat) {
            Intent intent = new Intent(activity, ChatActivity.class);
            intent.putExtra("hotelid", hotelid);
//            intent.putExtra("role",` userrole);
            startActivity(intent);
        } else if (view == img_next) {
            Toast.makeText(activity, String.valueOf(vp_imageVp.getCurrentItem()), Toast.LENGTH_SHORT).show();
            vp_imageVp.setCurrentItem(getItem(+1), true);
            vpButtonValidation(vp_imageVp);
            int curretItem = Integer.parseInt(String.valueOf(vp_imageVp.getCurrentItem()));
            if (curretItem == 0) {
                img_previous.setVisibility(View.INVISIBLE);
                img_next.setVisibility(View.VISIBLE);
            } else if (curretItem == models.size() - 1) {
                img_previous.setVisibility(View.VISIBLE);
                img_next.setVisibility(View.INVISIBLE);
            }
        } else if (view == img_previous) {
            vp_imageVp.setCurrentItem(getItem(-1), true);
            vpButtonValidation(vp_imageVp);
        } else if (view == ll_showCheckDates) {
            showDialog();
        } else if (view == rl_reviews) {
            Intent intent = new Intent(activity, ReviewActivity.class);
            intent.putExtra("hotelid", hotelid);
            startActivity(intent);
        } else if (view == tv_viewAllImages) {
            Intent intent = new Intent(activity, FullScreenImageViewActivity.class);
            startActivity(intent);
        } else if (view == tv_payment) {
            if (checkIn == null || checkOut == null) {
                Toast.makeText(activity, "Please select dates", Toast.LENGTH_SHORT).show();
            } else {
                if (sendAdults == null) {
                    sendAdults = "1";
                }
                if (sendRooms == null) {
                    sendRooms = "1";
                }
                Intent intent = new Intent(activity, ProceedToPaymentActivity.class);
                intent.putExtra("rooms", sendRooms);
                intent.putExtra("adults", sendAdults);
                intent.putExtra("checkIn", checkIn);
                intent.putExtra("checkOut", checkOut);
                intent.putExtra("hotelId", hotelid);
                intent.putExtra("totalPrice", String.valueOf(totalPrice));
                intent.putExtra("hotelPrice", String.valueOf(hotelPrice));
                intent.putExtra("offer", offer);
//            intent.putExtra("role", userrole);
                startActivity(intent);
            }
        } else if (view == img_like) {
            if (flag == true) {
                img_like.setImageResource(R.drawable.like_red);
                hotelViewPresenter.addToFavMethod(hotelid, flag);
            } else {
                img_like.setImageResource(R.drawable.like_vector);
                hotelViewPresenter.addToFavMethod(hotelid, flag);
            }
            flag = !flag;
        }
    }

    private int getItem(int i) {
        return vp_imageVp.getCurrentItem() + i;
    }

    private boolean vpButtonValidation(ViewPager viewPager) {
        currentItem = Integer.parseInt(String.valueOf(viewPager.getCurrentItem()));
        if (currentItem == 0) {
            img_previous.setVisibility(View.INVISIBLE);
            img_next.setVisibility(View.VISIBLE);
        } else if (currentItem > 0 && currentItem < models.size() - 1) {
            img_previous.setVisibility(View.VISIBLE);
            img_next.setVisibility(View.VISIBLE);
        } else if (currentItem == models.size() - 1) {
            img_previous.setVisibility(View.VISIBLE);
            img_next.setVisibility(View.INVISIBLE);
        }
        return true;
    }

    @Override
    public void showMessage(Activity activity, String msg) {
//        Utils.showMessage(activity, msg);
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
    public void setData(Activity activity, HotelViewData data) {
        tv_hotelNameHeader.setText(data.getName());
        tv_hotelName.setText(data.getName());
        hotelPrice = data.getPrice();
        if (data.getIsWifiAvailable() || data.getIsParkingAvailable()) {
            tv_aminities.setVisibility(View.VISIBLE);
        } else {
            tv_aminities.setVisibility(View.GONE);
        }
        if (data.getIsParkingAvailable() == true) {
            ll_parking.setVisibility(View.VISIBLE);
        } else {
            ll_parking.setVisibility(View.GONE);
        }
        if (data.getIsWifiAvailable() == true) {
            ll_wifi.setVisibility(View.VISIBLE);
        } else {
            ll_wifi.setVisibility(View.GONE);
        }

        tv_price.setText(String.valueOf(hotelPrice));
        offerAndDeals = data.getOffers();
        if (offerAndDeals.isEmpty()) {
            ll_offer.setVisibility(View.GONE);
            tv_offers.setVisibility(View.GONE);
            String prise = String.valueOf(data.getPrice());
            tv_rent.setText("$" + prise);
            offer = "None";
            price = Integer.parseInt(prise);
        } else {
            ll_offer.setVisibility(View.VISIBLE);
            tv_offers.setVisibility(View.VISIBLE);
            tv_offerName.setText(offerAndDeals);
            int originalPrice = data.getPrice();
            String offerOff = offerAndDeals.substring(0, 2);
            int priceOff = Integer.parseInt(offerOff);
            afterOff = ((originalPrice * priceOff) / 100);
            tv_rent.setText("$" + afterOff);
            price = afterOff;
            offer = offerAndDeals;
        }
    }

    private void viewPagerSwiping() {
        vp_imageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    img_previous.setVisibility(View.INVISIBLE);
                    img_next.setVisibility(View.VISIBLE);
                } else if (position > 0 && position < models.size() - 1) {
                    img_previous.setVisibility(View.VISIBLE);
                    img_next.setVisibility(View.VISIBLE);
                } else if (position == models.size() - 1) {
                    img_previous.setVisibility(View.VISIBLE);
                    img_next.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.check_date_dialog);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView checkInDate = dialog.findViewById(R.id.checkInDate);
        TextView checkOutDate = dialog.findViewById(R.id.checkOutDate);
        ImageView img_decrRooms = dialog.findViewById(R.id.img_decrRooms);
        ImageView img_incrRooms = dialog.findViewById(R.id.img_incrRooms);
        ImageView img_decrGuests = dialog.findViewById(R.id.img_decrGuests);
        ImageView img_incrGuests = dialog.findViewById(R.id.img_incrGuests);
        TextView tv_noOfRooms = dialog.findViewById(R.id.tv_noOfRooms);
        TextView tv_noOfGuests = dialog.findViewById(R.id.tv_noOfGuests);
        LinearLayout ll_check_in = dialog.findViewById(R.id.ll_check_in);
        LinearLayout ll_check_out = dialog.findViewById(R.id.ll_check_out);
        ll_check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                checkInYear = calendar.get(Calendar.YEAR);
                checkInMonth = calendar.get(Calendar.MONTH);
                checkInDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        fromDate = dayOfMonth + "/" + month + "/" + year;
                        Month m = Month.of(month + 1);
                        String monthName = m.getDisplayName(TextStyle.SHORT, Locale.getDefault());
                        checkInDate.setText(dayOfMonth + " " + monthName);
                        date = dayOfMonth + " " + monthName;
                        checkIn = year + "-" + m + "-" + dayOfMonth;
                    }
                }, checkInYear, checkInMonth, checkInDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            }
        });
        ll_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromDate == null) {
                    Toast.makeText(activity, "please first select Check In date", Toast.LENGTH_SHORT).show();
                } else {
                    final Calendar calendar = Calendar.getInstance();
                    checkOutYear = calendar.get(Calendar.YEAR);
                    checkOutMonth = calendar.get(Calendar.MONTH);
                    checkOutDay = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            Month m = Month.of(month + 1);
                            String monthName = m.getDisplayName(TextStyle.SHORT, Locale.getDefault());
                            checkOutDate.setText(dayOfMonth + " " + monthName);
                            outDate = dayOfMonth + " " + monthName;
                            checkOut = year + "-" + m + "-" + dayOfMonth;
                        }
                    }, checkOutYear, checkOutMonth, checkOutDay);
                    String getfromdate = fromDate.trim();
                    String[] getfrom = getfromdate.split("/");
                    int inYear = Integer.parseInt(getfrom[2]);
                    int inMonth = Integer.parseInt(getfrom[1]);
                    int inDay = Integer.parseInt(getfrom[0]);
                    calendar.set(inYear, inMonth, inDay);
                    datePickerDialog.show();
                    datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                }
            }
        });
        img_decrRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rooms = tv_noOfRooms.getText().toString();
                roomsInInt = Integer.parseInt(rooms);
                if (roomsInInt == 1) {
                    rooms = "1";
                    noOfRooms = rooms;
                    room = roomsInInt;
                    Toast.makeText(activity, "Rooms should not be less than one", Toast.LENGTH_SHORT).show();
                } else {
                    roomsInInt -= 1;
                    rooms = String.valueOf(roomsInInt);
                    noOfRooms = rooms;
                    tv_noOfRooms.setText(rooms);
                    room = roomsInInt;
                }
                sendRooms = rooms;
                tv_noOfGuests.setText(String.valueOf(roomsInInt * 2));
            }
        });
        img_incrRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rooms = tv_noOfRooms.getText().toString();
                int roomsInInt = Integer.parseInt(rooms);
                roomsInInt += 1;
                rooms = String.valueOf(roomsInInt);
                noOfRooms = rooms;
                tv_noOfRooms.setText(rooms);
                sendRooms = rooms;
                room = roomsInInt;
            }
        });
        img_decrGuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guests = tv_noOfGuests.getText().toString();
                int guestsInInt = Integer.parseInt(guests);
                if (guestsInInt == 1) {
                    noOfGuests = "1";
                    Toast.makeText(activity, "Guests should not be less than one", Toast.LENGTH_SHORT).show();
                } else {
                    guestsInInt -= 1;
                    guests = String.valueOf(guestsInInt);
                    noOfGuests = guests;
                    tv_noOfGuests.setText(guests);
                }
                sendAdults = guests;
            }
        });
        img_incrGuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maxGuests = String.valueOf(maxGuests(room));
                String guests = tv_noOfGuests.getText().toString();
                if (guests.equals(maxGuests)) {
                    Toast.makeText(activity, "Please increase rooms for more", Toast.LENGTH_SHORT).show();
                } else {
                    int guestsInInt = Integer.parseInt(guests);
                    guestsInInt += 1;
                    guests = String.valueOf(guestsInInt);
                    noOfGuests = guests;
                    tv_noOfGuests.setText(guests);
                    sendAdults = guests;
                }
            }
        });

        Button btn_save = dialog.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(activity, date, Toast.LENGTH_SHORT).show();

                tv_chekInDate.setText(date);
                tv_chekOutDate.setText(outDate);
                if (checkIn == null || checkOut == null) {
                    Toast.makeText(activity, "Please select dates", Toast.LENGTH_SHORT).show();
                } else {
                    if (sendAdults == null) {
                        sendAdults = "1";
                    }
                    if (sendRooms == null) {
                        sendRooms = "1";
                    }
                    setRoomsAndDays();

                    dialog.dismiss();
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

    private int maxGuests(int rooms) {
        int maxGuest = 2;
        maxGuest = rooms * 2;
        return maxGuest;
    }

    private int noOfDaysInMonth(String month) {
        int days = 30;
        if (month.equals("Jan")) {
            return days = 30;
        } else if (month.equals("Feb")) {
            return days = 28;
        } else if (month.equals("Mar")) {
            return days = 31;
        } else if (month.equals("Apr")) {
            return days = 30;
        } else if (month.equals("May")) {
            return days = 31;
        } else if (month.equals("Jun")) {
            return days = 30;
        } else if (month.equals("Jul")) {
            return days = 31;
        } else if (month.equals("Aug")) {
            return days = 31;
        } else if (month.equals("Sep")) {
            return days = 30;
        } else if (month.equals("Oct")) {
            return days = 31;
        } else if (month.equals("Nov")) {
            return days = 30;
        } else if (month.equals("Dec")) {
            return days = 31;
        }
        return days;
    }

    private void setRoomsAndDays() {
        String[] inDates = date.split(" ");
        String inDay = inDates[0];
        String inMonth = inDates[1];

        String[] outDates = outDate.split(" ");
        String outDay = outDates[0];
        String outMonth = outDates[1];

        int noOfDays = 1;
        if (outMonth.equals(inMonth)) {
            if (inDay.equals(outDay)) {
                noOfDays = 1;
            } else {
                noOfDays = Integer.parseInt(outDay) - Integer.parseInt(inDay);
            }
        } else {
            int daysInCheckInMonth = noOfDaysInMonth(inMonth);
            int daysInCheckOutMonth = noOfDaysInMonth(outMonth);
            int outMonthy = daysInCheckOutMonth - Integer.parseInt(outDay);
            int daysInCheckOutMonthForBooking = daysInCheckOutMonth - outMonthy;
            int daysInCheckInMonthForBooking = daysInCheckInMonth - Integer.parseInt(inDay);
            noOfDays = daysInCheckInMonthForBooking + daysInCheckOutMonthForBooking;
        }
        if (sendRooms.equals(null)) {
            sendRooms = "1";
            roomsInt = Integer.parseInt(sendRooms);
        } else {
            roomsInt = Integer.parseInt(sendRooms);
        }
        if (noOfDays == 1) {
            if (roomsInt == 1) {
                tv_timing.setText("for " + roomsInt + " room/" + noOfDays + " Night");
            } else {
                tv_timing.setText("for " + roomsInt + " rooms/" + noOfDays + " Night");
            }
        } else {
            if (roomsInt == 1) {
                tv_timing.setText("for " + roomsInt + " room/" + noOfDays + " Nights");
            } else {
                tv_timing.setText("for " + roomsInt + " rooms/" + noOfDays + " Nights");
            }
        }
        totalPrice = price;
        if (checkIn == null || checkOut == null) {
            Toast.makeText(activity, "Please select dates and rooms", Toast.LENGTH_SHORT).show();
        } else {
            if (sendAdults == null) {
                sendAdults = "1";
            }
            if (sendRooms == null) {
                sendRooms = "1";
            }
            if (sendRooms.equals(null)) {
                sendRooms = "1";
                roomsInt = Integer.parseInt(sendRooms);
                totalPrice *= roomsInt * noOfDays;
                tv_rent.setText("$" + totalPrice);
            } else {
                roomsInt = Integer.parseInt(sendRooms);
                totalPrice *= roomsInt * noOfDays;
                tv_rent.setText("$" + totalPrice);
            }
        }
    }
}