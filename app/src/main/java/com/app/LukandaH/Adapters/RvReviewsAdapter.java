package com.app.LukandaH.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Models.GetRatingsModels.GetratingsItem;
import com.app.LukandaH.R;

import java.util.List;

public class RvReviewsAdapter extends RecyclerView.Adapter<RvReviewsAdapter.ViewHolder> {
    private Activity activity;
    private List<GetratingsItem> getratingsItems;

    public RvReviewsAdapter(Activity activity,List<GetratingsItem> getratingsItems) {
        this.activity = activity;
        this.getratingsItems = getratingsItems;
    }


    @NonNull
    @Override
    public RvReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_reviews_list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvReviewsAdapter.ViewHolder holder, int position) {
//        holder.tv_name.setText(hotelViewRatings.get(position).getCustomerName());
//        holder.tv_totalResult.setText(data.get(position).getTotalResult());
//        String lastUpdation = hotelViewRatings.get(position).getPostedOn();
//        holder.tv_lastUpdation.setText(lastUpdation.substring(0, 10));
//        holder.tv_rating.setText(String.valueOf(hotelViewRatings.get(position).getRating()));
//        holder.tv_userReview.setText(hotelViewRatings.get(position).getReview());
//        holder.ratingBar.setRating(hotelViewRatings.get(position).getRating());
        GetratingsItem items = getratingsItems.get(position);
        holder.tv_name.setText(items.getUser());
        holder.ratingBar.setRating(items.getRating());
        holder.tv_userReview.setText(items.getReview());
//        holder.tv_lastUpdation.setText(items.get);
        String ratiing = items.getRating().toString();
        holder.tv_rating.setText(ratiing);

    }

    @Override
    public int getItemCount() {
//        return hotelViewRatings.size();
        return getratingsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_totalResult;
        private TextView tv_lastUpdation;
        private TextView tv_rating;
        private TextView tv_userReview;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_totalResult = itemView.findViewById(R.id.tv_totalResult);
            tv_lastUpdation = itemView.findViewById(R.id.tv_lastUpdation);
            tv_userReview = itemView.findViewById(R.id.tv_userReview);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

}
