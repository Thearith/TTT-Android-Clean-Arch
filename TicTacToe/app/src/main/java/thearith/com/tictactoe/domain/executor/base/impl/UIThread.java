package thearith.com.tictactoe.domain.executor.base.impl;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@ApplicationScope
public class UIThread implements PostExecutionThread {

    @Inject
    UIThread() {

    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }


}
