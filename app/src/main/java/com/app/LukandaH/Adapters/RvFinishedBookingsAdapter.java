package com.app.LukandaH.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.WriteReviewActivity.WriteReviewActivity;
import com.app.LukandaH.Models.FinishedBookingModels.FinishedBookingDatum;
import com.app.LukandaH.R;

import java.io.IOException;
import java.util.List;

public class RvFinishedBookingsAdapter extends RecyclerView.Adapter<RvFinishedBookingsAdapter.ViewHolder> {

    private Activity activity;
    private List<FinishedBookingDatum> data;

    public RvFinishedBookingsAdapter(Activity activity, List<FinishedBookingDatum> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public RvFinishedBookingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_finished_bookings_list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvFinishedBookingsAdapter.ViewHolder holder, int position) {
//        holder.img_hotel.setImageResource(data.get(position).getHotel().getImage());
        holder.tv_hotelName.setText(data.get(position).getHotel().getName());
        holder.tv_totalReviews.setText(String.valueOf(data.get(position).getHotel().getRating().size()));
        holder.tv_price.setText(String.valueOf(data.get(position).getHotel().getPrice()));
        holder.tv_noOfRooms.setText(data.get(position).getRooms().toString());
        holder.tv_noOfPeoples.setText(String.valueOf(data.get(position).getAdults()));
        String checkinMonth = data.get(position).getCheckIn().substring(5, 7);
        String checkinDate = data.get(position).getCheckIn().substring(8, 10);
        String checkoutMonth = data.get(position).getCheckOut().substring(5, 7);
        String checkoutDate = data.get(position).getCheckOut().substring(8, 10);
        holder.tv_checkInDate.setText(checkinDate + " " + getMonth(checkinMonth));
        holder.tv_checkOutDate.setText(checkoutDate + " " + getMonth(checkoutMonth));
        String hotelId = data.get(position).getHotel().getId();
        holder.ratingBar.setRating(data.get(position).getHotel().getOverAllRating());
        holder.tv_writeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WriteReviewActivity.class);
                intent.putExtra("hotelId", hotelId);
                activity.startActivity(intent);
            }
        });
        List<Float> coordinates = data.get(position).getHotel().getLocation().getCoordinates();
        double lat = coordinates.get(0);
        double lng = coordinates.get(1);
        Geocoder geocoder = new Geocoder(activity);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            String cityName = addresses.get(0).getLocality();
            holder.tv_placeName.setText(cityName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_hotel;
        private TextView tv_hotelName;
        private TextView tv_placeName;
        private TextView tv_location;
        private TextView tv_totalReviews;
        private TextView tv_price;
        private TextView tv_checkInDate;
        private TextView tv_checkOutDate;
        private TextView tv_noOfRooms;
        private TextView tv_noOfPeoples;
        private TextView tv_rooms;
        private TextView tv_peoples;
        private RatingBar ratingBar;
        private TextView tv_writeReview;

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
            tv_writeReview = itemView.findViewById(R.id.tv_writeReview);
        }
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
}