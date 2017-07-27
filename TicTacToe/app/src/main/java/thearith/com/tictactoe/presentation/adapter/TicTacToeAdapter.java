package thearith.com.tictactoe.presentation.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import thearith.com.tictactoe.R;
import thearith.com.tictactoe.cross.model.PlayerType;
import thearith.com.tictactoe.cross.utils.ArrayUtils;
import thearith.com.tictactoe.presentation.utils.constants.TicTacToeUtils;

/**
 * A RecyclerView's Adapter for handling TicTacToe's UI state
 */

public class TicTacToeAdapter extends RecyclerView.Adapter<TicTacToeAdapter.TicTacToeViewHolder> {

    // An interface for Clicking TicTacToe grid
    public interface TicTacToeListener {
        void onGridClick(int row, int col);
    }

    private final int mColSize;
    private PlayerType[] mScores;
    private TicTacToeListener mGridListener;

    public TicTacToeAdapter(int size) {
        mColSize = size;
        mScores = ArrayUtils.initArray(PlayerType.UNKNOWN, size);
    }

    public TicTacToeAdapter(PlayerType[] grid, int size) {
        mScores = grid;
        mColSize = size;
    }

    public TicTacToeAdapter setClickListener(TicTacToeListener listener) {
        mGridListener = listener;
        return this;
    }

    public void setGrid(PlayerType[] scores) {
        if(scores != null) {
            for(int pos=0; pos<scores.length; pos++) {
                if(!mScores[pos].equals(scores[pos])) {
                    mScores[pos] = scores[pos];
                    notifyItemChanged(pos);
                }
            }
        }
    }

    public void setTicTacToeCol(int row, int col, PlayerType val) {
        int position = row*mColSize + col;
        mScores[position] = val;
        notifyItemChanged(position);
    }


    @Override
    public TicTacToeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)
                parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View colView = inflater.inflate(R.layout.view_holder_tic_tac_toe_grid, parent, false);

        return new TicTacToeViewHolder(colView);
    }

    @Override
    public void onBindViewHolder(TicTacToeViewHolder holder, final int position) {
        if(position >= mScores.length) {
            return;
        }

        PlayerType value = mScores[position];

        Context context = holder.itemView.getContext();
        int bgColor = TicTacToeUtils.getTicTacToeBgColor(value);
        String text = value.toString();

        holder.mTicTacToeCol.setCardBackgroundColor(context.getResources().getColor(bgColor));
        holder.mTicTacToeColTv.setText(text);

        if(!value.isUnknown()) {
            holder.itemView.setClickable(false);
            holder.itemView.setOnClickListener(null);

        } else {
            holder.mTicTacToeCol.setCardBackgroundColor(holder.itemView
                    .getResources().getColor(R.color.tictactoe_default_bg));

            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(view -> {
                int row = position / mColSize;
                int col = position % mColSize;

                if(mGridListener != null) {
                    mGridListener.onGridClick(row, col);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mScores != null ? mScores.length : 0;
    }

    class TicTacToeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tic_tac_toe_col)     CardView mTicTacToeCol;
        @BindView(R.id.tic_tac_toe_col_tv)  TextView mTicTacToeColTv;

        TicTacToeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
