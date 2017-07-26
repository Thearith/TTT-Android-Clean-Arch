package thearith.com.tictactoe.data.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import thearith.com.tictactoe.data.repository.GameManagerRepository;
import thearith.com.tictactoe.data.repository.GamePublisherRepository;
import thearith.com.tictactoe.data.repository.impl.GameManagerRepositoryImpl;
import thearith.com.tictactoe.data.repository.impl.GamePublisherRepositoryImpl;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;
import thearith.com.tictactoe.presentation.internal.di.modules.ApplicationModule;

/**
 * Module that controls dependencies for Data
 */

@Module(includes = {ApplicationModule.class})
public class DataModule {

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
}
