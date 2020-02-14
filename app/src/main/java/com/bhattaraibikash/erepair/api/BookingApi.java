package com.bhattaraibikash.erepair.api;

import com.bhattaraibikash.erepair.models.Booking;
import com.bhattaraibikash.erepair.responses.MyBookingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingApi {
    @POST("bookings")
    Call<Booking> serviceBooking(@Header("Authorization") String token, @Body Booking booking);

    @GET("bookings/me")
    Call<List<MyBookingResponse>> getMyBookings(@Header("Authorization") String token);

    @DELETE("bookings/{id}")
    Call<Void> deleteBooking(@Path("id") String _id);
}
