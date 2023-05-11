package com.app.LukandaH.Models;

public class RvSearchResultsModelData {
    private int image;
    private String name;
    private float rating;
    private String location;
    private String price;

    public RvSearchResultsModelData(int image, String name, float rating, String location, String price) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.price = price;
    }

    public int getImage() { 
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
