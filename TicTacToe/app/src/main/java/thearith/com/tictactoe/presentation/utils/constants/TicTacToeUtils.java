package thearith.com.tictactoe.presentation.utils.constants;

import android.support.annotation.ColorRes;

import thearith.com.tictactoe.R;
import thearith.com.tictactoe.cross.model.PlayerType;

/**
 * Created by Thearith on 7/24/17.
 */

public class TicTacToeUtils {


    @ColorRes
    public static int getTicTacToeBgColor(PlayerType type) {
        switch (type) {
            case PLAYER_X:
                return R.color.tictactoe_x_bg;

            case PLAYER_O:
                return R.color.tictactoe_o_bg;

            default:
                return R.color.tictactoe_default_bg;
        }
    }
}
