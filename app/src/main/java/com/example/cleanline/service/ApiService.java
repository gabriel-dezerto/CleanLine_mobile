package com.example.cleanline.service;


import com.example.cleanline.model.Supervisor;
import com.example.cleanline.model.Vistoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("Supervisor")
    Call<Supervisor> dadosSuper();

    @POST("Vistoria")
    Call<Vistoria> dadosVistoria(Vistoria vistoria);
}
