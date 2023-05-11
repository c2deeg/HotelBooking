
package com.app.LukandaH.Models.OtpVerifyModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpVerifyExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private OtpVerifyMessage message;

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

    public OtpVerifyMessage getMessage() {
        return message;
    }

    public void setMessage(OtpVerifyMessage message) {
        this.message = message;
    }

}
