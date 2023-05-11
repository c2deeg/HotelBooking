package com.app.LukandaH.Models.LoginModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("language")
    @Expose
    private LoginLanguage language;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("token")
    @Expose
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LoginLanguage getLanguage() {
        return language;
    }

    public void setLanguage(LoginLanguage language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
