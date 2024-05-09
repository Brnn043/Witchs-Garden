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

        stage.show();

        // Action in every 1 second
        // ex) decrease GameTimer, Veggie's water, Zombie&Player's Cooldowm, Veggie's growth point
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!game.isGameover()){
                    Clock clock = game.getClock();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // spaw broom every 10 second
                    if(game.getGameTimer()%10 == 0){
                        Broom broom = new Broom();
                        game.getBroomOnGround().add(broom);
                        RenderableHolder.getInstance().add(broom);
                    }

                    // spaw slime every 7 second
                    if(game.getGameTimer()%7 == 0){
                        game.getNewSlime();
                    }

                    // set clock timer coolDown
                    clock.setTimer(clock.getTimer()-1);
                    gamePanel.updateClockTimer();


                    // decrease slime attack coolDown
                    for(Slime slime: game.getSlimeList()) {
                        slime.setAttackCooldown(slime.getAttackCooldown() - 1);
                        slime.attack();
                    }

                    // decrease veggie water & add growth point
                    for(BaseVeggies veggie : game.getVeggiesList()) {
                        if (game.getClock().getWeather() == Config.Weather.RAINY) {
                            veggie.setWaterPoint(veggie.getMAXWATER());
                        } else {
                            veggie.setWaterPoint(veggie.getWaterPoint() - veggie.getWaterDroppingRate());
                        }
                        veggie.setGrowthPoint(veggie.getGrowthPoint() + veggie.getGrowthRate());
                    }

                    // check if gameTimer == 0
                    game.setGameTimer(game.getGameTimer()-1);
                    if(game.getGameTimer() == 0){
                        game.setGameover(true);
                    }

                    gamePanel.updateTimerBar(game.getGameTimer());
                    System.out.println("TIMER : "+ game.getGameTimer());
                }
            }
        });
        timer.start();

        Thread playerAction = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!game.isGameover()){
                    try {
                        Thread.sleep(20);
                        game.getPlayer().action();
                        game.getPlayer().setAttackCooldown(game.getPlayer().getAttackCooldown() - 20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        playerAction.start();

        Thread slimeWalk = new Thread(()->{
            while (!game.isGameover()) {
                try {
                    Thread.sleep(100);
                    for (Slime slime : game.getSlimeList()) {
                        slime.walk();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        slimeWalk.start();


    AnimationTimer animation;
        animation = new AnimationTimer() {
            public void handle(long now) {
                if(game.isGameover()){
                    this.stop();
                }else {
                    try {
                        gameScreen.paintComponent();
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

