package thearith.com.tictactoe.cross.eventbus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.presentation.internal.di.ActivityScope;
import thearith.com.tictactoe.presentation.internal.di.ApplicationScope;
import thearith.com.tictactoe.presentation.presenter.Observer;

/**
 * Created by Thearith on 7/26/17.
 */

@ApplicationScope
public class EventBus<T> {

    private final Subject<T> mEventBus;
    private PostExecutionThread mPostExecutionThread;

    @Inject
    public EventBus(PostExecutionThread postExecutionThread) {
        mEventBus = PublishSubject.create();
        mPostExecutionThread = postExecutionThread;
    }

    public void send(T object) {
        mEventBus.onNext(object);
    }

    public Disposable subscribe(Observer<T> observer) {
        return mEventBus
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribeWith(observer);
    }
}
