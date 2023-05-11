
package com.app.LukandaH.Models.UpcomingBookingsModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingBookingsExample {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private List<UpcomingBookingsDatum> data = null;

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

    public List<UpcomingBookingsDatum> getData() {
        return data;
    }

    public void setData(List<UpcomingBookingsDatum> data) {
        this.data = data;
    }

}



