package com.app.LukandaH.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HotelViewActivity.HotelViewActivity;
import com.app.LukandaH.Models.NearMeHotelsModel.NearMeHotelsDatum;
import com.app.LukandaH.R;

import java.util.List;

public class RvNearMeAdapter extends RecyclerView.Adapter<RvNearMeAdapter.ViewHolder> {
    private final List<NearMeHotelsDatum> hoteldata;
    private final Activity activity;
    //    private String userrole;
    private final String yellow = "#FFA132";
    private final String dark_gray = "#67686d";

    public RvNearMeAdapter(Activity activity, List<NearMeHotelsDatum> data) {
        this.activity = activity;
        this.hoteldata = data;
//        this.userrole = userrole;
    }

    @NonNull
    @Override
    public RvNearMeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_near_me_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvNearMeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_hotelName.setText(hoteldata.get(position).getName());
        holder.tv_rate.setText((hoteldata.get(position).getDescription()));
        starRatingByImage(hoteldata.get(position).getOverAllRating().substring(0, 1), holder);
        holder.ll_nearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HotelViewActivity.class);
                intent.putExtra("hotelid", hoteldata.get(position).getId());
                intent.putExtra("lat", 30.7371);
                intent.putExtra("longh", 76.6807);
//                intent.putExtra("role", userrole);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoteldata.size();
    }

    private void starRatingByImage(String rating, RvNearMeAdapter.ViewHolder holder) {
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
        private final ImageView img_hotelView;
        private final TextView tv_hotelName;
        private final TextView tv_review;
        private final TextView tv_location;
        private final TextView tv_rate;
        private final LinearLayout ll_nearMe;
        private final ImageView img_star1;
        private final ImageView img_star2;
        private final ImageView img_star3;
        private final ImageView img_star4;
        private final ImageView img_star5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotelView = itemView.findViewById(R.id.img_hotelView);
            tv_hotelName = itemView.findViewById(R.id.tv_hotelName);
            tv_review = itemView.findViewById(R.id.tv_review);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_rate = itemView.findViewById(R.id.tv_rate);
            ll_nearMe = itemView.findViewById(R.id.ll_nearMe);
            img_star1 = itemView.findViewById(R.id.img_star1);
            img_star2 = itemView.findViewById(R.id.img_star2);
            img_star3 = itemView.findViewById(R.id.img_star3);
            img_star4 = itemView.findViewById(R.id.img_star4);
            img_star5 = itemView.findViewById(R.id.img_star5);
        }
    }
}