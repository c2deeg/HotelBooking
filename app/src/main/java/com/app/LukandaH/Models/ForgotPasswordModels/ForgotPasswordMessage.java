
package com.app.LukandaH.Models.ForgotPasswordModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordMessage {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("OTP")
    @Expose
    private String otp;
    @SerializedName("otpToken")
    @Expose
    private String otpToken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpToken() {
        return otpToken;
    }

    public void setOtpToken(String otpToken) {
        this.otpToken = otpToken;
    }

}
