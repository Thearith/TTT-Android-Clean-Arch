package thearith.com.tictactoe.cross.model;

import thearith.com.tictactoe.presentation.utils.constants.Constants;
import thearith.com.tictactoe.presentation.utils.constants.TicTacToeConstants;

/**
 * Created by Thearith on 7/29/17.
 */

public enum WinningState {
    NOT_OVER(0),
    PLAYER_X(1),
    PLAYER_O(2),
    DRAW(4);

    private int mType;

    WinningState(int type) {
        mType = type;
    }

    @Override
    public String toString() {
        switch (this) {
            case PLAYER_X:
                return TicTacToeConstants.PLAYER_X;

            case PLAYER_O:
                return TicTacToeConstants.PLAYER_O;

            case NOT_OVER:
            default:
                return Constants.EMPTY_STRING;
        }
    }

    public int getValue() {
        return mType;
    }

    public boolean isWinning() {
        return mType == PLAYER_X.getValue() || mType == PLAYER_O.getValue();
    }

    public boolean isDraw() {
        return mType == DRAW.getValue();
    }

    public boolean isGameNotOver() {
        return mType == NOT_OVER.getValue();
    }
}
