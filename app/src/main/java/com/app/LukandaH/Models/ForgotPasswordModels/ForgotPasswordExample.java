
package com.app.LukandaH.Models.ForgotPasswordModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private ForgotPasswordMessage message;

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

    public ForgotPasswordMessage getMessage() {
        return message;
    }

    public void setMessage(ForgotPasswordMessage message) {
        this.message = message;
    }

}
