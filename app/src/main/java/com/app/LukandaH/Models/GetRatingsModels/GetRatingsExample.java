
package com.app.LukandaH.Models.GetRatingsModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRatingsExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("items")
    @Expose
    private List<GetratingsItem> items = null;

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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<GetratingsItem> getItems() {
        return items;
    }

    public void setItems(List<GetratingsItem> items) {
        this.items = items;
    }

}
