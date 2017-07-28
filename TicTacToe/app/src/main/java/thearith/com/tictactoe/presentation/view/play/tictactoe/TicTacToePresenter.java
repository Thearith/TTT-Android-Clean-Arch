package thearith.com.tictactoe.presentation.view.play.tictactoe;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import thearith.com.tictactoe.cross.eventbus.EventBus;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.cross.model.Player;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.cross.utils.ArrayUtils;
import thearith.com.tictactoe.domain.interactor.DrawTicTacToeGridUseCase;
import thearith.com.tictactoe.domain.interactor.InitTicTacToeGridUseCase;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;
import thearith.com.tictactoe.presentation.presenter.Observer;
import thearith.com.tictactoe.presentation.presenter.Presenter;

/**
 * Created by Thearith on 7/26/17.
 */

@ApplicationScope
public class TicTacToePresenter implements Presenter {

    private CompositeDisposable mCompositeDisposable;
    private EventBus<GameState> mEventBus;

    // Use Cases
    private final DrawTicTacToeGridUseCase mDrawUseCase;
    private final InitTicTacToeGridUseCase mInitUseCase;

    // View
    private TicTacToeView mView;

    // Data
    private GameState mCurrentState;

    @Inject
    TicTacToePresenter(InitTicTacToeGridUseCase initUseCase, DrawTicTacToeGridUseCase drawUseCase,
                       EventBus<GameState> eventBus) {
        this.mDrawUseCase = drawUseCase;
        this.mInitUseCase = initUseCase;
        this.mEventBus = eventBus;

        initCompositeDisposable();
    }

    public void setView(@NonNull TicTacToeView ticTacToeView) {
        mView = ticTacToeView;
    }

    private void initCompositeDisposable() {
        mCompositeDisposable = new CompositeDisposable();
        Disposable disposable = mEventBus.subscribe(new TicTacToeObserver());
        mCompositeDisposable.add(disposable);
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if(mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }

        if(mDrawUseCase != null) {
            mDrawUseCase.dispose();
        }

        if(mInitUseCase != null) {
            mInitUseCase.dispose();
        }
    }


    /**
     * View to Presenter method
     * */
    public void initGame(List<Player> players, int size) {
        mInitUseCase.execute(new TicTacToeObserver(), players, size);
    }

    public void drawGrid(int row, int col) {
        PlayerType playerType = mCurrentState.getPlayerTurn().getPlayerType();
        GamePosition gamePosition = new GamePosition(playerType, row, col);
        mDrawUseCase.execute(new TicTacToeObserver(), mCurrentState, gamePosition);
    }



    private final class TicTacToeObserver extends Observer<GameState> {

        @Override
        public void onNext(GameState value) {

            if(value != null) {
                mCurrentState = value;

                List<List<PlayerType>> score = value.getScores();
                List<PlayerType> flatScore = ArrayUtils.flatten(score);
                mView.drawGrid(flatScore);

                Player playerTurn = value.getPlayerTurn();
                mView.drawPlayerTurn(playerTurn.getPlayerType(), playerTurn.getName());

                PlayerType winner = value.getWinner();
                if(!winner.isUnknown()) {
                    mView.displayGameOverDialog();
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
