
package com.app.LukandaH.Models.ChangeBookingModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeBookingExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private ChangeBookingData data;

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

    public ChangeBookingData getData() {
        return data;
    }

    public void setData(ChangeBookingData data) {
        this.data = data;
    }

}
