package thearith.com.tictactoe.data.repository;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;

/**
 * Created by Thearith on 7/26/17.
 */

public interface GamePublisherRepository {

    Observable<Boolean> setUpConnection();

    Observable<GameState> publishUI(GameState gameState);
}
