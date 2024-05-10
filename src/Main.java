import GUI.GamePanel;
import GUI.GameScreen;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Character.Slime;
import Items.Inventory.Clock;
import Items.Inventory.Broom;
import Items.Veggies.BaseVeggies;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Consolas", 18));
        startButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        startButton.setPrefWidth(180);
        startButton.setPrefHeight(50);
        startButton.setOnMouseEntered(e -> {
            startButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), null)));
        });
        startButton.setOnMouseExited(e -> {
            startButton.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), null)));
        });
        startButton.setOnAction(e -> startGame(primaryStage));

        // Create layout and add the start button
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(startButton);

        // Set scene and show the main window
        Scene scene = new Scene(root, Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void startGame(Stage primaryStage) {
        primaryStage.close();
        Stage stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Witch's Garden");

        root.setAlignment(Pos.CENTER);
        GameController game = GameController.getInstance();
        GameScreen gameScreen = new GameScreen(Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);

        StackPane gameScreenWithEffect = new StackPane();
        gameScreenWithEffect.setAlignment(Pos.CENTER);

        gameScreenWithEffect.getChildren().addAll(game.getSunnyBackground(),gameScreen);
        GamePanel gamePanel = new GamePanel(game,gameScreen,gameScreenWithEffect);

        root.getChildren().addAll(gamePanel,gameScreenWithEffect);
        gameScreen.requestFocus();
        game.initGames();

        stage.setResizable(false);
        stage.show();

        AnimationTimer animation;
            animation = new AnimationTimer() {
                public void handle(long now) {
                    if(game.isGameover()){
                        this.stop();
                    }else {
                        try {
                            gameScreen.paintComponent();
                            gamePanel.updateClockTimer();
                            gamePanel.updateTimerBar(game.getGameTimer());
                            GameController.play();
                            RenderableHolder.getInstance().update();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            animation.start();
    }
}

