package com.app.LukandaH.Models.ProfileUpdateModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileUpdateExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private ProfileUpdateData data;

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

    public ProfileUpdateData getData() {
        return data;
    }

    public void setData(ProfileUpdateData data) {
        this.data = data;
    }

}
