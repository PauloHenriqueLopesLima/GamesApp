package com.example.gamesapp.service.api;

import com.example.gamesapp.model.GameResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface GamesApi {

    @GET("games")
    Observable<GameResponse> getGames(@Query("api_key") String apiKey,
                                      @Query("limit") int limit,
                                      @Query("format") String format,
                                      @Query("offset") int offset);

    @GET("original")
    Observable<GameResponse> getImage(@Query("api_key") String apiKey,
                                      @Query("format") String format);
}
