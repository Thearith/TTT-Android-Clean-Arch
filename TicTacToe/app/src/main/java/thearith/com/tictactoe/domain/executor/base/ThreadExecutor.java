package thearith.com.tictactoe.domain.executor.base;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link io.grasp.thearith.grasp.base.domain.interactor.UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {}