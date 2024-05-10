import GUI.GameEnd;
import GUI.GamePanel;
import GUI.GameScreen;
import GUI.Manu;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        // download scene
        VBox downloadRoot = new VBox();
        Scene dowloadScene = new Scene(downloadRoot, Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        downloadRoot.setAlignment(Pos.CENTER);
        Text downloadText = new Text("Please wait while we are casting a spell...");
        downloadText.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 14));
        downloadRoot.getChildren().add(downloadText);

        downloadRoot.setBackground(new Background(new BackgroundFill(Color.web("#8F6F5C"), null, null)));
        primaryStage.setScene(dowloadScene);

        // game scene
        VBox root = new VBox();
        Scene scene = new Scene(root);

        root.setAlignment(Pos.CENTER);
        GameController game = GameController.getInstance();
        game.clearStats(level);
        game.initGames();
        GameScreen gameScreen = new GameScreen(Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);

        StackPane gameScreenWithEffect = new StackPane();
        gameScreenWithEffect.setAlignment(Pos.CENTER);

        gameScreenWithEffect.getChildren().addAll(game.getSunnyBackground(),gameScreen);
        GamePanel gamePanel = new GamePanel(game,gameScreen,gameScreenWithEffect);

        root.getChildren().addAll(gamePanel,gameScreenWithEffect);
        gameScreen.requestFocus();

        primaryStage.setScene(scene);


        AnimationTimer animation;
        animation = new AnimationTimer() {
            public void handle(long now) {
                if(game.isGameover()){
                    this.stop();
                    gameEnd(primaryStage);
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

    private void gameEnd(Stage primaryStage) {
        GameEnd gameEnd = new GameEnd(() -> {
            if(GameController.getInstance().getGameTimer()!=0){
                level = level + 1 ;
            }
            if(level == 4){
                ConpleteGame(primaryStage);
            }else{
                startGame(primaryStage);
            }
        }, primaryStage);
        Scene scene =new Scene(gameEnd, Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        primaryStage.setScene(scene);
    }

    private void ConpleteGame(Stage primaryStage) {
        VBox completeGame = new VBox();
        completeGame.setAlignment(Pos.CENTER);
        Text completeText = new Text("Well Done! The Witch regain all her power. You had clear this game!!!");
        completeText.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 14));
        completeGame.setBackground(new Background(new BackgroundFill(Color.web("#8F6F5C"), null, null)));

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
        exitButton.setOnAction(e -> primaryStage.close());

        Scene scene =new Scene(completeGame, Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        completeGame.setSpacing(20);
        completeGame.getChildren().add(completeText);
        completeGame.getChildren().add(exitButton);

        primaryStage.setScene(scene);
    }
}

