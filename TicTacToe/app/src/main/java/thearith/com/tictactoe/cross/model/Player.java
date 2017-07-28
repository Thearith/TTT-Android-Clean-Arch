package thearith.com.tictactoe.cross.model;

/**
 * Created by Thearith on 7/27/17.
 */

public class Player {

    private final PlayerType mPlayerType;
    private final String mName;
    private boolean mIsTurn;

    public Player(PlayerType playerType, String name, boolean isTurn) {
        mPlayerType = playerType;
        mName = name;
        mIsTurn = isTurn;
    }

    public void setTurn(boolean isTurn) {
        mIsTurn = isTurn;
    }

    public PlayerType getPlayerType() {
        return mPlayerType;
    }

    public String getName() {
        return mName;
    }

    public boolean isTurn() {
        return mIsTurn;
    }
}
