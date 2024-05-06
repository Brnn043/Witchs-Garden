import GUI.GameScreen;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Character.Slime;
import Items.Inventory.Clock;
import Items.Veggies.BaseVeggies;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Witch's Garden");

        GameController.getInstance();

        GameScreen gameScreen = new GameScreen(Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();


        stage.show();

        // Action in every 1 second
        // ex) decrease GameTimer, Veggie's water, Zombie&Player's Cooldowm, Veggie's growth point
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!GameController.getInstance().isGameover()){
                    Clock clock = GameController.getInstance().getClock();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // set clock timer coolDown
                    clock.setTimer(clock.getTimer()-1);

                    // set player coolDown
                    GameController.getInstance().getPlayer().setAttackCooldown(
                            GameController.getInstance().getPlayer().getAttackCooldown() - 1);

                    // decrease slime attack coolDown
                    for(Slime slime: GameController.getInstance().getSlimeList()) {
                        slime.setAttackCooldown(slime.getAttackCooldown() - 1);
                    }

                    // decrease veggie water & add growth point
                    for(BaseVeggies veggie : GameController.getInstance().getVeggiesList()) {
                        veggie.setWaterPoint(veggie.getWaterPoint() - veggie.getWaterDroppingRate());
                        veggie.setGrowthPoint(veggie.getGrowthPoint() + veggie.getGrowthRate());
                    }

                    // check if gameTimer == 0
                    GameController.getInstance().setGameTimer(GameController.getInstance().getGameTimer()-1);
                    if(GameController.getInstance().getGameTimer() == 0){
                        GameController.getInstance().setGameover(true);
                    }

                    System.out.println("TIMER : "+ GameController.getInstance().getGameTimer());
                }
            }
        });
        timer.start();


        AnimationTimer animation;
        animation = new AnimationTimer() {
            public void handle(long now) {
                if(GameController.getInstance().isGameover()){
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

