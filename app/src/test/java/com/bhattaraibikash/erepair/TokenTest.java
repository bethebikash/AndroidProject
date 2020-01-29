package com.bhattaraibikash.erepair;

import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.responses.LoginResponse;
import com.bhattaraibikash.erepair.url.Url;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TokenTest {
    @Test
    public void testToken() {

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<LoginResponse> usersCall = userApi.userLogin("username", "admin123");

        try {
            Response<LoginResponse> loginResponse = usersCall.execute();
            Url.token = "Bearer " + loginResponse.body().getToken();
            assertThat(Url.token, is(IsNull.notNullValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
