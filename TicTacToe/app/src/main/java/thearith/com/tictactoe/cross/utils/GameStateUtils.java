package thearith.com.tictactoe.cross.utils;

import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.cross.model.WinningState;

/**
 * Created by Thearith on 8/7/17.
 */

public class GameStateUtils {

    public static WinningState getWinner(PlayerType playerType) {
        switch (playerType) {
            case PLAYER_O:
                return WinningState.PLAYER_O;

            case PLAYER_X:
                return WinningState.PLAYER_X;

            case UNKNOWN:
            default:
                return WinningState.NOT_OVER;
        }

    }
}
