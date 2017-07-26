package thearith.com.tictactoe.domain.interactor.base;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import thearith.com.tictactoe.domain.executor.base.PostExecutionThread;
import thearith.com.tictactoe.domain.executor.base.ThreadExecutor;
import thearith.com.tictactoe.presentation.presenter.Observer;

/**
 * A default UseCase base class
 */

public abstract class UseCase<T> implements Interactor<T> {

    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final CompositeDisposable disposables;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }


    /**
     * abstract method
     * To be implemented by individual use case
     *
     * */
    protected abstract Observable<T> createObservable(Object... params);


    private Observable<T> createObservable() {
        return createObservable(null);
    }


    @Override
    public Observable<T> execute() {
        return createObservable();
    }

    @Override
    public Observable<T> execute(Object... params) {
        return createObservable(params);
    }


    @Override
    public void execute(Observer<T> subscriber) {
        final Disposable disposable = createObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribeWith(subscriber);

        addDisposable(disposable);
    }

    @Override
    public void execute(Observer<T> subscriber, Object... params) {
        final Disposable disposable = createObservable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribeWith(subscriber);

        addDisposable(disposable);
    }

    public void dispose() {
        if(!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
