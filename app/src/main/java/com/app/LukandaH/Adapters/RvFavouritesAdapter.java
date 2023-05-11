package com.app.LukandaH.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HotelViewActivity.HotelViewActivity;
import com.app.LukandaH.Fragments.FavoritesFragment.Presenter.FavoritesPresenter;
import com.app.LukandaH.Fragments.FavoritesFragment.View.FavoritesView;
import com.app.LukandaH.Models.GetRatingsModels.GetratingsItem;
import com.app.LukandaH.Models.GetfavoritesModels.GetFavoriteDatum;
import com.app.LukandaH.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RvFavouritesAdapter extends RecyclerView.Adapter<RvFavouritesAdapter.ViewHolder>  {

    private final Activity activity;
    private final List<GetFavoriteDatum> data;

    public RvFavouritesAdapter(Activity activity, List<GetFavoriteDatum> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public RvFavouritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_favourites_list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvFavouritesAdapter.ViewHolder holder, int position) {
        Geocoder gcd = new Geocoder(activity, Locale.getDefault());
        holder.ratingBar.setRating(data.get(position).getOverAllRating());
        holder.tv_totalReviews.setText(String.valueOf(data.get(position).getTotalReviews()));
        List<Float> locationCoordinates = data.get(position).getLocation().getCoordinates();
        float lat = locationCoordinates.get(0);
        float longh = locationCoordinates.get(1);
        String hotelId = data.get(position).getId();
        holder.ll_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HotelViewActivity.class);
                intent.putExtra("hotelid", hotelId);
                intent.putExtra("lat", lat);
                intent.putExtra("longh", longh);
                activity.startActivity(intent);
            }
        });
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, longh, 1);
            if (addresses.size() > 0) {
                String citiName = addresses.get(0).getLocality();
                holder.tv_placeName.setText(citiName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        holder.img_hotel.setImageResource(data.get(position).getImage());
        holder.tv_hotelName.setText(data.get(position).getName());
//        holder.tv_placeName.setText(data.get(position).getPlace());
//        holder.tv_location.setText(data.get(position).getLocation());
//        holder.tv_totalReviews.setText(data.get(position).ge());
        holder.tv_price.setText(String.valueOf(data.get(position).getPrice()));
//        holder.ratingBar.setRating(data.get(position).getHotelId().getRating().get(position).getRating());
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
        private RatingBar ratingBar;
        private LinearLayout ll_hotel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotel = itemView.findViewById(R.id.img_hotel);
            tv_hotelName = itemView.findViewById(R.id.tv_hotelName);
            tv_placeName = itemView.findViewById(R.id.tv_placeName);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_totalReviews = itemView.findViewById(R.id.tv_totalReviews);
            tv_price = itemView.findViewById(R.id.tv_price);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ll_hotel = itemView.findViewById(R.id.ll_hotel);
        }
    }
}