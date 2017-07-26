package thearith.com.tictactoe.cross.model;

/**
 * Created by Thearith on 7/26/17.
 */

public class GamePosition {
    
    private final PlayerType mValue;
    private final int mRow;
    private final int mCol;
    
    
    public GamePosition(PlayerType value, int row, int col) {
        mValue = value;
        mRow = row;
        mCol = col;
    }

    public PlayerType getValue() {
        return mValue;
    }

    public int getRow() {
        return mRow;
    }

    public int getCol() {
        return mCol;
    }
}
