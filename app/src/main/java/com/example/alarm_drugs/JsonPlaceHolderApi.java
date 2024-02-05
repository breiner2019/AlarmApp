package com.example.alarm_drugs;


import com.example.alarm_drugs.Clases.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("Usuarios")
    Call<List<User>> getDrugs();
}
