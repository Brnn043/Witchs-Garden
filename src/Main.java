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
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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


        GameController game = GameController.getInstance();
        GameScreen gameScreen = new GameScreen(Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT);
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();


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

                    // set clock timer coolDown
                    clock.setTimer(clock.getTimer()-1);

                    // set player coolDown
                    game.getPlayer().setAttackCooldown(
                            game.getPlayer().getAttackCooldown() - 1);

                    // decrease slime attack coolDown
                    for(Slime slime: game.getSlimeList()) {
                        slime.setAttackCooldown(slime.getAttackCooldown() - 1);
                        slime.walk();
                        slime.attack();
                    }

                    // decrease veggie water & add growth point
                    for(BaseVeggies veggie : game.getVeggiesList()) {
                        veggie.setWaterPoint(veggie.getWaterPoint() - veggie.getWaterDroppingRate());
                        veggie.setGrowthPoint(veggie.getGrowthPoint() + veggie.getGrowthRate());
                    }

                    // check if gameTimer == 0
                    game.setGameTimer(game.getGameTimer()-1);
                    if(game.getGameTimer() == 0){
                        game.setGameover(true);
                    }

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
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        playerAction.start();


        AnimationTimer animation;
        animation = new AnimationTimer() {
            public void handle(long now) {
                if(game.isGameover()){
                    this.stop();
                }else {
                    try {
                        gameScreen.paintComponent();
                        game.play();
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

