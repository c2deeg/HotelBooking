
package com.app.LukandaH.Models.AddToFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToFavoriteMessage {

    @SerializedName("isFav")
    @Expose
    private Boolean isFav;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("hotel")
    @Expose
    private String hotel;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
