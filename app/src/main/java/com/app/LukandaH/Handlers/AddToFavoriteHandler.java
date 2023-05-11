package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.AddToFavoritesModel.AddToFavoriteExample;
import com.app.LukandaH.Models.ChangePasswordModels.ChangePasswordExample;

public interface AddToFavoriteHandler {
    public void onSuccess(AddToFavoriteExample addToFavoriteExample);

    public void onError(String message);
}
