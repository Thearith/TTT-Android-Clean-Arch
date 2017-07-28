package thearith.com.tictactoe.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.cross.model.Player;
import thearith.com.tictactoe.data.repository.GameManagerRepository;
import thearith.com.tictactoe.data.repository.GamePublisherRepository;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.domain.executor.base.ThreadExecutor;
import thearith.com.tictactoe.domain.interactor.base.UseCase;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * Created by Thearith on 7/28/17.
 */

@ApplicationScope
public class InitTicTacToeGridUseCase extends UseCase<GameState> {

    private final GameManagerRepository mGameManager;
    private final GamePublisherRepository mGamePublisher;

    @Inject
    InitTicTacToeGridUseCase(GameManagerRepository gameManager, GamePublisherRepository gamePublisher,
                                    ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mGameManager = gameManager;
        mGamePublisher = gamePublisher;
    }

    @Override
    protected Observable<GameState> createObservable(Object... params) {
        if(params != null && params.length >= 2) {
            List<Player> players = (List<Player>) params[0];
            int gameSize = (int) params[1];

            return mGameManager.initializeGame(players, gameSize)
                    .flatMap(mGamePublisher::publishUI);
        }

        return null;
    }
}
