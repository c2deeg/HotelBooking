
package com.app.LukandaH.Models.AddToFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToFavoriteExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private AddToFavoriteMessage message;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public AddToFavoriteMessage getMessage() {
        return message;
    }

    public void setMessage(AddToFavoriteMessage message) {
        this.message = message;
    }

}
