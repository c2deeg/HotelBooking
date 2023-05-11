package com.app.LukandaH.Fragments.BookedHotelFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.BookedHotelFragment.Presenter.BookHotelPresenter;
import com.app.LukandaH.Fragments.BookedHotelFragment.View.BookHotelView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class BookedHotelsFragment extends Fragment implements View.OnClickListener, BookHotelView {
    private final String black22 = "#38000000";
    private final String black75 = "#BF000000";
    private Activity activity;
    private View view;
    private RelativeLayout rl_upcoming;
    private RelativeLayout rl_finished;
    private TextView tv_upcoming;
    private TextView tv_finished;
    private ImageView img_upcomingSelected;
    private ImageView img_finishedSelected;
    private RecyclerView rv_UpcomingBookings;
    private RecyclerView rv_finishedBookings;
    private ImageView img_notification;
    private TextView tv_toMainScreen;
    private BookHotelPresenter bookHotelPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_booked_hotels, container, false);
        activity = getActivity();
        bookHotelPresenter = new BookHotelPresenter(activity, this);
        init();
        listeners();
        bookHotelPresenter.upcomingBookingsMethod(rv_UpcomingBookings);
        bookHotelPresenter.finishedBookingMethod(rv_finishedBookings);
        return view;
    }

    private void init() {
        rl_upcoming = view.findViewById(R.id.rl_upcoming);
        rl_finished = view.findViewById(R.id.rl_finished);
        tv_upcoming = view.findViewById(R.id.tv_upcoming);
        tv_finished = view.findViewById(R.id.tv_finished);
        img_upcomingSelected = view.findViewById(R.id.img_upcomingSelected);
        img_finishedSelected = view.findViewById(R.id.img_finishedSelected);
        rv_UpcomingBookings = view.findViewById(R.id.rv_UpcomingBookings);
        rv_finishedBookings = view.findViewById(R.id.rv_finishedBookings);
        img_notification = view.findViewById(R.id.img_notification);
        tv_toMainScreen = view.findViewById(R.id.tv_toMainScreen);
    }

    private void listeners() {
        rl_upcoming.setOnClickListener(this);
        rl_finished.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == rl_upcoming) {
            tv_upcoming.setTextColor(Color.parseColor(black75));
            tv_finished.setTextColor(Color.parseColor(black22));
            img_upcomingSelected.setVisibility(View.VISIBLE);
            img_finishedSelected.setVisibility(View.INVISIBLE);
            rv_UpcomingBookings.setVisibility(View.VISIBLE);
            rv_finishedBookings.setVisibility(View.GONE);
        } else if (v == rl_finished) {
            tv_upcoming.setTextColor(Color.parseColor(black22));
            tv_finished.setTextColor(Color.parseColor(black75));
            img_upcomingSelected.setVisibility(View.INVISIBLE);
            img_finishedSelected.setVisibility(View.VISIBLE);
            rv_UpcomingBookings.setVisibility(View.GONE);
            rv_finishedBookings.setVisibility(View.VISIBLE);
        } else if (v == img_notification) {
            Intent intent = new Intent(activity, NotificationsActivity.class);
            startActivity(intent);
        } else if (v == tv_toMainScreen) {
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity, msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }
}