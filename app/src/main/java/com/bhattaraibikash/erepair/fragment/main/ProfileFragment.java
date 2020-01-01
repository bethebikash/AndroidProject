package com.bhattaraibikash.erepair.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activity.profile.EditProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ImageView ivBtnEditProfile;
    private ImageView ivBtnChangePassword;
    private ImageView ivProfileShow;
    private TextView tvNameProfile;
    private TextView tvUsernameProfile;
    private TextView tvEmailProfile;
    private TextView tvPhoneProfile;
    private TextView tvAddressProfile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ivBtnEditProfile = view.findViewById(R.id.ivBtnEditProfile);

        ivBtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfile();
            }
        });
        return view;
    }

    private void openEditProfile() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }
}
