package com.example.gamesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gamesapp.adapter.GamesAdapter;
import com.example.gamesapp.viewmodel.GameViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView gameRecyclerView;
    private GamesAdapter gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameRecyclerView = findViewById(R.id.games_recycler_view_id);
        gamesAdapter = new GamesAdapter();

        gameRecyclerView.setAdapter(gamesAdapter);
        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GameViewModel gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        gameViewModel.atualizarGames();

        gameViewModel.getGameLiveData()
                .observe(this, gameList -> gamesAdapter.atualizarGames(gameList));
    }
}
