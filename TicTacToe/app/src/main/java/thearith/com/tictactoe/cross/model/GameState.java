package thearith.com.tictactoe.cross.model;

import thearith.com.tictactoe.cross.utils.ArrayUtils;

/**
 * Created by Thearith on 7/25/17.
 */

public class GameState {

    private PlayerType[][] mScores;
    private PlayerType mWinner;


    /**
     * Constructor
     * */

    public GameState(int size) {
        mWinner = PlayerType.UNKNOWN;
        mScores = ArrayUtils.init2DArray(PlayerType.UNKNOWN, size);
    }

    public GameState(GameState gameState) {
        int size = gameState.mScores.length;
        mScores = ArrayUtils.copy2DArray(gameState.getScores());
        mWinner = PlayerType.UNKNOWN;
    }




    /**
     * Mutate methods
     * */

    public void setScores(PlayerType playerType, int row, int col) {
        mScores[row][col] = playerType;
    }

    public void setWinner(PlayerType winner) {
        mWinner = winner;
    }



    /**
     * Getters
     * */

    public PlayerType[][] getScores() {
        return mScores;
    }

    public PlayerType getWinner() {
        return mWinner;
    }

    public boolean hasWinner() {
        return mWinner.isUnknown();
    }

    public int getGameSize() {
        return mScores.length;
    }
}
