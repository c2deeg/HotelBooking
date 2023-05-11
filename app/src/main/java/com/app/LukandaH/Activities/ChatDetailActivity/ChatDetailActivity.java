package com.app.LukandaH.Activities.ChatDetailActivity;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Adapters.ChatAdapterRecyclerview;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.ChatApplication.ChatApplication;
import com.app.LukandaH.Utils.ChatApplication.Messages;
import com.app.LukandaH.Utils.Utils;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatDetailActivity extends AppCompatActivity implements View.OnClickListener {
    String userId;
    private Activity activity;
    private RecyclerView chatrecyclerview;
    private EditText et_msg;
    private ImageButton btnSend;
    private ChatAdapterRecyclerview chatAdapterRecyclerview;
    private List<Messages> messageList;
    private Socket mSocket;
    private String hotelid;
    private ImageView img_back;
    private TextView tv_toMainScreen;
    private ImageView img_notification;
    public Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "Socket Connected!" + mSocket.connected());
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "connected", Toast.LENGTH_SHORT).show();
//                    if (!isconnected) {
//                        Snackbar s = Snackbar.make(binding.getRoot(), "Loading..", Snackbar.LENGTH_LONG);
//                        s.getView().setBackgroundColor(binding.getRoot().getResources().getColor(R.color.orange));
//                        isconnected = true;
//                        s.show();
//                    }
                }
            });
        }
    };
    private final Emitter.Listener onConnectionError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Connection error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    private final Emitter.Listener onGettingRoomId = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //  roomId = args[0].toString();
//                    if (!roomId.isEmpty()) {
//                        //  getOldChats(roomId);
//                    }
                }
            });
        }
    };
    private final Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        chatrecyclerview.post(new Runnable() {
                            @Override
                            public void run() {
                                chatrecyclerview.scrollToPosition(chatAdapterRecyclerview.getItemCount() - 1);
                                // Here adapter.getItemCount()== child count
                            }
                        });

                        JSONObject jsonObject = (JSONObject) args[0];
                        if (jsonObject.has("msgFrom") && jsonObject.optString("msgFrom").equalsIgnoreCase("toUser")) {

                            String time;
                            String message;
                            try {

                                time = jsonObject.getString("date");
                                message = jsonObject.getString("msg");
                                Log.d(TAG, message + "   received msg");

                            } catch (JSONException e) {
                                return;
                            }
                            addMessage(time, message, Messages.TYPE_MESSAGE_REMOTE);
                        }
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hotelid = getIntent().getStringExtra("hotelid");
        messageList = new ArrayList<>();
        ChatApplication app = new ChatApplication();


        mSocket = app.getmSocket();
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectionError);
        //  mSocket.on("set-user-data",)
        mSocket.on("set-room", onGettingRoomId);
        mSocket.on("chat-msg", onNewMessage);
//        mSocket.on("typing", onTyping);
        mSocket.connect();
        Log.d(TAG, "socket" + mSocket.connected());
        mSocket = app.getmSocket();
        setContentView(R.layout.activity_chat_detail);
        activity = this;
        init();
        listners();
        userId = CSPreferences.readString(activity, Utils.USERID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatrecyclerview.setLayoutManager(layoutManager);
        chatrecyclerview.setAdapter(chatAdapterRecyclerview);
    }

    private void init() {
        chatrecyclerview = findViewById(R.id.chatrecyclerview);
        et_msg = findViewById(R.id.et_msg);
        btnSend = findViewById(R.id.btnSend);
        img_back = findViewById(R.id.img_back);
        tv_toMainScreen = findViewById(R.id.tv_toMainScreen);
        img_notification = findViewById(R.id.img_notification);
        chatAdapterRecyclerview = new ChatAdapterRecyclerview(messageList);
    }

    private void listners() {
        btnSend.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
        img_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSend) {
            addMessage("strDate", et_msg.getText().toString(), Messages.TYPE_MESSAGE);
            et_msg.setText("");
        } else if (view == img_back){
            Intent intent = new Intent();
            intent.putExtra("hotelid", hotelid);
            setResult(Activity.RESULT_OK,intent);
            finish();
        } else if(view == tv_toMainScreen){
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        } else if(view == img_notification){
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        }
    }

    private void attemptSend() {
        if (!mSocket.connected()) return;
        String message = et_msg.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            et_msg.requestFocus();
            return;
        }
        et_msg.setText("");

        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
        String strDate = mdformat.format(calendar.getTime());
        Log.d(TAG, "attemptSenddate" + strDate);

        // String date = "2021-02-16T15:24:44.000Z";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("msg", message);
            jsonObject.put("sender", "toUser");
            jsonObject.put("date", strDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, jsonObject + "   emit this");
        Log.d(TAG, "socket" + mSocket.connected());
        addMessage(strDate, message, Messages.TYPE_MESSAGE);
        mSocket.emit("chat-msg", jsonObject);
    }

    private void addMessage(String time, String message, int type) {
        messageList.add(new Messages.Builder(type).time(time).message(message).build());
        chatAdapterRecyclerview.notifyItemInserted(messageList.size() - 1);
//        scrollToBottom();
    }

//    private void scrollToBottom() {
////        chatrecyclerview.scrollToPosition(chatAdapterRecyclerview.getItemCount() - 1);
//    }

/*
    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (activity!= null)
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String typingMess = args[0].toString();
                        animFadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
                        animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

                        binding.txtTyping.setAnimation(animFadeIn);
                        binding.txtTyping.setVisibility(View.VISIBLE);
                        if (!mTyping)
                            mTyping = true;

                        if (mTyping) {
                            mTypingHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    binding.txtTyping.setVisibility(View.GONE);
                                    binding.txtTyping.setAnimation(animFadeOut);
                                    mTyping = false;
                                }
                            }, 1500);
                        }
                    }
                });
        }
    };
*/

//    private void getOldChats(String roomId) {
//
//        new RetrofitService(getActivity(), ServiceUrls.OLDMESSAGES + "?room_id=" + roomId + "&pageNo=" + currentPage
//                +"&pageSize=1000000",
//                1, 1, new JsonObject(), true, this).callService(false);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mSocket.connected()) {
            mSocket.connect();
            Log.d(TAG, "onStart: " + mSocket.connect());
        }
        mSocket.emit("set-user-data", userId);
    }

//    @Override
//    public void onServiceResponse(String result, int requestCode, int resCode) {
//        if (requestCode == 1) {
//            try {
//                JSONObject jsonObject=new JSONObject(result);
//                Log.d(TAG, "onSetobj"+result);
//                if (jsonObject.has("statusCode") && jsonObject.optInt("statusCode") == 200 ) {
//                    JSONArray jsonArray=jsonObject.optJSONArray("items");
//                    Log.d(TAG, "onSetryrt"+jsonArray.length());
//
//                    for (int i=0; i<jsonArray.length(); i++) {
//                        Messages message=new Messages();
//                        JSONObject obj=jsonArray.optJSONObject(i);
//
////                        if (obj.optString("toMsg").equals(helper.getusername())){
//////                            strusername = obj.optString("fromMsg");
////                        }else{
////                            obj.optString("toMsg")  ;
////                        }
//
//                        message.setmMessage(obj.optString("msg"));
//                        //obj.optString("readStatus");
//                        message.setmTime(obj.optString("date"));
////                        profileimageuser = obj.optString("avatar");
//
////                            Picasso.get()
////                                .load(profileimageuser)
////                                .placeholder(R.drawable.nopropertyfound)
////                                .into(binding.createprofile);
//
//                        if (obj.optString("fromMsg").equalsIgnoreCase(PreferenceHelper.getInstance(getActivity()).getusername()))
//                        {
//                            message.setmType(Messages.TYPE_MESSAGE);
//                        }
//                        else
//                        {
//                            message.setmType(Messages.TYPE_MESSAGE_REMOTE);
//                        }
//
//                        messageList.add(message);
//                    }
//
//                    chatAdapterRecyclerview.notifyDataSetChanged();
//                    chatrecyclerview.scrollToPosition(messageList.size() - 1);
//
//
//
//                    // binding.tvUpdatepic.setText(strusername);
//
//                    Log.d(TAG, "setimg"+binding.createprofile);
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void onServiceError(String error, int requestCode, int resCode) {
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        mSocket.connect();
//        toUser = "61d536de8d6ba67d290ed201";

//        toUser =  PreferenceHelper.getInstance(activity).getfriendname();
//        String currentRoom = userId + "-" + toUser;
//        String reverseRoom = toUser + "-" + userId;
//        Log.d(TAG, "current room " + currentRoom + "reverse room " + reverseRoom);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name1", userId + "-" + "msgTo");
            jsonObject.put("name2", "msgTo" + "-" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("set-room", jsonObject);
        mSocket.emit("set-user-data", userId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off("set-room", onGettingRoomId);
        mSocket.off("new message", onNewMessage);
        // mSocket.off("typing", onTyping);
    }
}