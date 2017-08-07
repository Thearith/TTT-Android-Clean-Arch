package thearith.com.tictactoe.presentation.view.play.tictactoe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import thearith.com.tictactoe.R;
import thearith.com.tictactoe.cross.model.Player;
import thearith.com.tictactoe.presentation.adapter.TicTacToeAdapter;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.presentation.view.base.BaseFragment;

/**
 * Created by Thearith on 7/17/17.
 */

public class TicTacToeFragment extends BaseFragment implements TicTacToeView, TicTacToeAdapter.TicTacToeListener {

    // Views
    @BindView(R.id.grid_tic_tac_toe)    RecyclerView mTicTacToeGrid;
    @BindView(R.id.tv_player_turn)      TextView mPlayerTurnTextView;
    private SweetAlertDialog mResetDialog;

    @Inject
    TicTacToePresenter mPresenter;

    // Data
    private int mSize = 3;
    private List<Player> mPlayers;
    private TicTacToeAdapter mTicTacToeAdapter;

    /**
     * Constructor
     * */

    public static TicTacToeFragment newInstance() {
        Bundle args = new Bundle();
        TicTacToeFragment fragment = new TicTacToeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * Initialization
     * */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();

        mPlayers = new ArrayList<>();
        mPlayers.add(new Player(PlayerType.PLAYER_X, "Player X", true));
        mPlayers.add(new Player(PlayerType.PLAYER_O, "Player O", false));
    }

    private void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    private void initPresenter() {
        mPresenter.setView(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tictactoe, container, false);
        ButterKnife.bind(this, view);

        prepareUI();

        initPresenter();

        initGame();

        return view;
    }

    private void prepareUI() {
        prepareRecyclerView(mSize);
        prepareDialog();
    }

    private void prepareRecyclerView(int size) {
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), size);
        mTicTacToeGrid.setHasFixedSize(true);
        mTicTacToeGrid.setLayoutManager(lLayout);

        // Adapter
        mTicTacToeAdapter = new TicTacToeAdapter(size*size).setClickListener(this);
        mTicTacToeGrid.setAdapter(mTicTacToeAdapter);
    }

    private void prepareDialog() {
        mResetDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Congratulations")
                .setConfirmText("Ok")
                .setConfirmClickListener(dialog -> {
                    initGame();
                    dialog.dismiss();
                });
    }


    /**
     * TicTacToe's RecyclerView's Click Listener
     * */

    private void initGame() {
        mPresenter.initGame(mPlayers, mSize);
    }

    @Override
    public void onGridClick(int row, int col) {
        mPresenter.checkGrid(row, col);
    }


    /**
     * TicTacToeView methods used by Presenter
     * */

    @Override
    public void drawPlayerTurn(PlayerType player, String playerName) {
        mPlayerTurnTextView.setText(playerName);
    }

    @Override
    public void drawGrid(List<PlayerType> grid) {
        mTicTacToeAdapter.setGrid(grid);
    }

    @Override
    public void displayWinDialog(String playerName) {
        mResetDialog
                .setContentText("Player " + playerName + " has won!!")
                .show();
    }

    @Override
    public void displayDrawDialog() {
        mResetDialog
                .setContentText("This is a draw game")
                .show();
    }
}
