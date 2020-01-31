package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.User;
import com.bhattaraibikash.erepair.responses.LoginResponse;
import com.bhattaraibikash.erepair.responses.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserApi {
    @POST("users")
    Call<UserResponse> userRegister(@Body User user);

    @FormUrlEncoded
    @POST("/users/login")
    Call<LoginResponse> userLogin(@Field("username") String username,
                                  @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<Void> uploadImage(@Header("Authorization") String token,
                           @Part MultipartBody.Part image);

    @GET("users/me")
    Call<UserResponse> getUserDetails(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("users/me")
    Call<UserResponse> userProfileUpdate(@Header("Authorization") String token,
                                         @Field("name") String name,
                                         @Field("email") String email,
                                         @Field("address") String address,
                                         @Field("phone") String phone,
                                         @Field("username") String username);

}
