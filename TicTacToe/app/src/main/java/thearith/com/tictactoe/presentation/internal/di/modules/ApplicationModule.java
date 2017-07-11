package thearith.com.tictactoe.presentation.internal.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
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
    public Context provideContext() {
        return mApplicationContext;
    }

}
