package thearith.com.tictactoe.presentation.view.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import thearith.com.tictactoe.presentation.internal.di.components.ApplicationComponent;
import thearith.com.tictactoe.presentation.presenter.Presenter;

/**
 * BaseActivity.java
 *
 * A base activity which all activities here inherit from
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    /**
     * Methods related to Presenter
     * */

    protected abstract void setUpPresenter();
    protected abstract Presenter getPresenter();


    @Override
    protected void onResume() {
        super.onResume();

        Presenter presenter = getPresenter();
        if(presenter != null) {
            presenter.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Presenter presenter = getPresenter();
        if(presenter != null) {
            presenter.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Presenter presenter = getPresenter();
        if(presenter != null) {
            presenter.destroy();
        }
    }


    /**
     * Gets Application Component
     * */
    public ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

}
