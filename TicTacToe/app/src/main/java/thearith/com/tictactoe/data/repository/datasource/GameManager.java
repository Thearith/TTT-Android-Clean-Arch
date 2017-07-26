package thearith.com.tictactoe.data.repository.datasource;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;

/**
 * Created by Thearith on 7/25/17.
 */

public interface GameManager {

    Observable<GameState> check(@NonNull GameState gameState, @NonNull GamePosition gamePosition);

}
