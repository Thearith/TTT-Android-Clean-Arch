package thearith.com.tictactoe.presentation.internal.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import thearith.com.tictactoe.cross.eventbus.EventBus;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.GameState;
import thearith.com.tictactoe.data.repository.GameManagerRepository;
import thearith.com.tictactoe.data.repository.GamePublisherRepository;
import thearith.com.tictactoe.data.repository.impl.GameManagerRepositoryImpl;
import thearith.com.tictactoe.data.repository.impl.GamePublisherRepositoryImpl;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.domain.executor.base.ThreadExecutor;
import thearith.com.tictactoe.domain.executor.base.impl.JobExecutor;
import thearith.com.tictactoe.domain.executor.base.impl.UIThread;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * A module that controls all dependencies for Application
 */

@Module
public class ApplicationModule {

    private final Context mApplicationContext;


    public ApplicationModule(Context applicationContext) {
        mApplicationContext = applicationContext;
    }


    @Provides
    @ApplicationScope
    Context provideContext() {
        return mApplicationContext;
    }

//    @Provides
//    @ApplicationScope
//    EventBus<GameState> provideTicTacToeEventBus(PostExecutionThread postExecutionThread) {
//        return new EventBus<>(postExecutionThread);
//    }


    @Provides
    @ApplicationScope
    GameManagerRepository provideGameManager(GameManagerRepositoryImpl gameManagerRepositoryImpl) {
        return gameManagerRepositoryImpl;
    }

    @Provides
    @ApplicationScope
    GamePublisherRepository provideGamePublisher(GamePublisherRepositoryImpl gamePublisherRepositoryImpl) {
        return gamePublisherRepositoryImpl;
    }


    @Provides
    @ApplicationScope
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }


    @Provides
    @ApplicationScope
    PostExecutionThread providePostThreadExecutor(UIThread uiThread) {
        return uiThread;
    }

}
