package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.Request;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestApi {
    @POST("requests")
    Call<Request> becomeProfessional(@Body Request request);
}
