package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.responses.ReviewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewApi {

    @GET("reviews-service")
    Call<List<ReviewResponse>> getReview(@Query("service") String _id);
}
