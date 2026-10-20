package com.example.copekzadanieretrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listViewFilmy;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewFilmy = findViewById(R.id.listViewFilmy);
        progressBar = findViewById(R.id.progressBar);

        pobierzFilmy();
    }

    private void pobierzFilmy() {

        progressBar.setVisibility(View.VISIBLE);

        FilmApi api = RetrofitClient.getApi();

        Call<List<Film>> call = api.pobierzFilmy();

        call.enqueue(new Callback<List<Film>>() {

            @Override
            public void onResponse(
                    Call<List<Film>> call,
                    Response<List<Film>> response) {

                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {

                    List<Film> filmy = response.body();

                    ArrayList<String> lista = new ArrayList<>();

                    for (Film film : filmy) {

                        String tekst = film.getTytul()
                                + " (" + film.getRok() + ")"
                                + "\nGatunek: " + film.getGatunek();

                        lista.add(tekst);
                    }

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<>(
                                    MainActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    lista
                            );

                    listViewFilmy.setAdapter(adapter);

                    Toast.makeText(
                            MainActivity.this,
                            "Pobrano filmy: " + filmy.size(),
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "Nie udało się pobrać danych",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(
                    Call<List<Film>> call,
                    Throwable t) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(
                        MainActivity.this,
                        "Błąd połączenia: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
