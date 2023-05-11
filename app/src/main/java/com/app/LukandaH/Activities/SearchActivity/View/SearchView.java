package com.app.LukandaH.Activities.SearchActivity.View;

import android.app.Activity;

import com.app.LukandaH.Models.HotelsSortByModels.HotelSortByDatum;
import com.app.LukandaH.Models.SearchFilterModels.FilterDatum;
import com.app.LukandaH.Models.SearchModels.SearchDatum;

import java.util.List;

public interface SearchView {
    public void showMessage(Activity activity, String msg);

    public void showDialog(Activity activity);

    public void hideDialog();
}
