package thearith.com.tictactoe.presentation.view.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import thearith.com.tictactoe.presentation.internal.di.components.ActivityComponent;
import thearith.com.tictactoe.presentation.internal.di.components.ApplicationComponent;
import thearith.com.tictactoe.presentation.internal.di.components.DaggerActivityComponent;
import thearith.com.tictactoe.presentation.internal.di.modules.ActivityModule;
import thearith.com.tictactoe.presentation.presenter.Presenter;

/**
 * BaseActivity.java
 *
 * A base activity which all activities here inherit from
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getApplicationComponent().inject(this);

        initializeInjector();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(@IdRes int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }


    /**
     * Methods related to Presenter
     * */

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
     * Dagger 2 Injections
     * */
    private void initializeInjector() {
//        mActivityComponent = DaggerActivityComponent.builder()
//                .applicationComponent(getApplicationComponent())
//                .activityModule(new ActivityModule(this))
//                .build();
        mActivityComponent = null;
    }

    public ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

}
