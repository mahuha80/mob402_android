package com.example.vinhntph08047_mob402_assignmentgd2;

import java.lang.reflect.Array;
import java.util.List;

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

    @GET("api/product/getAllProducts")
    Call<BaseProductResponse> getAllProduct();

    @FormUrlEncoded
    @POST("api/product/addProductToCart")
    Call<ResponseBody> postProductToCart(@Field("userId") String userId, @Field("items") List<Cart> list);

    @FormUrlEncoded
    @POST("api/product/getAllProductInCartByID")
    Call<Root> getAllProductCartById(@Field("id") String id);

}
