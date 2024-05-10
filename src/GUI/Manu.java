package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Manu extends GridPane {
    @FunctionalInterface
    public interface GameStarter {
        void startGame();
    }
    public Manu(GameStarter gameStarter, Stage stage) {
        Text titleText = new Text("The Witch's Garden");
        titleText.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20)); // Set font and size
        titleText.setFill(Color.BLACK);

        // howToPlayMenu
        VBox howToPlayMenu = new VBox();
        howToPlayMenu.setAlignment(Pos.CENTER);
        howToPlayMenu.setSpacing(5);
        howToPlayMenu.getChildren().addAll(
                new Text("Will insert Image Later")
        );
        howToPlayMenu.setVisible(false);
        howToPlayMenu.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));

        // creditMenu
        VBox creditMenu = new VBox();
        creditMenu.setAlignment(Pos.CENTER);
        creditMenu.setSpacing(5);
        creditMenu.getChildren().addAll(
                new Text("This project created by"),
                new Text("Naphat Serirak 6632061321"),
                new Text("*** Beam's name & student ID ****"),
                new Text("                   "),
                new Text("2110215 (2023/2)"),
                new Text("Programming Methodology I")
        );
        creditMenu.setVisible(false);
        creditMenu.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));

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
        howToPlayButton.setOnAction(e -> {
            creditMenu.setVisible(false);
            howToPlayMenu.setVisible(true);
        });



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
        craditButton.setOnAction(e -> {
            creditMenu.setVisible(true);
            howToPlayMenu.setVisible(false);
        });

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
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(20, 0, 30, 30));
        this.add(titleText, 0, 0);
        this.add(startButton, 0, 1);
        this.add(howToPlayButton, 0, 2);
        this.add(craditButton, 0, 3);
        this.add(exitButton, 0, 4);
        this.add(creditMenu, 5, 0, 75, 5);
        this.add(howToPlayMenu, 5, 0, 75, 5);
    }
}
