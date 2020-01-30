package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("services")
    Call<List<Service>> getServices();
}
