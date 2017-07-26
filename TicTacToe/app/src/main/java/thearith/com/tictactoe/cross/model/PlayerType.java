package thearith.com.tictactoe.cross.model;

import thearith.com.tictactoe.presentation.utils.constants.Constants;
import thearith.com.tictactoe.presentation.utils.constants.TicTacToeConstants;

/**
 * Created by Thearith on 7/25/17.
 */

public enum PlayerType {
    PLAYER_X(1),
    PLAYER_O(2),
    UNKNOWN(0);

    private int mType;

    PlayerType() {
        mType = 0;
    }

    PlayerType(int type) {
        mType = type;
    }

    public int getValue() {
        return mType;
    }


    @Override
    public String toString() {
        switch (this) {
            case PLAYER_X:
                return TicTacToeConstants.PLAYER_X;

            case PLAYER_O:
                return TicTacToeConstants.PLAYER_O;

            case UNKNOWN:
            default:
                return Constants.EMPTY_STRING;
        }
    }

    public boolean equal(PlayerType playerType) {
        return mType == playerType.mType;
    }

    public boolean isUnknown() {
        return mType != PLAYER_X.getValue() && mType != PLAYER_O.getValue();
    }

    public PlayerType getToggle() {
        switch (this) {
            case PLAYER_X:
                return PLAYER_O;

            case PLAYER_O:
                return PLAYER_X;

            case UNKNOWN:
            default:
                return UNKNOWN;
        }
    }
}
