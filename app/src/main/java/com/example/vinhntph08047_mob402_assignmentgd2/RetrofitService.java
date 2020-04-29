package com.example.vinhntph08047_mob402_assignmentgd2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("api/user/login")
    Call<LoginResponse> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/user/register")
    Call<RegisterResponse> registerUser(@Field("username") String username, @Field("password") String password);
}
