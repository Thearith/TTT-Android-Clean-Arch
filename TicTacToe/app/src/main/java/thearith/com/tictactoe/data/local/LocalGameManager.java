package thearith.com.tictactoe.data.local;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.data.repository.datasource.GameManager;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * Created by Thearith on 7/25/17.
 */

@ApplicationScope
public class LocalGameManager implements GameManager {

    @Inject
    public LocalGameManager() {

    }

    @Override
    public Observable<GameState> check(@NonNull GameState gameState, @NonNull GamePosition gamePosition) {
        PlayerType player = gamePosition.getValue();
        int row = gamePosition.getRow();
        int col = gamePosition.getCol();

        return Observable.just(gameState)
                .map(state -> this.markGrid(state, player, row, col))
                .map(this::checkWinner);
    }

    private GameState markGrid(final GameState state, final PlayerType player, final int row, final int col) {
        GameState newState = new GameState(state);
        newState.setScores(player, row, col);
        return newState;
    }

    private GameState checkWinner(final GameState state) {
        GameState newState = new GameState(state);
        PlayerType[][] scores = newState.getScores();
        PlayerType winner = checkGrid(scores);
        newState.setWinner(winner);

        return newState;
    }

    private PlayerType checkGrid(PlayerType[][] scores) {
        if(scores != null) {
            PlayerType winnerRow = checkRows(scores);
            if(!winnerRow.isUnknown()) {
                return winnerRow;
            }

            PlayerType winnerColumn = checkColumns(scores);
            if(!winnerColumn.isUnknown()) {
                return winnerColumn;
            }

            PlayerType winnerDiagonal = checkDiagonals(scores);
            if(!winnerDiagonal.isUnknown()) {
                return winnerDiagonal;
            }
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkRows(@NonNull PlayerType[][] scores) {
        int size = scores.length;

        for(int i=0; i<size; i++) {
            boolean isSameType = true;
            for(int j=0; j<size-1; j++) {
                if(!scores[i][j].equal(scores[i][j+1])) {
                    isSameType = false;
                    break;
                }
            }

            if(isSameType) {
                return scores[i][0];
            }
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkColumns(@NonNull PlayerType[][] scores) {
        int size = scores.length;

        for(int i=0; i<size; i++) {
            boolean isSameType = true;
            for(int j=0; j<size-1; j++) {
                if(!scores[j][i].equal(scores[j+1][i])) {
                    isSameType = false;
                    break;
                }
            }

            if(isSameType) {
                return scores[0][i];
            }
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkDiagonals(@NonNull PlayerType[][] scores) {
        PlayerType winnerLeft = checkLeftDiagonal(scores);
        if(!winnerLeft.isUnknown()) {
            return winnerLeft;
        }

        PlayerType winnerRight = checkRightDiagonal(scores);
        if(!winnerRight.isUnknown()){
            return winnerRight;
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkLeftDiagonal(@NonNull PlayerType[][] scores) {
        int size = scores.length;
        boolean isSameType = true;

        for(int i=0; i<size-1; i++) {
            if(!scores[i][i].equal(scores[i+1][i+1])) {
                isSameType = false;
                break;
            }
        }

        return isSameType ? scores[0][0] : PlayerType.UNKNOWN;
    }

    private PlayerType checkRightDiagonal(PlayerType[][] scores) {
        int size = scores.length;
        boolean isSameType = true;

        for(int i=0; i<size-1; i++) {
            if(!scores[i][size-1-i].equal(scores[i+1][size-2-i])) {
                isSameType = false;
                break;
            }
        }

        return isSameType ? scores[0][size-1] : PlayerType.UNKNOWN;
    }

}
