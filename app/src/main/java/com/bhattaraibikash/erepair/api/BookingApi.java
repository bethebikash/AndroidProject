package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.Booking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BookingApi {
    @POST("bookings")
    Call<Booking> serviceBooking(@Header("Authorization") String token, @Body Booking booking);
}
