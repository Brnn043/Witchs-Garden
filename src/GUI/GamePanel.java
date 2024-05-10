package GUI;

import GUI.weatherCanvas.RainyCanvas;
import GUI.weatherCanvas.SnowyCanvas;
import GUI.weatherCanvas.WhiteCanvas;
import Games.Config;
import Games.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;


public class GamePanel extends HBox {
    private final GameController gameController;
    private final GameScreen gameScreen;
    private final StackPane gameScreenWithEffect;
    private final ProgressBar timerBar;
    private final Text clockTimer;
    private final Text gameModeLabel;
    private final Text redFlowerCount;
    private final Text rainbowDrakeCount;
    private final Text daffodilCount;

    public GamePanel(GameController gameController,GameScreen gameScreen,StackPane gameScreenWithEffect) {
        super();
        this.gameController = gameController;
        this.gameScreen = gameScreen;
        this.gameScreenWithEffect = gameScreenWithEffect;
        setAlignment(Pos.CENTER);
        setPrefSize(Config.GAMELABELWIDTH,Config.GAMELABELHEIGHT);
        setSpacing(10);
        Text timerText = new Text("Timer : ");
        timerBar = new ProgressBar();
        timerBar.setPrefWidth(300);
        timerBar.setStyle("-fx-accent: violet;");

        gameModeLabel = new Text("Make the potion no.2");

        HBox TimerLabel = new HBox(timerText,timerBar);

        VBox gameModeandTimer = new VBox(gameModeLabel,TimerLabel);
        gameModeandTimer.setAlignment(Pos.CENTER);

        clockTimer = new Text();
        updateClockTimer();

        Button sunnyButton = new Button("Sunny");
        Button snowyButton = new Button("Snowy");
        Button rainyButton = new Button("Rainy");

        HBox buttonContainer = new HBox(sunnyButton,snowyButton,rainyButton);
        buttonContainer.setAlignment(Pos.CENTER);

        VBox weatherContainer = new VBox(clockTimer,buttonContainer);
        weatherContainer.setAlignment(Pos.CENTER);

        ImageView redFlowerIcon = new ImageView(ClassLoader.getSystemResource("Veggie/RedFlower_Idle.gif").toString());
        ImageView rainbowDrakeIcon = new ImageView(ClassLoader.getSystemResource("Veggie/RainbowDrake_Idle.gif").toString());
        ImageView daffodilIcon = new ImageView(ClassLoader.getSystemResource("Veggie/Daffodil_Idle.gif").toString());

        redFlowerIcon.setPreserveRatio(true);
        rainbowDrakeIcon.setPreserveRatio(true);
        daffodilIcon.setPreserveRatio(true);
        redFlowerIcon.setFitWidth(35);
        rainbowDrakeIcon.setFitWidth(35);
        daffodilIcon.setFitWidth(35);
        
        redFlowerCount = new Text();
        rainbowDrakeCount = new Text();
        daffodilCount = new Text();

        updateVeggieCount();

        HBox targetRedFlower = new HBox(redFlowerIcon,redFlowerCount);
        HBox targetRainbowDrake = new HBox(rainbowDrakeIcon,rainbowDrakeCount);
        HBox targetDaffodil = new HBox(daffodilIcon,daffodilCount);

        HBox targetVeggie = new HBox(targetRedFlower, targetDaffodil, targetRainbowDrake);

        Text veggieTitle = new Text("Collect the following veggies");

        VBox targetVeggieContainer = new VBox(veggieTitle,targetVeggie);

        sunnyButton.setOnAction(event -> handleSunnyButton());
        snowyButton.setOnAction(event -> handleSnowyButton());
        rainyButton.setOnAction(event -> handleRainyButton());

        getChildren().addAll(targetVeggieContainer,gameModeandTimer,weatherContainer);

        setSpacing(50);
        setBackground(new Background(new BackgroundFill(Color.web("#8F6F5C"), CornerRadii.EMPTY, Insets.EMPTY)));

        clockTimer.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        clockTimer.setFill(Color.WHEAT);

        // Set font and color for timer text
        timerText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        timerText.setFill(Color.WHEAT);

        // Set font and color for game mode label
        gameModeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
        gameModeLabel.setFill(Color.WHEAT);

        // Set cute style for buttons
        sunnyButton.setStyle("-fx-background-color: #F2E1C2; -fx-text-fill: #8F6F5C; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");
        snowyButton.setStyle("-fx-background-color: #C2F2F1; -fx-text-fill: #8F6F5C; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");
        rainyButton.setStyle("-fx-background-color: #D7C2F2; -fx-text-fill: #8F6F5C; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");

        // Set spacing between elements
        gameModeandTimer.setSpacing(10);
        buttonContainer.setSpacing(20);
        weatherContainer.setSpacing(10);

        redFlowerCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        redFlowerCount.setFill(Color.WHEAT);
        rainbowDrakeCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        rainbowDrakeCount.setFill(Color.WHEAT);
        daffodilCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        daffodilCount.setFill(Color.WHEAT);
        veggieTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        veggieTitle.setFill(Color.WHEAT);

        targetVeggieContainer.setAlignment(Pos.CENTER);
        targetVeggieContainer.setSpacing(10);

        targetVeggie.setAlignment(Pos.CENTER);
        targetVeggie.setSpacing(20);
        targetRedFlower.setAlignment(Pos.CENTER);
        targetRedFlower.setSpacing(10);
        targetRainbowDrake.setAlignment(Pos.CENTER);
        targetRainbowDrake.setSpacing(10);
        targetDaffodil.setAlignment(Pos.CENTER);
        targetDaffodil.setSpacing(10);
    }

    public void setGameModeLabel(String string) {
        gameModeLabel.setText(string);
    }

    public void updateTimerBar(double time) {
        timerBar.setProgress(time/gameController.getMaxGameTimer());
    }

    public void updateClockTimer() {
        clockTimer.setText("You can change the weather in "
                + gameController.getClock().getTimer()
                + " second(s).");
    }

    public void updateVeggieCount() {
        redFlowerCount.setText(GameController.getInstance().getRedFlowerCount()+"/"+gameController.getmaxRedFlower());
        rainbowDrakeCount.setText(GameController.getInstance().getRainbowDrakeCount()+"/"+gameController.getmaxRainbowDrake());
        daffodilCount.setText(GameController.getInstance().getDaffodilCount()+"/"+gameController.getmaxDaffodil());
    }


    public void handleSunnyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.SUNNY)) {
            gameController.getBackgroundImage().changeWeather(Config.Weather.SUNNY);
            gameController.getHouse().changeWeather(Config.Weather.SUNNY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameController.getSunnyBackground(),gameScreen);
        }
        gameScreen.requestFocus();
    }

    public void handleSnowyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.SNOWY)) {
            gameController.getBackgroundImage().changeWeather(Config.Weather.SNOWY);
            gameController.getHouse().changeWeather(Config.Weather.SNOWY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameController.getSnowyBackground(),gameScreen,new SnowyCanvas());
        }
        gameScreen.requestFocus();
    }

    public void handleRainyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.RAINY)) {
            gameController.getBackgroundImage().changeWeather(Config.Weather.RAINY);
            gameController.getHouse().changeWeather(Config.Weather.SUNNY);
            gameScreenWithEffect.getChildren().clear();
            WhiteCanvas whiteCanvas = new WhiteCanvas(Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT, gameController.getLevel(), (double) gameController.getGameTimer() /gameController.getMaxGameTimer());
            gameScreenWithEffect.getChildren().addAll(gameController.getRainyBackground(),gameScreen,whiteCanvas,new RainyCanvas());
            whiteCanvas.start();
        }
        gameScreen.requestFocus();
    }
}