package com.example.pumpkin;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.Random;

public class TicTacToeController {
    private TicTacToeModel model;
    private TicTacToeView view;
    private Random random = new Random();
    private boolean isGameOver = false;

    public TicTacToeController(TicTacToeView view, TicTacToeModel model) {
        this.view = view;
        this.model = model;
    }

    public void handlePlayerMove(int row, int col) {
        if (!isGameOver && model.makeMove(row, col)) {
            view.updateBoard(model.getBoard());
            checkGameState();

            if (!isGameOver && model.getCurrentPlayer() == 'O') {
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(e -> computerMove());
                delay.play();
            }
        }
    }

    private void computerMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!model.makeMove(row, col));
        view.updateBoard(model.getBoard());

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> checkGameState());
        delay.play();
    }

    private void checkGameState() {
        char winner = model.checkWinner();
        if (winner != ' ') {
            isGameOver = true;

                if (winner == 'X') {
                    model.incrementPlayerScore();
                    view.showResult("Player wins!");
                } else {
                    model.incrementComputerScore();
                    view.showResult("Computer wins!");
                }
        } else if (model.isBoardFull()) {
            isGameOver = true;
            view.showResult("It's a tie!");
        }
    }

    public void resetGame() {
        model.resetBoard();
        view.updateBoard(model.getBoard());
        view.updateScore(model.getPlayerScore(), model.getComputerScore());
        isGameOver = false;
    }
}
