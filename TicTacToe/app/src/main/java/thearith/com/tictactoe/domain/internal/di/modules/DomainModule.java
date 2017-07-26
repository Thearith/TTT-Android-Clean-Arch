package thearith.com.tictactoe.domain.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.domain.executor.base.ThreadExecutor;
import thearith.com.tictactoe.domain.executor.base.impl.JobExecutor;
import thearith.com.tictactoe.domain.executor.base.impl.UIThread;
import thearith.com.tictactoe.domain.interactor.DrawTicTacToeGridUseCase;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;
import thearith.com.tictactoe.presentation.internal.di.modules.ApplicationModule;

/**
 * A module that controls all dependencies of Domain
 */

@Module(includes = {ApplicationModule.class})
public class DomainModule {


}
