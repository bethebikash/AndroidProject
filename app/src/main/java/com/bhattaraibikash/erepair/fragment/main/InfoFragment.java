package com.bhattaraibikash.erepair.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.info.AboutActivity;
import com.bhattaraibikash.erepair.activities.info.ProfessionalActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    LinearLayout llBecomeProfessional, llAbout;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);
        llBecomeProfessional = view.findViewById(R.id.llBecomeProfessional);
        llAbout = view.findViewById(R.id.llAbout);

        llBecomeProfessional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfessionalActivity.class);
                startActivity(intent);
            }
        });

        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
