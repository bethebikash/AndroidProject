package com.bhattaraibikash.erepair.bll;

import com.bhattaraibikash.erepair.api.BookingApi;
import com.bhattaraibikash.erepair.models.Booking;
import com.bhattaraibikash.erepair.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class BookingBLL {

    boolean isSuccess = false;

    public boolean booking(String token, Booking booking) {

        BookingApi bookingApi = Url.getInstance().create(BookingApi.class);
        Call<Booking> bookingCall = bookingApi.serviceBooking(token, booking);

        try {
            Response<Booking> bookingResponse = bookingCall.execute();
            if (bookingResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
