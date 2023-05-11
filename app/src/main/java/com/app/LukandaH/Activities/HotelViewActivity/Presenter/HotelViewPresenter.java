package com.app.LukandaH.Activities.HotelViewActivity.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.HotelViewActivity.View.HotelViewView;
import com.app.LukandaH.Adapters.RvReviewsAdapter;
import com.app.LukandaH.Handlers.AddToFavoriteHandler;
import com.app.LukandaH.Handlers.GetFavoriteHandler;
import com.app.LukandaH.Handlers.GetRatingsHandler;
import com.app.LukandaH.Handlers.HotelViewHandler;
import com.app.LukandaH.Models.AddToFavoritesModel.AddToFavoriteExample;
import com.app.LukandaH.Models.GetRatingsModels.GetRatingsExample;
import com.app.LukandaH.Models.GetfavoritesModels.GetFavoriteExample;
import com.app.LukandaH.Models.HotelViewModels.HotelViewExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class HotelViewPresenter {
    private final HotelViewView hotelViewView;
    private final Activity activity;
    private String hotel_id;
    private final boolean flag = true;
    private boolean isFav;
    private String hotelId;
    private int pageNo;
    private int pageSize;

    public HotelViewPresenter(Activity activity, HotelViewView hotelViewView) {
        this.activity = activity;
        this.hotelViewView = hotelViewView;

    }

    public void hotelViewMethod(String hotelid) {
        this.hotel_id = hotelid;

        hotelViewView.showDialog(activity);
        WebServices.getmInstance().hotelViewMethod(hotel_id, new HotelViewHandler() {
            @Override
            public void onSuccess(HotelViewExample hotelViewExample, String accessToken) {
                hotelViewView.hideDialog();
                if (hotelViewExample != null) {
                    if (hotelViewExample.getIsSuccess() == true) {
                        hotelViewView.showMessage(activity, hotelViewExample.getMessage());
                        hotelViewView.setData(activity, hotelViewExample.getData());
                    } else {
                        hotelViewView.showMessage(activity, hotelViewExample.getMessage());
                    }
                } else {
                    hotelViewView.showMessage(activity, hotelViewExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                hotelViewView.hideDialog();
                hotelViewView.showMessage(activity, message);
            }
        });
    }

    public void addToFavMethod(String hotelId, boolean isFav) {
        this.hotel_id = hotelId;
        this.isFav = isFav;
        hotelViewView.showDialog(activity);
        String userId = CSPreferences.readString(activity, Utils.USERID);
        WebServices.getmInstance().addToFavoriteMethod(userId, hotelId, isFav, new AddToFavoriteHandler() {
            @Override
            public void onSuccess(AddToFavoriteExample addToFavoriteExample) {
                hotelViewView.hideDialog();
                if (addToFavoriteExample != null) {
                    if (addToFavoriteExample.getIsSuccess() == true) {
                        hotelViewView.showMessage(activity, "success");
                    } else {
                        hotelViewView.showMessage(activity, "Fail");
                    }
                } else {
                    hotelViewView.showMessage(activity, "Fail");
                }
            }

            @Override
            public void onError(String message) {
                hotelViewView.hideDialog();
                hotelViewView.showMessage(activity, message);
            }
        });
    }

}
