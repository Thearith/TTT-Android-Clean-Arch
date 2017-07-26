package thearith.com.tictactoe.data.repository.datasource;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;

/**
 * Created by Thearith on 7/25/17.
 */

public interface GamePublisher {

    /**
     * Sets up connection between two players
     * */
    Observable<Boolean> setUpConnection();


    /**
     * Publishes UI change to another player
     * */
    Observable<GameState> publishUI(GameState gameState);
}
