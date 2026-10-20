package com.example.copekzadanieretrofit;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String URL =
            "https://raw.githubusercontent.com/k3mil/RetroFit-zadanie-dlugie/main/";

    private static Retrofit retrofit;

    public static FilmApi getApi() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    )
                    .build();
        }

        return retrofit.create(FilmApi.class);
    }
}
