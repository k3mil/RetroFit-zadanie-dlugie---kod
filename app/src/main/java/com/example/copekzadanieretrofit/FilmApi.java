package com.example.copekzadanieretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmApi {

    @GET("db.json")
    Call<List<Film>> pobierzFilmy();

}
