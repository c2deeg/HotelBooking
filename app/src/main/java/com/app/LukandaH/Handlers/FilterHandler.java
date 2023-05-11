package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.SearchFilterModels.FilterExample;

public interface FilterHandler {
    public void onSuccess(FilterExample filterExample, String accessToken);

    public void onError(String message);
}
