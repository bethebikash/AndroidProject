package com.bhattaraibikash.erepair.fragment.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.adapter.MyBookingAdapter;
import com.bhattaraibikash.erepair.api.BookingApi;
import com.bhattaraibikash.erepair.responses.MyBookingResponse;
import com.bhattaraibikash.erepair.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookingFragment extends Fragment {

    private RecyclerView rvMyBookings;
    private LinearLayout noBookings;

    public MyBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_booking, container, false);
        rvMyBookings = view.findViewById(R.id.rvMyBookings);
        noBookings = view.findViewById(R.id.noBooking);

        loadMyBookings();

        return view;
    }

    private void loadMyBookings() {
        BookingApi bookingApi = Url.getInstance().create(BookingApi.class);

        Call<List<MyBookingResponse>> call = bookingApi.getMyBookings(Url.token);
        call.enqueue(new Callback<List<MyBookingResponse>>() {
            @Override
            public void onResponse(Call<List<MyBookingResponse>> call, Response<List<MyBookingResponse>> response) {

                if (response.body() != null) {
                    List<MyBookingResponse> myBookingResponses = response.body();

                    MyBookingAdapter myBookingAdapter = new MyBookingAdapter(getContext(), myBookingResponses);
                    rvMyBookings.setAdapter(myBookingAdapter);
                    rvMyBookings.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                    noBookings.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<MyBookingResponse>> call, Throwable t) {
            }
        });
    }

}
