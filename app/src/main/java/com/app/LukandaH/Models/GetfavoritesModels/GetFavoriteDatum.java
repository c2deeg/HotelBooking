
package com.app.LukandaH.Models.GetfavoritesModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFavoriteDatum {
    @SerializedName("id")
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
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("discount")
    @Expose
    private String discount;
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
    @SerializedName("location")
    @Expose
    private GetFavoriteLocation location;
    @SerializedName("overAllRating")
    @Expose
    private Integer overAllRating;
    @SerializedName("totalReviews")
    @Expose
    private Integer totalReviews;

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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public GetFavoriteLocation getLocation() {
        return location;
    }

    public void setLocation(GetFavoriteLocation location) {
        this.location = location;
    }

    public Integer getOverAllRating() {
        return overAllRating;
    }

    public void setOverAllRating(Integer overAllRating) {
        this.overAllRating = overAllRating;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }
}
