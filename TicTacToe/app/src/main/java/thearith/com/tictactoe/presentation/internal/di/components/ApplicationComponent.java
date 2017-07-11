package thearith.com.tictactoe.presentation.internal.di.components;


import android.content.Context;

import dagger.Component;
import thearith.com.tictactoe.data.internal.di.modules.DataModule;
import thearith.com.tictactoe.domain.interactor.internal.di.modules.DomainModule;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;
import thearith.com.tictactoe.presentation.internal.di.modules.ApplicationModule;

/**
 * A component whose lifetime is the life of the application.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, DomainModule.class, DataModule.class})
public interface ApplicationComponent {

    Context context();
}
