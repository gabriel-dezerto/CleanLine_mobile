package com.example.cleanline.service;


import com.example.cleanline.model.Supervisor;
import com.example.cleanline.model.Vistoria;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/login")
    Call<Supervisor> login(@Body LoginRequest loginRequest);

    @GET("api/supervisor/{id}")
    Call<Supervisor> obterPerfil(@Path("id") int idSupervisor);

    @Multipart
    @POST("api/vistoria")
    Call<ResponseBody> enviarVistoria(
            @Part("id_super") RequestBody idSuper,
            @Part("id_setor") RequestBody idSetor,
            @Part("pontuacao") RequestBody pontuacao,
            @Part MultipartBody.Part imagem,
            @Part("q1") RequestBody q1, @Part("q2") RequestBody q2,
            @Part("q3") RequestBody q3, @Part("q4") RequestBody q4,
            @Part("q5") RequestBody q5, @Part("q6") RequestBody q6,
            @Part("q7") RequestBody q7, @Part("q8") RequestBody q8
    );
}
