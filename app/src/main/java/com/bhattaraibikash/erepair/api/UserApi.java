package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.User;
import com.bhattaraibikash.erepair.responses.LoginResponse;
import com.bhattaraibikash.erepair.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApi {
    @POST("users")
    Call<UserResponse> userRegister(@Body User user);

    @FormUrlEncoded
    @POST("/users/login")
    Call<LoginResponse> userLogin(@Field("username") String username, @Field("password") String password);

//    @Multipart
//    @POST("upload")
//    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<UserResponse> getUserDetails(@Header("Authorization") String token);
}
