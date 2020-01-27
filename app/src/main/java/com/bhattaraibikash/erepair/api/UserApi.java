package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.User;
import com.bhattaraibikash.erepair.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("users")
    Call<UserResponse> userRegister(@Body User user);

//    @FormUrlEncoded
//    @POST("/users/login")
//    Call<SignUpResponse> userLogin(@Field("username") String username, @Field("password") String password);
//
//    @Multipart
//    @POST("upload")
//    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

//    @GET("users/me")
//    Call<User> getUserDetails(@Header("Authorization") String token);
}
