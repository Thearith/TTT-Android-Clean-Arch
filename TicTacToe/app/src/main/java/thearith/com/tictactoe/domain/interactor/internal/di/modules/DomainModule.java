package thearith.com.tictactoe.domain.interactor.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import thearith.com.tictactoe.domain.interactor.executor.base.PostExecutionThread;
import thearith.com.tictactoe.domain.interactor.executor.base.ThreadExecutor;
import thearith.com.tictactoe.domain.interactor.executor.base.impl.JobExecutor;
import thearith.com.tictactoe.domain.interactor.executor.base.impl.UIThread;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * A module that controls all dependencies of Domain
 */

@Module
public class DomainModule {

    @Provides
    @ApplicationScope
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }


    @Provides
    @ApplicationScope
    public PostExecutionThread providePostThreadExecutor(UIThread uiThread) {
        return uiThread;
    }
}
