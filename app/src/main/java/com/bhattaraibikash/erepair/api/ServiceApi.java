package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("services")
    Call<List<Service>> getServices();

    @GET("services/{id}")
    Call<Service> getServicesDetail(@Path("id") String _id);

    @GET("services-category")
    Call<List<Service>> getServicesByCategory(@Query("category") String _id);
}
