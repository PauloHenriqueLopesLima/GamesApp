package com.example.gamesapp.viewmodel;

import android.app.Application;

import com.example.gamesapp.model.Game;
import com.example.gamesapp.repository.GameRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GameViewModel extends AndroidViewModel {

    private MutableLiveData<List<Game>> gameLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private GameRepository gameRepository = new GameRepository();

    public GameViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Game>> getGameLiveData() {
        return gameLiveData;
    }

    public void atualizarGames (int limit,int offset) {

        disposable.add(
                gameRepository.getGameListApi(limit,offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(gameList -> gameLiveData.setValue(gameList),
                        throwable -> throwable.printStackTrace())


        );

    }
}
