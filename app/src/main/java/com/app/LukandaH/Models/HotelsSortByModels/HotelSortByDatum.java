
package com.app.LukandaH.Models.HotelsSortByModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelSortByDatum {

    @SerializedName("location")
    @Expose
    private HotelSortByLocation location;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("rooms")
    @Expose
    private Integer rooms;
    @SerializedName("isOnDiscount")
    @Expose
    private Boolean isOnDiscount;
    @SerializedName("bookedRoom")
    @Expose
    private Integer bookedRoom;
    @SerializedName("isParkingAvailable")
    @Expose
    private Boolean isParkingAvailable;
    @SerializedName("isWifiAvailable")
    @Expose
    private Boolean isWifiAvailable;
    @SerializedName("isMealAvailable")
    @Expose
    private Boolean isMealAvailable;
    @SerializedName("isPetAllow")
    @Expose
    private Boolean isPetAllow;
    @SerializedName("isFreeCancellation")
    @Expose
    private Boolean isFreeCancellation;
    @SerializedName("offers")
    @Expose
    private String offers;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("gallery")
    @Expose
    private List<Object> gallery = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("rating")
    @Expose
    private List<HotelSortByRating> rating = null;
    @SerializedName("overAllRating")
    @Expose
    private Integer overAllRating;

    public HotelSortByLocation getLocation() {
        return location;
    }

    public void setLocation(HotelSortByLocation location) {
        this.location = location;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Boolean getIsOnDiscount() {
        return isOnDiscount;
    }

    public void setIsOnDiscount(Boolean isOnDiscount) {
        this.isOnDiscount = isOnDiscount;
    }

    public Integer getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Integer bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public Boolean getIsParkingAvailable() {
        return isParkingAvailable;
    }

    public void setIsParkingAvailable(Boolean isParkingAvailable) {
        this.isParkingAvailable = isParkingAvailable;
    }

    public Boolean getIsWifiAvailable() {
        return isWifiAvailable;
    }

    public void setIsWifiAvailable(Boolean isWifiAvailable) {
        this.isWifiAvailable = isWifiAvailable;
    }

    public Boolean getIsMealAvailable() {
        return isMealAvailable;
    }

    public void setIsMealAvailable(Boolean isMealAvailable) {
        this.isMealAvailable = isMealAvailable;
    }

    public Boolean getIsPetAllow() {
        return isPetAllow;
    }

    public void setIsPetAllow(Boolean isPetAllow) {
        this.isPetAllow = isPetAllow;
    }

    public Boolean getIsFreeCancellation() {
        return isFreeCancellation;
    }

    public void setIsFreeCancellation(Boolean isFreeCancellation) {
        this.isFreeCancellation = isFreeCancellation;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<Object> getGallery() {
        return gallery;
    }

    public void setGallery(List<Object> gallery) {
        this.gallery = gallery;
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

    public List<HotelSortByRating> getRating() {
        return rating;
    }

    public void setRating(List<HotelSortByRating> rating) {
        this.rating = rating;
    }

    public Integer getOverAllRating() {
        return overAllRating;
    }

    public void setOverAllRating(Integer overAllRating) {
        this.overAllRating = overAllRating;
    }
}
