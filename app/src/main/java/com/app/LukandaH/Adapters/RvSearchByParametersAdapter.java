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
import com.app.LukandaH.Models.SearchByParametersModels.SearchByParametersDatum;
import com.app.LukandaH.R;

import java.util.List;

public class RvSearchByParametersAdapter extends RecyclerView.Adapter<RvSearchByParametersAdapter.ViewHolder> {
    private final String yellow = "#FFA132";
    private final String dark_gray = "#67686d";
    private final Activity activity;
    private final List<SearchByParametersDatum> data;
    private double lat;
    private double longh;

    public RvSearchByParametersAdapter(Activity activity, List<SearchByParametersDatum> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public RvSearchByParametersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_near_me_list_item, parent, false);
        return new ViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RvSearchByParametersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_hotelName.setText(data.get(position).getName());

//        Toast.makeText(activity,data.get(position).getOverAllRating().toString() , Toast.LENGTH_SHORT).show();
//        int oARating = (int) data.get(position).getOverAllRating();
        String overAllRating = data.get(position).getOverAllRating().toString();
//        = String.valueOf((int)data.get(position).getOverAllRating());
//        if(data.get(position).getOverAllRating().toString() == null){
//            overAllRating = "4";
//        }else{
//            int a = (int)data.get(position).getOverAllRating();
//            overAllRating = String.valueOf(a);
//        }
//        String totalRatings = String.valueOf(data.get(position).getRating().size();
        String totalRatings = data.get(position).getTotalReviews().toString();
        holder.tv_rate.setText(data.get(position).getPrice() + "/Night");
        holder.tv_review.setText(overAllRating + "/" + totalRatings + " Reviews");
        starRatingByImage(overAllRating, holder);
        holder.tv_location.setText(data.get(position).getCity());
        List<Float> cordinates = data.get(position).getLocation().getCoordinates();
        lat = cordinates.get(0);
        longh = cordinates.get(1);
        holder.ll_nearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HotelViewActivity.class);
                intent.putExtra("hotelid", data.get(position).getId());
                intent.putExtra("lat", lat);
                intent.putExtra("longh", longh);
//                intent.putExtra("role", userrole);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void starRatingByImage(String rating, RvSearchByParametersAdapter.ViewHolder holder) {
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
        private final ImageView img_star1;
        private final ImageView img_star2;
        private final ImageView img_star3;
        private final ImageView img_star4;
        private final ImageView img_star5;
        private final TextView tv_location;
        private final TextView tv_rate;
        private final LinearLayout ll_nearMe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotelView = itemView.findViewById(R.id.img_hotelView);
            tv_hotelName = itemView.findViewById(R.id.tv_hotelName);
            tv_review = itemView.findViewById(R.id.tv_review);
            img_star1 = itemView.findViewById(R.id.img_star1);
            img_star2 = itemView.findViewById(R.id.img_star2);
            img_star3 = itemView.findViewById(R.id.img_star3);
            img_star4 = itemView.findViewById(R.id.img_star4);
            img_star5 = itemView.findViewById(R.id.img_star5);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_rate = itemView.findViewById(R.id.tv_rate);
            ll_nearMe = itemView.findViewById(R.id.ll_nearMe);
        }
    }
}