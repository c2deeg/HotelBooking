
package com.app.LukandaH.Models.FinishedBookingModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinishedBookingDatum {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("primaryGuest")
    @Expose
    private String primaryGuest;
    @SerializedName("travelPurpose")
    @Expose
    private String travelPurpose;
    @SerializedName("checkIn")
    @Expose
    private String checkIn;
    @SerializedName("checkOut")
    @Expose
    private String checkOut;
    @SerializedName("rooms")
    @Expose
    private Integer rooms;
    @SerializedName("adults")
    @Expose
    private Integer adults;
    @SerializedName("children")
    @Expose
    private Integer children;
    @SerializedName("finishedbooking")
    @Expose
    private Boolean finishedbooking;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("saveuserinfo")
    @Expose
    private Boolean saveuserinfo;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("hotel")
    @Expose
    private FinishedBookingHotel hotel;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrimaryGuest() {
        return primaryGuest;
    }

    public void setPrimaryGuest(String primaryGuest) {
        this.primaryGuest = primaryGuest;
    }

    public String getTravelPurpose() {
        return travelPurpose;
    }

    public void setTravelPurpose(String travelPurpose) {
        this.travelPurpose = travelPurpose;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Boolean getFinishedbooking() {
        return finishedbooking;
    }

    public void setFinishedbooking(Boolean finishedbooking) {
        this.finishedbooking = finishedbooking;
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

    public Boolean getSaveuserinfo() {
        return saveuserinfo;
    }

    public void setSaveuserinfo(Boolean saveuserinfo) {
        this.saveuserinfo = saveuserinfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FinishedBookingHotel getHotel() {
        return hotel;
    }

    public void setHotel(FinishedBookingHotel hotel) {
        this.hotel = hotel;
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
