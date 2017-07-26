package thearith.com.tictactoe.data.repository.impl;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.data.local.LocalGamePublisher;
import thearith.com.tictactoe.data.repository.GamePublisherRepository;

/**
 * Created by Thearith on 7/26/17.
 */

public class GamePublisherRepositoryImpl implements GamePublisherRepository {

    private final LocalGamePublisher mLocal;


    @Inject
    public GamePublisherRepositoryImpl(LocalGamePublisher localGamePublisher) {
        mLocal = localGamePublisher;
    }

    @Override
    public Observable<Boolean> setUpConnection() {
        return mLocal.setUpConnection();
    }

    @Override
    public Observable<GameState> publishUI(GameState gameState) {
        return mLocal.publishUI(gameState);
    }
}
