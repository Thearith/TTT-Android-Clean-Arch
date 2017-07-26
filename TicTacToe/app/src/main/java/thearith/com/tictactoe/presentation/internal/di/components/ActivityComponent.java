package thearith.com.tictactoe.presentation.internal.di.components;

import dagger.Component;
import thearith.com.tictactoe.cross.eventbus.EventBus;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;
import thearith.com.tictactoe.presentation.internal.di.modules.ActivityModule;
import thearith.com.tictactoe.presentation.view.play.tictactoe.TicTacToeFragment;

/**
 * Created by Thearith on 7/17/17.
 */

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

//    void inject(TicTacToeFragment fragment);
}
