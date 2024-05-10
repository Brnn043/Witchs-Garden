package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameEnd extends VBox {
    @FunctionalInterface
    public interface GameStarter {
        void startGame();
    }
    public GameEnd(Manu.GameStarter gameStarter, Stage stage) {
        // Add important Button
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        startButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        startButton.setPrefWidth(180);
        startButton.setPrefHeight(50);
        startButton.setOnMouseEntered(e -> {
            startButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), null)));
        });
        startButton.setOnMouseExited(e -> {
            startButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        });
        startButton.setOnAction(e -> gameStarter.startGame());

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        exitButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        exitButton.setPrefWidth(180);
        exitButton.setPrefHeight(50);
        exitButton.setOnMouseEntered(e -> {
            exitButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), null)));
        });
        exitButton.setOnMouseExited(e -> {
            exitButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        });
        exitButton.setOnAction(e -> stage.close());

        // Create layout and add the start button
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(startButton);
        this.getChildren().add(exitButton);
    }
}
