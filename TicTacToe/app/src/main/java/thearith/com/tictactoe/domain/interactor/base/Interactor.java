package thearith.com.tictactoe.domain.interactor.base;

import io.reactivex.Observable;
import thearith.com.tictactoe.presentation.presenter.Observer;

/**
 * Interactor.java
 *
 * A generic interface for UseCase
 */

public interface Interactor<T> {
    void execute(Observer<T> subscriber);
    void execute(Observer<T> subscriber, Object... params);
    Observable<T> execute();
    Observable<T> execute(Object... params);
}
