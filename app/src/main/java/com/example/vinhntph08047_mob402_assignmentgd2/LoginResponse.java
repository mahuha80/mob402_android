package com.example.vinhntph08047_mob402_assignmentgd2;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("token")
    private String token;
    @SerializedName("username")
    private String username;
    @SerializedName("userId")
    private String userId;

    public LoginResponse(String status, String msg, String token, String username, String userId) {
        this.status = status;
        this.msg = msg;
        this.token = token;
        this.username = username;
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NonNull
    @Override
    public String toString() {
        return msg + " " + token + " " + userId + " ";
    }
}
