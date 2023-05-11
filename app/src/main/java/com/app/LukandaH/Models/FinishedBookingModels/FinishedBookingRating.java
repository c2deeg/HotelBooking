
package com.app.LukandaH.Models.FinishedBookingModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinishedBookingRating {

    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("postedOn")
    @Expose
    private String postedOn;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

}
