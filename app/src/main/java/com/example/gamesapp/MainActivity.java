package com.example.gamesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.gamesapp.adapter.GamesAdapter;
import com.example.gamesapp.adapter.listener.GameListListener;
import com.example.gamesapp.model.Game;
import com.example.gamesapp.viewmodel.GameViewModel;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GameListListener {


    public static final int LIMIT = 20;
    private int offset;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView gameRecyclerView;
    private GamesAdapter gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_id);
        gameRecyclerView = findViewById(R.id.games_recycler_view_id);
        gamesAdapter = new GamesAdapter(this);

        gameRecyclerView.setAdapter(gamesAdapter);
        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GameViewModel gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        gameViewModel.atualizarGames(LIMIT,offset);

        gameViewModel.getGameLiveData()
                .observe(this, gameList -> {
                    gamesAdapter.atualizarGames(gameList);
                swipeRefreshLayout.setRefreshing(false);
                });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                gameViewModel.atualizarGames(LIMIT,offset);
            }
        });

        gameRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (gameRecyclerView.canScrollVertically(1)){

                    offset += LIMIT;
                    gameViewModel.atualizarGames(LIMIT,offset);
                }
            }
        });
    }


    @Override
    public void onGameClick(Game game) {
        //compartilhar
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(intent.EXTRA_SUBJECT,game.getTitulo());




        intent.putExtra(intent.EXTRA_TEXT,game.getTitulo()+": "+game.getDescricao());

        startActivity(intent.createChooser(intent,"Compartilhar"));

    }
}
