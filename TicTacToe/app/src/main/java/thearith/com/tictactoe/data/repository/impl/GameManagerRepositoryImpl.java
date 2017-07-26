package thearith.com.tictactoe.data.repository.impl;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.data.local.LocalGameManager;
import thearith.com.tictactoe.data.repository.GameManagerRepository;

/**
 * Created by Thearith on 7/26/17.
 */

public class GameManagerRepositoryImpl implements GameManagerRepository {

    private final LocalGameManager mLocal;


    @Inject
    public GameManagerRepositoryImpl(LocalGameManager localGameManager) {
        mLocal = localGameManager;
    }

    @Override
    public Observable<GameState> check(@NonNull GameState gameState, @NonNull GamePosition gamePosition) {
        return mLocal.check(gameState, gamePosition);
    }
}
