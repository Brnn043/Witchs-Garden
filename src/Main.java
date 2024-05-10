import GUI.GameEnd;
import GUI.GamePanel;
import GUI.GameScreen;
import GUI.Manu;
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
    private int level = 1;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Manu manu = new Manu(() -> startGame(primaryStage), primaryStage);
        Scene scene = new Scene(manu, Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Witch's Garden");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void startGame(Stage primaryStage) {
        primaryStage.close();
        Stage gameStage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.setTitle("Witch's Garden");

        root.setAlignment(Pos.CENTER);
        GameController game = GameController.getInstance();
        GameScreen gameScreen = new GameScreen(Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);

        StackPane gameScreenWithEffect = new StackPane();
        gameScreenWithEffect.setAlignment(Pos.CENTER);

        gameScreenWithEffect.getChildren().addAll(game.getSunnyBackground(),gameScreen);
        GamePanel gamePanel = new GamePanel(game,gameScreen,gameScreenWithEffect);

        root.getChildren().addAll(gamePanel,gameScreenWithEffect);
        gameScreen.requestFocus();

        game.clearStats(level);
        game.initGames();

        gameStage.setResizable(false);
        gameStage.show();

        AnimationTimer animation;
        animation = new AnimationTimer() {
            public void handle(long now) {
                if(game.isGameover()){
                    this.stop();
                    gameEnd(gameStage);
                }else {
                    try {
                        gameScreen.paintComponent();
                        gamePanel.updateClockTimer();
                        gamePanel.updateTimerBar(game.getGameTimer());
                        gamePanel.updateVeggieCount();
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

    private void gameEnd(Stage gameStage) {
        gameStage.close();
        Stage endingStage = new Stage();
        GameEnd gameEnd = new GameEnd(() -> {
            level = level + 1;
            startGame(endingStage);
        }, endingStage);
        Scene scene =new Scene(gameEnd, Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        endingStage.setScene(scene);
        endingStage.setTitle("Witch's Garden");
        endingStage.setResizable(false);
        endingStage.show();
    }
}

