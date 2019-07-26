package com.example.gamesapp.repository;

import com.example.gamesapp.model.Game;
import com.example.gamesapp.model.GameImage;
import com.example.gamesapp.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GameRepository {

    private RetrofitService retrofitService = new RetrofitService();
    private static final String API_KEY = "cd082d3569e02515658088a4f8aa64df053d3ed8";
    private static final String FORMAT = "json";

    public Observable<List<Game>> getGameList () {
        return Observable.create(emitter -> {

            List<Game> gameList = new ArrayList<>();
            Game game = new Game();
            game.setTitulo("God of War 4");
            game.setDescricao("Game of the year 2018");
            gameList.add(game);

            emitter.onNext(gameList);
            emitter.onComplete();

        });
    }

    public Observable<List<Game>> getGameListApi () {
        return retrofitService.getGamesApi()
                .getGames(API_KEY, FORMAT)
                .map(gameResponse -> gameResponse.getResults());

    }

}
