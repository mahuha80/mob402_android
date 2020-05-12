package com.example.vinhntph08047_mob402_assignmentgd2.api;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class BaseProductResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Product> data;
    @SerializedName("msg")
    private String msg;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(Product[] data) {
        this.data = Arrays.asList(data);
    }

    public List<Product> getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
