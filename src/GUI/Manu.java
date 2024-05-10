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

public class Manu extends VBox {
    @FunctionalInterface
    public interface GameStarter {
        void startGame();
    }
    public Manu(GameStarter gameStarter, Stage stage) {
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

        Button howToPlayButton = new Button("How To Play");
        howToPlayButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        howToPlayButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        howToPlayButton.setPrefWidth(180);
        howToPlayButton.setPrefHeight(50);
        howToPlayButton.setOnMouseEntered(e -> {
            howToPlayButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), null)));
        });
        howToPlayButton.setOnMouseExited(e -> {
            howToPlayButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        });
//        howToPlayButton.setOnAction(e -> gameStarter.startGame());

        Button craditButton = new Button("Cradit");
        craditButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        craditButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        craditButton.setPrefWidth(180);
        craditButton.setPrefHeight(50);
        craditButton.setOnMouseEntered(e -> {
            craditButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), null)));
        });
        craditButton.setOnMouseExited(e -> {
            craditButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        });
//        craditButton.setOnAction(e -> gameStarter.startGame());

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
        this.getChildren().add(howToPlayButton);
        this.getChildren().add(craditButton);
        this.getChildren().add(exitButton);
    }
}
