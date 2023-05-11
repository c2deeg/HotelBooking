package com.app.LukandaH.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HotelViewActivity.HotelViewActivity;
import com.app.LukandaH.Models.GetHotelsModels.GetMeHotelsDatum;
import com.app.LukandaH.Models.HotelsSortByModels.HotelSortByDatum;
import com.app.LukandaH.Models.SearchFilterModels.FilterDatum;
import com.app.LukandaH.Models.SearchModels.SearchDatum;
import com.app.LukandaH.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RvSearchListAdapter extends RecyclerView.Adapter<RvSearchListAdapter.ViewHolder> {
    private final List<GetMeHotelsDatum> hoteldata;
    private final Activity activity;
    private final String yellow = "#FFA132";
    private final String dark_gray = "#67686d";
    private final List<FilterDatum> filterData;
    private final List<SearchDatum> searchData;
    private final List<HotelSortByDatum> sortData;
    //    private List<Address> address;
    private double latitude;
    private double longitude;
//    private String cityName;
//    private String userrole;

    public RvSearchListAdapter(Activity activity, List<GetMeHotelsDatum> data, List<FilterDatum> filterData, List<SearchDatum> searchData, List<HotelSortByDatum> sortData) {
        this.activity = activity;
        this.hoteldata = data;
        this.filterData = filterData;
        this.searchData = searchData;
        this.sortData = sortData;
//        this.userrole = userrole;
    }

    @NonNull
    @Override
    public RvSearchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_search_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvSearchListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.img_hotelView.setImageResource(modelData.get(position).getImage());
//        holder.tv_hotelName.setText(modelData.get(position).getName());
//        holder.tv_review.setText(modelData.get(position).getReview());
////        holder.rb_rating.setRating((modelData.get(position).getRating()));
//        holder.tv_location.setText(modelData.get(position).getLocation());
//        holder.tv_rate.setText(modelData.get(position).getRate());
        if (hoteldata != null) {
            if(hoteldata.size() == 0){
                holder.ll_noHotelFound.setVisibility(View.VISIBLE);
                holder.ll_hotel.setVisibility(View.GONE);
            } else {
                holder.ll_noHotelFound.setVisibility(View.GONE);
                holder.ll_hotel.setVisibility(View.VISIBLE);
                holder.tv_hotelName.setText(hoteldata.get(position).getName());
                holder.tv_rate.setText((hoteldata.get(position).getDescription()));
                String review = hoteldata.get(position).getOverAllRating().toString() + "/" +
                        hoteldata.get(position).getTotalReviews().toString();
                holder.tv_review.setText(review);
                starRatingByImage(String.valueOf((int) hoteldata.get(position).getOverAllRating()), holder);
            }
        } else if (filterData != null) {
            if(filterData.size() == 0){
                holder.ll_noHotelFound.setVisibility(View.VISIBLE);
                holder.ll_hotel.setVisibility(View.GONE);
            } else {
                holder.ll_noHotelFound.setVisibility(View.GONE);
                holder.ll_hotel.setVisibility(View.VISIBLE);
                holder.tv_hotelName.setText(filterData.get(position).getName());
                holder.tv_rate.setText((filterData.get(position).getDescription()));
                Geocoder gcd = new Geocoder(activity, Locale.getDefault());
                List<Float> list = filterData.get(position).getLocation().getCoordinates();
                latitude = list.get(0);
                longitude = list.get(1);
//            Toast.makeText(activity, latitude+"  "+ longitude, Toast.LENGTH_SHORT).show();
//            Log.d("check", latitude + " ," +longitude);
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses.size() > 0) {
                        String cityName = addresses.get(0).getAddressLine(0);
                        holder.tv_location.setText(cityName);
                    } else {
                        holder.tv_location.setText("MSPL");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                starRatingByImage(filterData.get(position).getOverAllRating().toString(), holder);
            }
        } else if (searchData != null) {
            if(searchData.size() == 0){
                holder.ll_noHotelFound.setVisibility(View.VISIBLE);
                holder.ll_hotel.setVisibility(View.GONE);
            } else {
                holder.ll_noHotelFound.setVisibility(View.GONE);
                holder.ll_hotel.setVisibility(View.VISIBLE);
                holder.tv_hotelName.setText(searchData.get(position).getName());
                holder.tv_rate.setText((searchData.get(position).getDescription()));
                starRatingByImage("4", holder);
            }
        } else {
            if(sortData.size() == 0){
                holder.ll_noHotelFound.setVisibility(View.VISIBLE);
                holder.ll_hotel.setVisibility(View.GONE);
            } else {
                holder.ll_noHotelFound.setVisibility(View.GONE);
                holder.ll_hotel.setVisibility(View.VISIBLE);
                holder.tv_hotelName.setText(sortData.get(position).getName());
                holder.tv_rate.setText((sortData.get(position).getDescription()));
                String overallrating = String.valueOf(sortData.get(position).getOverAllRating());
                starRatingByImage(overallrating, holder);
                Geocoder gcd = new Geocoder(activity, Locale.getDefault());
                List<Float> list = sortData.get(position).getLocation().getCoordinates();
                latitude = list.get(0);
                longitude = list.get(1);
//            Toast.makeText(activity, latitude+"  "+ longitude, Toast.LENGTH_SHORT).show();
//            Log.d("check", latitude + " ," +longitude);
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses.size() > 0) {
                        String cityName = addresses.get(0).getAddressLine(0);
                        holder.tv_location.setText(cityName);
                    } else {
                        holder.tv_location.setText("MSPL");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        holder.ll_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterData != null) {
                    Geocoder gcd = new Geocoder(activity, Locale.getDefault());
                    List<Float> list = filterData.get(position).getLocation().getCoordinates();
                    latitude = list.get(0);
                    longitude = list.get(1);
                    Intent intent = new Intent(activity, HotelViewActivity.class);
                    intent.putExtra("hotelid", filterData.get(position).getId());
                    intent.putExtra("lat", latitude);
                    intent.putExtra("longh", longitude);
                    activity.startActivity(intent);
                } else if (hoteldata != null) {
                    Intent intent = new Intent(activity, HotelViewActivity.class);
                    intent.putExtra("hotelid", hoteldata.get(position).getId());
                    intent.putExtra("lat", 30.7371);
                    intent.putExtra("longh", 76.6807);
                    activity.startActivity(intent);
                } else if (searchData != null) {
                    Intent intent = new Intent(activity, HotelViewActivity.class);
                    intent.putExtra("hotelid", searchData.get(position).getId());
                    intent.putExtra("lat", 30.7371);
                    intent.putExtra("longh", 76.6807);
                    activity.startActivity(intent);
                } else {
                    Geocoder gcd = new Geocoder(activity, Locale.getDefault());
                    List<Float> list = sortData.get(position).getLocation().getCoordinates();
                    latitude = list.get(0);
                    longitude = list.get(1);
                    Intent intent = new Intent(activity, HotelViewActivity.class);
                    intent.putExtra("hotelid", sortData.get(position).getId());
                    intent.putExtra("lat", latitude);
                    intent.putExtra("longh", longitude);
                    activity.startActivity(intent);
                }
//                intent.putExtra("role", userrole);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (hoteldata != null) {
            return hoteldata.size();
        } else if (filterData != null) {
            return filterData.size();
        } else if (searchData != null) {
            return searchData.size();
        } else {
            return sortData.size();
        }
    }

    private void starRatingByImage(String rating, RvSearchListAdapter.ViewHolder holder) {
        if (rating.equals("1")) {
            holder.img_star1.setColorFilter(Color.parseColor(yellow));
            holder.img_star2.setColorFilter(Color.parseColor(dark_gray));
            holder.img_star3.setColorFilter(Color.parseColor(dark_gray));
            holder.img_star4.setColorFilter(Color.parseColor(dark_gray));
            holder.img_star5.setColorFilter(Color.parseColor(dark_gray));
        } else if (rating.equals("2")) {
            holder.img_star1.setColorFilter(Color.parseColor(yellow));
            holder.img_star2.setColorFilter(Color.parseColor(yellow));
            holder.img_star3.setColorFilter(Color.parseColor(dark_gray));
            holder.img_star4.setColorFilter(Color.parseColor(dark_gray));
            holder.img_star5.setColorFilter(Color.parseColor(dark_gray));
        } else if (rating.equals("3")) {
            holder.img_star1.setColorFilter(Color.parseColor(yellow));
            holder.img_star2.setColorFilter(Color.parseColor(yellow));
            holder.img_star3.setColorFilter(Color.parseColor(yellow));
            holder.img_star4.setColorFilter(Color.parseColor(dark_gray));
            holder.img_star5.setColorFilter(Color.parseColor(dark_gray));
        } else if (rating.equals("4")) {
            holder.img_star1.setColorFilter(Color.parseColor(yellow));
            holder.img_star2.setColorFilter(Color.parseColor(yellow));
            holder.img_star3.setColorFilter(Color.parseColor(yellow));
            holder.img_star4.setColorFilter(Color.parseColor(yellow));
            holder.img_star5.setColorFilter(Color.parseColor(dark_gray));
        } else if (rating.equals("5")) {
            holder.img_star1.setColorFilter(Color.parseColor(yellow));
            holder.img_star2.setColorFilter(Color.parseColor(yellow));
            holder.img_star3.setColorFilter(Color.parseColor(yellow));
            holder.img_star4.setColorFilter(Color.parseColor(yellow));
            holder.img_star5.setColorFilter(Color.parseColor(yellow));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_hotelView;
        private TextView tv_hotelName;
        private TextView tv_review;
        private TextView tv_location;
        private TextView tv_rate;
        private LinearLayout ll_hotel;
        private ImageView img_star1;
        private ImageView img_star2;
        private ImageView img_star3;
        private ImageView img_star4;
        private ImageView img_star5;
        private LinearLayout ll_noHotelFound;
//        private TextView tv_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotelView = itemView.findViewById(R.id.img_hotelView);
            tv_hotelName = itemView.findViewById(R.id.tv_hotelName);
            tv_review = itemView.findViewById(R.id.tv_review);
//            rb_rating = itemView.findViewById(R.id.rb_rating);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_rate = itemView.findViewById(R.id.tv_rate);
            ll_hotel = itemView.findViewById(R.id.ll_hotel);
            img_star1 = itemView.findViewById(R.id.img_star1);
            img_star2 = itemView.findViewById(R.id.img_star2);
            img_star3 = itemView.findViewById(R.id.img_star3);
            img_star4 = itemView.findViewById(R.id.img_star4);
            img_star5 = itemView.findViewById(R.id.img_star5);
            ll_noHotelFound = itemView.findViewById(R.id.ll_noHotelFound);
        }
    }
}