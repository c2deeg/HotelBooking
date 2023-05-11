package com.app.LukandaH.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Models.RvNotificationsModelData;
import com.app.LukandaH.R;

import java.util.ArrayList;

public class RvNotificationsAdapter extends RecyclerView.Adapter<RvNotificationsAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<RvNotificationsModelData> data;

    public RvNotificationsAdapter(Activity activity, ArrayList<RvNotificationsModelData> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public RvNotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_notifications_list_items, parent, false);
        return new ViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RvNotificationsAdapter.ViewHolder holder, int position) {
        holder.tv_notification.setText(data.get(position).getNotification());
        holder.tv_time.setText(data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_notificationDot;
        private TextView tv_notification;
        private TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_notificationDot = itemView.findViewById(R.id.img_notificationDot);
            tv_notification = itemView.findViewById(R.id.tv_notification);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
