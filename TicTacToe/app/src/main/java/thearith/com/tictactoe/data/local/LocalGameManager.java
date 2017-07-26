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
        newState.setState(player, row, col);
        return newState;
    }

    private GameState checkWinner(final GameState state) {
        GameState newState = new GameState(state);
        PlayerType[][] grid = newState.getState();
        PlayerType winner = checkGrid(grid);
        newState.setWinner(winner);

        return newState;
    }

    private PlayerType checkGrid(PlayerType[][] grid) {
        if(grid != null) {
            PlayerType winnerRow = checkRows(grid);
            if(!winnerRow.isUnknown()) {
                return winnerRow;
            }

            PlayerType winnerColumn = checkColumns(grid);
            if(!winnerColumn.isUnknown()) {
                return winnerColumn;
            }

            PlayerType winnerDiagonal = checkDiagonals(grid);
            if(!winnerDiagonal.isUnknown()) {
                return winnerDiagonal;
            }
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkRows(@NonNull PlayerType[][] grid) {
        int size = grid.length;

        for(int i=0; i<size; i++) {
            boolean isSameType = true;
            for(int j=0; j<size-1; j++) {
                if(!grid[i][j].equal(grid[i][j+1])) {
                    isSameType = false;
                    break;
                }
            }

            if(isSameType) {
                return grid[i][0];
            }
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkColumns(@NonNull PlayerType[][] grid) {
        int size = grid.length;

        for(int i=0; i<size; i++) {
            boolean isSameType = true;
            for(int j=0; j<size-1; j++) {
                if(!grid[j][i].equal(grid[j+1][i])) {
                    isSameType = false;
                    break;
                }
            }

            if(isSameType) {
                return grid[0][i];
            }
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkDiagonals(@NonNull PlayerType[][] grid) {
        PlayerType winnerLeft = checkLeftDiagonal(grid);
        if(!winnerLeft.isUnknown()) {
            return winnerLeft;
        }

        PlayerType winnerRight = checkRightDiagonal(grid);
        if(!winnerRight.isUnknown()){
            return winnerRight;
        }

        return PlayerType.UNKNOWN;
    }

    private PlayerType checkLeftDiagonal(@NonNull PlayerType[][] grid) {
        int size = grid.length;
        boolean isSameType = true;

        for(int i=0; i<size-1; i++) {
            if(!grid[i][i].equal(grid[i+1][i+1])) {
                isSameType = false;
                break;
            }
        }

        return isSameType ? grid[0][0] : PlayerType.UNKNOWN;
    }

    private PlayerType checkRightDiagonal(PlayerType[][] grid) {
        int size = grid.length;
        boolean isSameType = true;

        for(int i=0; i<size-1; i++) {
            if(!grid[i][size-1-i].equal(grid[i+1][size-2-i])) {
                isSameType = false;
                break;
            }
        }

        return isSameType ? grid[0][size-1] : PlayerType.UNKNOWN;
    }

}
