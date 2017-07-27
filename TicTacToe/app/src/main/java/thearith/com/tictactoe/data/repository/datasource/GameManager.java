package thearith.com.tictactoe.data.repository.datasource;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.cross.model.Player;

/**
 * Created by Thearith on 7/25/17.
 */

public interface GameManager {

    Observable<GameState> initializeGame(List<Player> players, int size);
    Observable<GameState> check(@NonNull GameState gameState, @NonNull GamePosition gamePosition);

}
