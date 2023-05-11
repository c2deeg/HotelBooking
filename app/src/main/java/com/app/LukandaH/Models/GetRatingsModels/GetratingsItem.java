
package com.app.LukandaH.Models.GetRatingsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetratingsItem {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("hotel")
    @Expose
    private String hotel;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("review")
    @Expose
    private String review;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
