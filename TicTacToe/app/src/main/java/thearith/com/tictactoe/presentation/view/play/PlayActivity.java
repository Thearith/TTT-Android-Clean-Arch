package thearith.com.tictactoe.presentation.view.play;

import android.os.Bundle;
import android.support.annotation.Nullable;

import thearith.com.tictactoe.R;
import thearith.com.tictactoe.presentation.presenter.Presenter;
import thearith.com.tictactoe.presentation.view.base.BaseActivity;
import thearith.com.tictactoe.presentation.view.play.tictactoe.TicTacToeFragment;

/**
 * Created by Thearith on 7/11/17.
 */

public class PlayActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareUI();
    }

    @Override
    protected Presenter getPresenter() {
        return null;
    }


    private void prepareUI() {
        addFragment(R.id.tic_tac_toe_container, TicTacToeFragment.newInstance());
    }
}
