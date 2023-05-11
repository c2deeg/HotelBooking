package com.app.LukandaH.Adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.CustomClasses.DistanceCalc;
import com.app.LukandaH.CustomClasses.GPSTracker;
import com.app.LukandaH.Fragments.BookedHotelFragment.Presenter.BookHotelPresenter;
import com.app.LukandaH.Models.UpcomingBookingsModels.UpcomingBookingsDatum;
import com.app.LukandaH.R;

import java.io.IOException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RvUpcomingHotelsAdapter extends RecyclerView.Adapter<RvUpcomingHotelsAdapter.ViewHolder> {

    private final Activity activity;
    private final List<UpcomingBookingsDatum> data;
    private final BookHotelPresenter presenter;
    String hotelBookingId;
    private double lat1;
    private double long1;
    private int checkInYear;
    private int checkInMonth;
    private int checkInDay;
    private String fromDate;
    private String inDate;
    private String outDate;
    private int checkOutYear;
    private int checkOutMonth;
    private int checkOutDay;
    private int roomsInInt;
    private String noOfRooms;
    private String sendRooms;
    private String noOfGuests;
    private String sendAdults;
    private String checkIn;
    private String checkOut;
    private int room = 1;

    public RvUpcomingHotelsAdapter(Activity activity, List<UpcomingBookingsDatum> data, BookHotelPresenter bookHotelPresenter) {
        this.activity = activity;
        this.data = data;
        this.presenter = bookHotelPresenter;
    }

    @NonNull
    @Override
    public RvUpcomingHotelsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_upcomingbookings_list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvUpcomingHotelsAdapter.ViewHolder holder, int position) {
        holder.tv_hotelName.setText(data.get(position).getHotel().getName());
        holder.tv_price.setText(String.valueOf(data.get(position).getHotel().getPrice()));
        holder.tv_noOfRooms.setText(data.get(position).getRooms().toString());
        holder.tv_noOfPeoples.setText(String.valueOf(data.get(position).getAdults()));
        String checkinMonth = data.get(position).getCheckIn().substring(5, 7);
        String checkinDate = data.get(position).getCheckIn().substring(8, 10);
        String checkoutMonth = data.get(position).getCheckOut().substring(5, 7);
        String checkoutDate = data.get(position).getCheckOut().substring(8, 10);
        holder.tv_checkInDate.setText(checkinDate + " " + getMonth(checkinMonth));
        holder.tv_checkOutDate.setText(checkoutDate + " " + getMonth(checkoutMonth));
        hotelBookingId = data.get(position).getId();
        holder.tv_changeBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                inDate = dayOfMonth + " " + monthName;
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
//                    tv_noOfGuests.setText(String.valueOf(room*2));
                            Toast.makeText(activity, "Rooms should not be less than one", Toast.LENGTH_SHORT).show();
                        } else {
                            roomsInInt -= 1;
                            rooms = String.valueOf(roomsInInt);
                            noOfRooms = rooms;
                            tv_noOfRooms.setText(rooms);
                            room = roomsInInt;
                            tv_noOfGuests.setText(String.valueOf(room * 2));
                        }
                        sendRooms = rooms;
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
                        String maxGuest = String.valueOf(maxGuests(room));
                        String guests = tv_noOfGuests.getText().toString();
                        if (guests.equals(maxGuest)) {
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
                        checkInDate.setText(inDate);
                        checkOutDate.setText(outDate);
                        if (checkIn == null || checkOut == null) {
                            Toast.makeText(activity, "Please select dates", Toast.LENGTH_SHORT).show();
                        } else {
                            if (sendAdults == null) {
                                sendAdults = "1";
                            }
                            if (sendRooms == null) {
                                sendRooms = "1";
                            }
                            presenter.changeBookingMethod(hotelBookingId, checkIn, checkOut, Integer.parseInt(sendRooms), Integer.parseInt(sendAdults));
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
        });
        holder.tv_cancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.cancelbookingdialog);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                Button btn_yes = dialog.findViewById(R.id.btn_yes);

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.cancelBooking(hotelBookingId);
                        dialog.dismiss();
                    }
                });

                Button btn_no = dialog.findViewById(R.id.btn_no);
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        GPSTracker gpsTracker = new GPSTracker(activity);
        if (gpsTracker.canGetLocation()) {
            gpsTracker.getLocation();
            lat1 = gpsTracker.getLatitude();
            long1 = gpsTracker.getLongitude();
        }
        Geocoder gcd = new Geocoder(activity, Locale.getDefault());
        List<Float> locationCoordinates = data.get(position).getHotel().getLocation().getCoordinates();
        float lat = locationCoordinates.get(0);
        float lng = locationCoordinates.get(1);
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
                String citiName = addresses.get(0).getLocality();
                holder.tv_placeName.setText(citiName);
                DistanceCalc distanceCalc = new DistanceCalc();
                String distance = String.valueOf(distanceCalc.haversine(lat1, long1, lat, lng));
                holder.tv_location.setText(distance.substring(0, 3) + " km to " + citiName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.get(position).getRooms() <= 0) {
            holder.tv_rooms.setText(" Room");
        } else {
            holder.tv_rooms.setText(" Rooms");
        }

        if (data.get(position).getAdults() <= 0) {
            holder.tv_peoples.setText(" People");
        } else {
            holder.tv_peoples.setText(" Peoples");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String getMonth(String monthInInteger) {
        String month = null;
        if (monthInInteger.equals("01")) {
            month = "Jan";
        } else if (monthInInteger.equals("02")) {
            month = "Feb";
        } else if (monthInInteger.equals("03")) {
            month = "Mar";
        } else if (monthInInteger.equals("04")) {
            month = "Apr";
        } else if (monthInInteger.equals("05")) {
            month = "May";
        } else if (monthInInteger.equals("06")) {
            month = "Jun";
        } else if (monthInInteger.equals("07")) {
            month = "Jul";
        } else if (monthInInteger.equals("08")) {
            month = "Aug";
        } else if (monthInInteger.equals("09")) {
            month = "Sep";
        } else if (monthInInteger.equals("10")) {
            month = "Oct";
        } else if (monthInInteger.equals("11")) {
            month = "Nub";
        } else if (monthInInteger.equals("12")) {
            month = "Dec";
        }
        return month;
    }

    private int maxGuests(int rooms) {
        int maxGuest = 2;
        maxGuest = rooms * 2;
        return maxGuest;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_hotel;
        private final TextView tv_hotelName;
        private final TextView tv_placeName;
        private final TextView tv_location;
        private final TextView tv_totalReviews;
        private final TextView tv_price;
        private final TextView tv_checkInDate;
        private final TextView tv_checkOutDate;
        private final TextView tv_noOfRooms;
        private final TextView tv_noOfPeoples;
        private final TextView tv_rooms;
        private final TextView tv_peoples;
        private final RatingBar ratingBar;
        private final TextView tv_changeBooking;
        private final TextView tv_cancelBooking;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotel = itemView.findViewById(R.id.img_hotel);
            tv_hotelName = itemView.findViewById(R.id.tv_hotelName);
            tv_placeName = itemView.findViewById(R.id.tv_placeName);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_totalReviews = itemView.findViewById(R.id.tv_totalReviews);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_checkInDate = itemView.findViewById(R.id.tv_checkInDate);
            tv_checkOutDate = itemView.findViewById(R.id.tv_checkOutDate);
            tv_noOfRooms = itemView.findViewById(R.id.tv_noOfRooms);
            tv_noOfPeoples = itemView.findViewById(R.id.tv_noOfPeoples);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tv_rooms = itemView.findViewById(R.id.tv_rooms);
            tv_peoples = itemView.findViewById(R.id.tv_peoples);
            tv_changeBooking = itemView.findViewById(R.id.tv_changeBooking);
            tv_cancelBooking = itemView.findViewById(R.id.tv_cancelBooking);
        }
    }
}