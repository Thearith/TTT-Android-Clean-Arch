package thearith.com.tictactoe.presentation.view.play.tictactoe;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import thearith.com.tictactoe.cross.eventbus.EventBus;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.cross.utils.ArrayUtils;
import thearith.com.tictactoe.domain.interactor.DrawTicTacToeGridUseCase;
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
    private final DrawTicTacToeGridUseCase mDrawUseCase;
    private TicTacToeView mView;
    private EventBus<GameState> mEventBus;

    // Data
    private GameState mCurrentState;
    private PlayerType mPlayerTurn;

    @Inject
    public TicTacToePresenter(DrawTicTacToeGridUseCase drawUseCase, EventBus<GameState> eventBus) {
        this.mDrawUseCase = drawUseCase;
        mEventBus = eventBus;

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

    public void initState(int size) {
        mCurrentState = new GameState(size);
        mPlayerTurn = PlayerType.PLAYER_X;

        mView.drawPlayerTurn(mPlayerTurn, mPlayerTurn.toString());
        PlayerType[][] score = mCurrentState.getScores();
        PlayerType[] flatScore = ArrayUtils.flatten(score);
        mView.drawGrid(flatScore);
    }

    private void setGameState(GameState state) {
        mCurrentState = state;
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
    }


    /**
     * View to Presenter method
     * */
    public void drawGrid(int row, int col) {
        GamePosition gamePosition = new GamePosition(mPlayerTurn, row, col);
        mDrawUseCase.execute(new TicTacToeObserver(), mCurrentState, gamePosition);

        mPlayerTurn = mPlayerTurn.getToggle();
        mView.drawPlayerTurn(mPlayerTurn, mPlayerTurn.toString());
    }



    private final class TicTacToeObserver extends Observer<GameState> {

        @Override
        public void onNext(GameState value) {

            if(value != null) {
                mCurrentState = value;

                PlayerType[][] score = value.getScores();
                PlayerType[] flatScore = ArrayUtils.flatten(score);

                mView.drawGrid(flatScore);

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
