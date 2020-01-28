package com.bhattaraibikash.erepair.bll;

import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.responses.LoginResponse;
import com.bhattaraibikash.erepair.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<LoginResponse> usersCall = userApi.userLogin(username, password);

        try {
            Response<LoginResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("200")) {

                Url.token = "Bearer "+loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
