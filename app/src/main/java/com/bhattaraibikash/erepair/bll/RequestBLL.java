package com.bhattaraibikash.erepair.bll;

import com.bhattaraibikash.erepair.api.RequestApi;
import com.bhattaraibikash.erepair.models.Request;
import com.bhattaraibikash.erepair.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RequestBLL {

    boolean isSuccess = false;

    public boolean sendRequest(Request request) {

        RequestApi requestApi = Url.getInstance().create(RequestApi.class);
        Call<Request> requestCall = requestApi.becomeProfessional(request);

        try {
            Response<Request> requestResponse = requestCall.execute();
            if (requestResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
