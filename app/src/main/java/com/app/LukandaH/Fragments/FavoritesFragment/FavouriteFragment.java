package com.app.LukandaH.Fragments.FavoritesFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HomeActivity;
import com.app.LukandaH.Activities.NotificationsActivity.NotificationsActivity;
import com.app.LukandaH.Fragments.FavoritesFragment.Presenter.FavoritesPresenter;
import com.app.LukandaH.Fragments.FavoritesFragment.View.FavoritesView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class FavouriteFragment extends Fragment implements View.OnClickListener, FavoritesView {
    private Activity activity;
    private View view;
    private RecyclerView rv_favourites;

    private ImageView img_notification;
    private TextView tv_toMainScreen;
    private FavoritesPresenter favoritesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        activity = getActivity();
        favoritesPresenter = new FavoritesPresenter(activity, this);
        init();
        listeners();
        favoritesPresenter.getFavMethod(rv_favourites);
        return view;
    }

    private void init() {
        img_notification = view.findViewById(R.id.img_notification);
        rv_favourites = view.findViewById(R.id.rv_favourites);
        tv_toMainScreen = view.findViewById(R.id.tv_toMainScreen);
    }

    private void listeners() {
        img_notification.setOnClickListener(this);
        tv_toMainScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_notification) {
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