package com.example.vinhntph08047_mob402_assignmentgd2.base.model;

import androidx.annotation.NonNull;

public class Cart {
    private String productId;
    private int quantity;
    private String price;

    public Cart(String productId, int quantity, String price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "productId " + productId +"quantity "+quantity+"price"+price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

