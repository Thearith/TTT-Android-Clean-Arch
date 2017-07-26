package thearith.com.tictactoe.data.local;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.eventbus.EventBus;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.data.repository.datasource.GamePublisher;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * Created by Thearith on 7/25/17.
 */

@ApplicationScope
public class LocalGamePublisher implements GamePublisher {

    private EventBus<GameState> mEventBus;

    @Inject
    LocalGamePublisher(EventBus<GameState> eventBus) {
        mEventBus = eventBus;
    }

    @Override
    public Observable<Boolean> setUpConnection() {
        return Observable.just(true);
    }

    @Override
    public Observable<GameState> publishUI(GameState gameState) {
        mEventBus.send(gameState);
        return Observable.just(gameState);
    }
}
