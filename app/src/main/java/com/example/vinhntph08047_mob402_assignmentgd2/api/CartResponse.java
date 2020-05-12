package com.example.vinhntph08047_mob402_assignmentgd2.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CartResponse {
    @SerializedName("_id")
    private String Id;
    @SerializedName("productId")
    private String productId;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("price")
    private String price;

    @NonNull
    @Override
    public String toString() {
        return Id + "-" + "-" + productId + "-" + quantity + "-" + price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
