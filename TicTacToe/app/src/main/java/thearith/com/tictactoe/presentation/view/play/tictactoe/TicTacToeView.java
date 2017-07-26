package thearith.com.tictactoe.presentation.view.play.tictactoe;

import thearith.com.tictactoe.cross.model.PlayerType;

/**
 * A interface of TicTacToeFragment that communicates with TicTacToePresenter
 */

public interface TicTacToeView {

    void drawPlayerTurn(PlayerType player, String playerName);
    void drawGrid(PlayerType[] grid);
    void displayGameOverDialog();
}
