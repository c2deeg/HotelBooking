package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.AddToFavoritesModel.AddToFavoriteExample;
import com.app.LukandaH.Models.GetfavoritesModels.GetFavoriteExample;

public interface GetFavoriteHandler {
    public void onSuccess(GetFavoriteExample getFavoriteExample);

    public void onError(String message);
}
