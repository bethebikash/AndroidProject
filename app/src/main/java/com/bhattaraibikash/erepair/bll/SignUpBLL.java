package com.bhattaraibikash.erepair.bll;

import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.models.User;
import com.bhattaraibikash.erepair.responses.UserResponse;
import com.bhattaraibikash.erepair.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignUpBLL {

    boolean isSuccess = false;

    public boolean checkRegister(User user) {

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> userResponseCall = userApi.userRegister(user);

        try {
            Response<UserResponse> userResponse = userResponseCall.execute();
            if(userResponse.isSuccessful()){
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;

    }
}
