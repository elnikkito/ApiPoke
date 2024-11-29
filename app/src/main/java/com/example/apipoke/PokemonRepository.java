package com.example.apipoke;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRepository {

    private final PokeApiService pokeApiService;

    public PokemonRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokeApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokeApiService = retrofit.create(PokeApiService.class);
    }

    public MutableLiveData<Pokemon> getPokemonById(int id) {
        MutableLiveData<Pokemon> liveData = new MutableLiveData<>();
        pokeApiService.getPokemonById(String.valueOf(id)).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(response.body());
                } else {
                    Log.e("PokemonRepository", "No se pudo obtener el Pokémon");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("PokemonRepository", "Error: " + t.getMessage());
            }
        });
        return liveData;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList(int limit, int offset) {
        MutableLiveData<ArrayList<Pokemon>> liveData = new MutableLiveData<>();
        pokeApiService.getPokemonList(limit, offset).enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(response.body().getResults());
                } else {
                    Log.e("PokemonRepository", "No se pudo obtener la lista de Pokémon");
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e("PokemonRepository", "Error: " + t.getMessage());
            }
        });
        return liveData;
    }
}
