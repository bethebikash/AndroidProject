package com.bhattaraibikash.erepair.fragment.bookings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bhattaraibikash.erepair.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookingFragment extends Fragment {

    public MyBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_booking, container, false);

        return view;
    }

}
