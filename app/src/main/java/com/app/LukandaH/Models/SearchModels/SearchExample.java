package com.app.LukandaH.Models.SearchModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private List<SearchDatum> data = null;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<SearchDatum> getData() {
        return data;
    }

    public void setData(List<SearchDatum> data) {
        this.data = data;
    }

}
