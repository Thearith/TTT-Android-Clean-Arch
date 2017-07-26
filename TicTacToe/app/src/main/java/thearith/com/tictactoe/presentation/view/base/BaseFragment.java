package thearith.com.tictactoe.presentation.view.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import thearith.com.tictactoe.presentation.internal.di.components.ActivityComponent;
import thearith.com.tictactoe.presentation.internal.di.components.ApplicationComponent;

/**
 * Created by Thearith on 7/17/17.
 */

public class BaseFragment extends Fragment {

    public ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity()).getActivityComponent();
    }

    public ApplicationComponent getApplicationComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }

    public BaseActivity getBaseActivity() {
        Activity activity = getActivity();
        if(activity instanceof BaseActivity) {
            return (BaseActivity) activity;
        }

        return null;
    }

}
