package thearith.com.tictactoe.data.repository;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.cross.model.Player;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * Created by Thearith on 7/26/17.
 */

@ApplicationScope
public interface GameManagerRepository {

    Observable<GameState> initializeGame(List<Player> players, int size);
    Observable<GameState> check(@NonNull GameState gameState, @NonNull GamePosition gamePosition);
}
