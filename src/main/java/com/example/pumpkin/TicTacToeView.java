package com.example.pumpkin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeView extends Application {
    private Label scoreLabel = new Label("Player: 0 - Computer: 0");
    private Button[][] buttons = new Button[3][3];
    private TicTacToeController controller;
    private TicTacToeModel model;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        model = new TicTacToeModel();
        controller = new TicTacToeController(this, model);
        stage =  primaryStage;
        VBox root = new VBox();
        root.getChildren().add(scoreLabel);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(" ");
                buttons[i][j].setPrefSize(100, 100);
                int row = i, col = j;
                buttons[i][j].setOnAction(e -> controller.handlePlayerMove(row, col));
                gridPane.add(buttons[i][j], j, i);
            }
        }
        root.getChildren().add(gridPane);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    public void updateBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    public void updateScore(int playerScore, int computerScore) {
        scoreLabel.setText("Player: " + playerScore + " - Computer: " + computerScore);
    }

    public void showResult(String message) {
        Stage resultStage = new Stage();
        Label resultLabel = new Label(message);
        Button okButton = new Button("OK");

        okButton.setOnAction(e -> {
            resultStage.close();
            controller.resetGame();
        });

        VBox layout = new VBox(resultLabel, okButton);
        resultStage.setScene(new Scene(layout, 200, 100));
        resultStage.show();
    }
}   