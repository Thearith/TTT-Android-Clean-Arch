package thearith.com.tictactoe.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.data.repository.GameManagerRepository;
import thearith.com.tictactoe.data.repository.GamePublisherRepository;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.domain.executor.base.ThreadExecutor;
import thearith.com.tictactoe.domain.interactor.base.UseCase;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * Created by Thearith on 7/26/17.
 */

@ApplicationScope
public class DrawTicTacToeGridUseCase extends UseCase<GameState> {

    private final GameManagerRepository mGameManager;
    private final GamePublisherRepository mGamePublisher;

    @Inject
    public DrawTicTacToeGridUseCase(GameManagerRepository gameManager, GamePublisherRepository gamePublisher,
                                    ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mGameManager = gameManager;
        mGamePublisher = gamePublisher;
    }

    @Override
    protected Observable<GameState> createObservable(Object... params) {
        if(params != null && params.length >= 2) {
            GameState gameState = (GameState) params[0];
            GamePosition gamePosition = (GamePosition) params[1];

            return mGameManager.check(gameState, gamePosition)
                    .flatMap(mGamePublisher::publishUI);
        }

        return null;
    }
}
