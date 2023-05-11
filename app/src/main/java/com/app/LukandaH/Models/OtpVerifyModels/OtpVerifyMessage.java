
package com.app.LukandaH.Models.OtpVerifyModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpVerifyMessage {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("otpVerifyToken")
    @Expose
    private String otpVerifyToken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtpVerifyToken() {
        return otpVerifyToken;
    }

    public void setOtpVerifyToken(String otpVerifyToken) {
        this.otpVerifyToken = otpVerifyToken;
    }

}
