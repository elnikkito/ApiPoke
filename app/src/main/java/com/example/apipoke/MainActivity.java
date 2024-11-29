package com.example.apipoke;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokemonAdapter = new PokemonAdapter(new ArrayList<>());
        recyclerView.setAdapter(pokemonAdapter);

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        // Obtener y mostrar la lista de Pokémon
        viewModel.fetchPokemonList(20, 0);
        viewModel.getPokemonList().observe(this, pokemonList -> {
            if (pokemonList != null) {
                pokemonAdapter.setPokemonList(pokemonList);
            }
        });
    }
}
