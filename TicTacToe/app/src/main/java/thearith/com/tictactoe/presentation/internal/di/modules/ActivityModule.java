package thearith.com.tictactoe.presentation.internal.di.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import thearith.com.tictactoe.cross.eventbus.EventBus;
import thearith.com.tictactoe.cross.model.GamePosition;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;

/**
 * Created by Thearith on 7/20/17.
 */

@Module
public class ActivityModule {

    private final Context mActivityContext;

    public ActivityModule(Activity activity) {
        mActivityContext = activity;
    }


    @ActivityScope
    @Provides
    Context provideActivityContext() {
        return mActivityContext;
    }
}
