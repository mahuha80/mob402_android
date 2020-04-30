package com.example.vinhntph08047_mob402_assignmentgd2;

import com.google.gson.annotations.SerializedName;

public class Root {
    @SerializedName("data")
    private BaseGetProductCart[] data;

    public Root(BaseGetProductCart[] data) {
        this.data = data;
    }

    public BaseGetProductCart[] getData() {
        return data;
    }

    public void setData(BaseGetProductCart[] data) {
        this.data = data;
    }
}
