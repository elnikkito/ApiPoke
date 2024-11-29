package com.example.apipoke;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class PokemonViewModel extends ViewModel {

    private final PokemonRepository repository;

    private final MutableLiveData<Pokemon> selectedPokemon = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();

    public PokemonViewModel() {
        repository = new PokemonRepository();
    }

    public LiveData<Pokemon> getPokemonById(int id) {
        return repository.getPokemonById(id);
    }

    public void fetchPokemonList(int limit, int offset) {
        repository.getPokemonList(limit, offset).observeForever(pokemonList::postValue);
    }

    public LiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }
}

