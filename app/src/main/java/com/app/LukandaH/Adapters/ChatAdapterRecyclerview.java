package com.app.LukandaH.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.R;
import com.app.LukandaH.Utils.ChatApplication.Messages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAdapterRecyclerview extends RecyclerView.Adapter<ChatAdapterRecyclerview.ViewHolder> {
    private static final String TAG = "MessageAdapter";
    private final List<Messages> messageList;
    private Context context;

    public ChatAdapterRecyclerview(List<Messages> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layout = -1;
        switch (viewType) {
            case Messages.TYPE_MESSAGE:
                layout = R.layout.item_message;
                break;

            case Messages.TYPE_MESSAGE_REMOTE:
                layout = R.layout.itemmessageleft;
                break;

            case Messages.TYPE_ACTION:
                layout = R.layout.item_action;
                break;
        }

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Messages message = messageList.get(position);
        holder.setMessage(message.getmMessage());
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
//        tv.setText(currentDateTimeString);

        holder.setTime(currentDateTimeString);

        Log.e("time", message.getmTime());

       /* SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = inputFormat.parse(message.getmTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            String formattedDate = outputFormat.format(date);
            holder.txtTime.setText(formattedDate);
        }*/

        Log.d(TAG, "onBindView" + message.getmType() + "//" + Messages.TYPE_MESSAGE);
        if (message.getmType() == Messages.TYPE_MESSAGE_REMOTE) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.rlMessage.setLayoutParams(params);
//            holder.rlMessage.setBackground(context.getResources().getDrawable(R.drawable.strokeedittextcorner));

        } else if (message.getmType() == Messages.TYPE_MESSAGE) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.rlMessage.setLayoutParams(params);
//            holder.rlMessage.setBackground(context.getResources().getDrawable(R.drawable.strokeedittextcorner));
        }
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getmType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtmessage;
        private final TextView txtTime;
        private final RelativeLayout rlMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmessage = itemView.findViewById(R.id.txtmessage);
            rlMessage = itemView.findViewById(R.id.rlMessage);
            txtTime = itemView.findViewById(R.id.txtTime);
        }

        public void setTime(String time) {
            if (txtTime == null) return;
            txtTime.setText(time);
        }

        public void setMessage(String message) {
            if (txtmessage == null) return;
            txtmessage.setText(message);
        }
    }
}