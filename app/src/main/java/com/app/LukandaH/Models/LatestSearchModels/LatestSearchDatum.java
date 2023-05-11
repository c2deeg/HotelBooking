
package com.app.LukandaH.Models.LatestSearchModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestSearchDatum {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("hotel")
    @Expose
    private LatestSearchHotel hotel;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatestSearchHotel getHotel() {
        return hotel;
    }

    public void setHotel(LatestSearchHotel hotel) {
        this.hotel = hotel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
