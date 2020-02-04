package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.responses.ReviewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReviewApi {

    @GET("reviews-service")
    Call<List<ReviewResponse>> getReview(@Query("service") String _id);

    @FormUrlEncoded
    @POST("reviews")
    Call<ReviewResponse> sendReview(@Header("Authorization") String token,
                                         @Field("review") String review,
                                         @Field("service") String service);
}
