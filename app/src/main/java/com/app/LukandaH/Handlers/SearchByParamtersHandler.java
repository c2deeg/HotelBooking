package com.app.LukandaH.Handlers;

import com.app.LukandaH.Models.ResetPasswordModels.ResetPasswordExample;
import com.app.LukandaH.Models.SearchByParametersModels.SearchByParametersExample;

public interface SearchByParamtersHandler {
    public void onSuccess(SearchByParametersExample SearchByParametersExample);

    public void onError(String message);
}
