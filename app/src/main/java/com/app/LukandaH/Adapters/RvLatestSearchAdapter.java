package com.app.LukandaH.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.app.LukandaH.Models.LatestSearchModels.LatestSearchDatum;
import com.app.LukandaH.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RvLatestSearchAdapter extends RecyclerView.Adapter<RvLatestSearchAdapter.ViewHolder> {
    private final String yellow = "#FFA132";
    private final String dark_gray = "#67686d";
    private final List<LatestSearchDatum> hoteldata;
    private final Context context;
    private double lat;
    private double longh;
//    private String userrole;

    public RvLatestSearchAdapter(List<LatestSearchDatum> hoteldata, Context context) {
        this.hoteldata = hoteldata;
        this.context = context;
//        this.userrole = userrole;
    }

    @NonNull
    @Override
    public RvLatestSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_latest_search_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvLatestSearchAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_hotelName.setText(hoteldata.get(position).getHotel().getName());
        if(hoteldata.get(position).getHotel().getOverAllRating() == null){
//            Toast.makeText(context, "failed to load tottalRating", Toast.LENGTH_SHORT).show();
        } else {
            starRatingByImage(String.valueOf((int) hoteldata.get(position).getHotel().getOverAllRating()), holder);
        }
        List<Float> cordinates = hoteldata.get(position).getHotel().getLocation().getCoordinates();
        lat = cordinates.get(0);
        longh = cordinates.get(1);
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, longh, 1);
            if (addresses.size() > 0) {
                String citiName = addresses.get(0).getLocality();
                holder.tv_hotelCityName.setText(citiName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.ll_latestSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HotelViewActivity.class);
                intent.putExtra("hotelid", hoteldata.get(position).getHotel().getId());
                intent.putExtra("lat", lat);
                intent.putExtra("longh", longh);
//                intent.putExtra("role", userrole);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoteldata.size();
    }

    private void starRatingByImage(String rating, RvLatestSearchAdapter.ViewHolder holder) {
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
        private LinearLayout ll_latestSearch;
        private ImageView img_star1;
        private ImageView img_star2;
        private ImageView img_star3;
        private ImageView img_star4;
        private ImageView img_star5;
        private TextView tv_hotelCityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotelView = itemView.findViewById(R.id.img_hotelView);
            tv_hotelName = itemView.findViewById(R.id.tv_hotelName);
            ll_latestSearch = itemView.findViewById(R.id.ll_latestSearch);
            img_star1 = itemView.findViewById(R.id.img_star1);
            img_star2 = itemView.findViewById(R.id.img_star2);
            img_star3 = itemView.findViewById(R.id.img_star3);
            img_star4 = itemView.findViewById(R.id.img_star4);
            img_star5 = itemView.findViewById(R.id.img_star5);
            tv_hotelCityName = itemView.findViewById(R.id.tv_hotelCityName);
        }
    }
}
