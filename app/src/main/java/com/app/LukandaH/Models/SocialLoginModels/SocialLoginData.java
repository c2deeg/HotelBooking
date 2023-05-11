
package com.app.LukandaH.Models.SocialLoginModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialLoginData {

    @SerializedName("socialLinkId")
    @Expose
    private String socialLinkId;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("otp")
    @Expose
    private Object otp;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("socialLoginId")
    @Expose
    private String socialLoginId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getSocialLinkId() {
        return socialLinkId;
    }

    public void setSocialLinkId(String socialLinkId) {
        this.socialLinkId = socialLinkId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Object getOtp() {
        return otp;
    }

    public void setOtp(Object otp) {
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

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

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public void setSocialLoginId(String socialLoginId) {
        this.socialLoginId = socialLoginId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
