package com.app.LukandaH.Activities.HotelViewActivity.View;

import android.app.Activity;

import com.app.LukandaH.Models.AddToFavoritesModel.AddToFavoriteMessage;
import com.app.LukandaH.Models.GetRatingsModels.GetratingsItem;
import com.app.LukandaH.Models.GetfavoritesModels.GetFavoriteDatum;
import com.app.LukandaH.Models.HotelViewModels.HotelViewData;

import java.util.List;

public interface HotelViewView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();

    public void setData(Activity activity, HotelViewData data);
}
