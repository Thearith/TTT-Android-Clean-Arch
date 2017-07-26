package thearith.com.tictactoe.cross.model;

/**
 * Created by Thearith on 7/25/17.
 */

public class GameState {

    private PlayerType[][] mGrid;
    private PlayerType mWinner;

    public GameState(int size) {
        mWinner = PlayerType.UNKNOWN;
        mGrid = new PlayerType[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                mGrid[i][j] = PlayerType.UNKNOWN;
            }
        }
    }

    public GameState(GameState gameState) {
        int size = gameState.mGrid.length;
        mGrid = new PlayerType[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                mGrid[i][j] = gameState.mGrid[i][j];
            }
        }

        mWinner = PlayerType.UNKNOWN;
    }

    public PlayerType[][] getState() {
        return mGrid;
    }

    public void setState(PlayerType playerType, int row, int col) {
        mGrid[row][col] = playerType;
    }

    public PlayerType getWinner() {
        return mWinner;
    }

    public boolean hasWinner() {
        return mWinner.isUnknown();
    }

    public void setWinner(PlayerType winner) {
        mWinner = winner;
    }

    public int getGameSize() {
        return mGrid.length;
    }
}
