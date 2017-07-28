package thearith.com.tictactoe.cross.model;

import java.util.List;

import thearith.com.tictactoe.cross.utils.ArrayUtils;

/**
 * Created by Thearith on 7/25/17.
 */

public class GameState implements Cloneable {

    private final List<List<PlayerType>> mScores;
    private final List<Player> mPlayers;
    private final PlayerType mWinner;


    /**
     * Constructor
     * */

    public GameState(List<List<PlayerType>> scores, List<Player> players, PlayerType winner) {
        mScores = scores;
        mPlayers = players;
        mWinner = winner;
    }

    @Override
    public GameState clone() {
        List<List<PlayerType>> scores = ArrayUtils.copy2DArray(mScores);
        List<Player> players = ArrayUtils.copyArray(mPlayers);
        return new GameState(scores, players, mWinner);
    }


    /**
     * Getters
     * */

    public List<List<PlayerType>> getScores() {
        return mScores;
    }

    public PlayerType getWinner() {
        return mWinner;
    }

    public boolean hasWinner() {
        return !mWinner.isUnknown();
    }

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public Player getPlayerTurn() {
        for(Player player : mPlayers) {
            if(player.isTurn()) {
                return player;
            }
        }

        return null;
    }
}
