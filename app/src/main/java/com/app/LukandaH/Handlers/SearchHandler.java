package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.SearchModels.SearchExample;

public interface SearchHandler {
    public void onSuccess(SearchExample searchExample, String accessToken);

    public void onError(String message);
}
