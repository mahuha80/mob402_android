package com.example.vinhntph08047_mob402_assignmentgd2.api;

import com.example.vinhntph08047_mob402_assignmentgd2.api.CartResponse;
import com.google.gson.annotations.SerializedName;

public class BaseGetProductCart {
    @SerializedName("_id")
    private String Id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("items")
    private CartResponse[] items;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CartResponse[] getItems() {
        return items;
    }

    public void setItems(CartResponse[] items) {
        this.items = items;
    }
}
