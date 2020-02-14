package com.bhattaraibikash.erepair.fragment.main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.LoginActivity;
import com.bhattaraibikash.erepair.activities.profile.ChangePasswordActivity;
import com.bhattaraibikash.erepair.activities.profile.EditProfileActivity;
import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.responses.UserResponse;
import com.bhattaraibikash.erepair.url.Url;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

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
    private String imagePath;
    private Button btnLogout;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ivProfileShow = view.findViewById(R.id.ivProfileShow);
        tvNameProfile = view.findViewById(R.id.tvNameProfile);
        tvUsernameProfile = view.findViewById(R.id.tvUsernameProfile);
        tvEmailProfile = view.findViewById(R.id.tvEmailProfile);
        tvPhoneProfile = view.findViewById(R.id.tvPhoneProfile);
        tvAddressProfile = view.findViewById(R.id.tvAddressProfile);
        ivBtnEditProfile = view.findViewById(R.id.ivBtnEditProfile);
        ivBtnChangePassword = view.findViewById(R.id.ivBtnChangePassword);
        btnLogout = view.findViewById(R.id.btnLogout);

        loadProfile();

        ivBtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = tvNameProfile.getText().toString();
                String username = tvUsernameProfile.getText().toString();
                String email = tvEmailProfile.getText().toString();
                String address = tvAddressProfile.getText().toString();
                String phone = tvPhoneProfile.getText().toString();

                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                intent.putExtra("imagePath", imagePath);
                startActivity(intent);
            }
        });

        ivBtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url.token = "";
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("username", "");
                editor.putString("password", "");
                editor.apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    private void loadProfile() {
        UserApi userApi = Url.getInstance().create(UserApi.class);

        Call<UserResponse> call = userApi.getUserDetails(Url.token);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.body() != null) {
                    tvNameProfile.setText(response.body().getName());
                    tvUsernameProfile.setText(response.body().getUsername());
                    tvEmailProfile.setText(response.body().getEmail());
                    tvPhoneProfile.setText(response.body().getPhone());
                    tvAddressProfile.setText(response.body().getAddress());
                    imagePath = response.body().getImage();

                    try {
                        Glide.with(ProfileFragment.this)
                                .load(Url.base_url + response.body().getImage())
                                .placeholder(R.drawable.default_profile)
                                .into(ivProfileShow);
                    } catch (Exception e) {
                        // This will catch any exception, because they are all descended from Exception
                        System.out.println("Error " + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
}
