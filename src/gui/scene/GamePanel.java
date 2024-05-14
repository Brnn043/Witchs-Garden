package gui.scene;

import gui.weatherCanvas.RainyCanvas;
import gui.weatherCanvas.SnowyCanvas;
import gui.weatherCanvas.WhiteCanvas;
import guiSharedObject.RenderableHolder;
import game.Config;
import game.GameController;
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

// this is gamePanel which is display at the top of gameScreen
public class GamePanel extends HBox {
    private GameController gameController;
    private GameScreen gameScreen;
    private StackPane gameScreenWithEffect;
    private ProgressBar timerBar;
    private Text clockTimer;
    private Text gameModeLabel;
    private Text redFlowerCount;
    private Text rainbowDrakeCount;
    private Text daffodilCount;
    private Text timerText;
    private int level;
    private Button sunnyButton;
    private Button snowyButton;
    private Button rainyButton;
    private VBox targetVeggieContainer;
    private HBox targetVeggie;

    public GamePanel(GameController gameController, GameScreen gameScreen, StackPane gameScreenWithEffect, int level) {
        super();
        this.gameController = gameController;
        this.gameScreen = gameScreen;
        this.gameScreenWithEffect = gameScreenWithEffect;
        this.level = level;

        initializeTimerText();
        initializeGameModeLabel();
        initializeClockTimer();
        initializeWeatherButton();
        initializeTargetVeggie();
        initializeTargetVeggieContainer();
        setStyle();
        addElement();
    }

    private void addElement() {
        HBox TimerLabel = new HBox(timerText,timerBar);
        VBox gameModeAndTimer = new VBox(gameModeLabel,TimerLabel);
        HBox buttonContainer = new HBox(sunnyButton,snowyButton,rainyButton);
        VBox weatherContainer = new VBox(clockTimer,buttonContainer);

        getChildren().addAll(targetVeggieContainer,gameModeAndTimer,weatherContainer);

        gameModeAndTimer.setAlignment(Pos.CENTER);
        gameModeAndTimer.setSpacing(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(20);
        weatherContainer.setSpacing(10);
        weatherContainer.setAlignment(Pos.CENTER);
    }

    private void setStyle() {
        // set style for this
        setAlignment(Pos.CENTER);
        setPrefSize(Config.GAMELABELWIDTH,Config.GAMELABELHEIGHT);
        setSpacing(10);
        setSpacing(50);
        setBackground(new Background(new BackgroundFill(Color.web("#8F6F5C"), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    private void initializeClockTimer() {
        clockTimer = new Text();
        updateClockTimer();
        clockTimer.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        clockTimer.setFill(Color.WHEAT);
    }

    private void initializeTargetVeggie() {
        ImageView redFlowerIcon = new ImageView(ClassLoader.getSystemResource("Veggie/RedFlower_Idle.gif").toString());
        ImageView rainbowDrakeIcon = new ImageView(ClassLoader.getSystemResource("Veggie/RainbowDrake_Idle.gif").toString());
        ImageView daffodilIcon = new ImageView(ClassLoader.getSystemResource("Veggie/Daffodil_Idle.gif").toString());

        redFlowerIcon.setPreserveRatio(true);
        rainbowDrakeIcon.setPreserveRatio(true);
        daffodilIcon.setPreserveRatio(true);
        redFlowerIcon.setFitWidth(35);
        rainbowDrakeIcon.setFitWidth(35);
        daffodilIcon.setFitWidth(35);

        initializeVeggieCount();
        updateVeggieCount();

        HBox targetRedFlower = new HBox(redFlowerIcon,redFlowerCount);
        HBox targetRainbowDrake = new HBox(rainbowDrakeIcon,rainbowDrakeCount);
        HBox targetDaffodil = new HBox(daffodilIcon,daffodilCount);
        targetVeggie = new HBox(targetRedFlower, targetDaffodil, targetRainbowDrake);

        targetRedFlower.setAlignment(Pos.CENTER);
        targetRedFlower.setSpacing(10);
        targetRainbowDrake.setAlignment(Pos.CENTER);
        targetRainbowDrake.setSpacing(10);
        targetDaffodil.setAlignment(Pos.CENTER);
        targetDaffodil.setSpacing(10);
    }

    private void initializeVeggieCount() {
        redFlowerCount = new Text();
        rainbowDrakeCount = new Text();
        daffodilCount = new Text();
        redFlowerCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        redFlowerCount.setFill(Color.WHEAT);
        rainbowDrakeCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        rainbowDrakeCount.setFill(Color.WHEAT);
        daffodilCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        daffodilCount.setFill(Color.WHEAT);
    }

    private void initializeTargetVeggieContainer() {
        Text veggieTitle = new Text("Collect the following veggies");
        targetVeggieContainer = new VBox(veggieTitle,targetVeggie);

        targetVeggieContainer.setAlignment(Pos.CENTER);
        targetVeggieContainer.setSpacing(10);
        veggieTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        veggieTitle.setFill(Color.WHEAT);
        targetVeggie.setAlignment(Pos.CENTER);
        targetVeggie.setSpacing(20);
    }

    private void initializeTimerText() {
        timerText = new Text("Timer : ");
        timerBar = new ProgressBar();

        timerText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        timerText.setFill(Color.WHEAT);
        timerBar.setPrefWidth(300);
        timerBar.setStyle("-fx-accent: violet;");
    }

    private void initializeGameModeLabel() {
        String potionName = "...";
        if (level == 1) potionName = "LOVE POTION";
        else if (level == 2) potionName = "STAR POTION";
        else if (level == 3) potionName = "LUNA POTION";

        gameModeLabel = new Text("Make the " + potionName);

        // Set font and color for game mode label
        gameModeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 19));
        gameModeLabel.setFill(Color.WHEAT);
    }

    private void initializeWeatherButton() {
        sunnyButton = new Button("Sunny");
        snowyButton = new Button("Snowy");
        rainyButton = new Button("Rainy");

        // set style
        sunnyButton.setStyle("-fx-background-color: #F2E1C2; -fx-text-fill: #8F6F5C; " +
                "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");
        snowyButton.setStyle("-fx-background-color: #C2F2F1; -fx-text-fill: #8F6F5C; " +
                "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");
        rainyButton.setStyle("-fx-background-color: #D7C2F2; -fx-text-fill: #8F6F5C; " +
                "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");

        // set action
        sunnyButton.setOnAction(event -> handleSunnyButton());
        snowyButton.setOnAction(event -> handleSnowyButton());
        rainyButton.setOnAction(event -> handleRainyButton());
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
        redFlowerCount.setText(GameController.getInstance().getRedFlowerCount()+"/"+gameController.getMaxRedFlower());
        rainbowDrakeCount.setText(GameController.getInstance().getRainbowDrakeCount()+"/"+gameController.getMaxRainbowDrake());
        daffodilCount.setText(GameController.getInstance().getDaffodilCount()+"/"+gameController.getMaxDaffodil());
    }


    public void handleSunnyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.SUNNY)) {
            RenderableHolder.clockSound.play();
            gameController.getBackgroundImage().changeWeather(Config.Weather.SUNNY);
            gameController.getHouse().changeWeather(Config.Weather.SUNNY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameController.getSunnyBackground(),gameScreen);
        }
        gameScreen.requestFocus();
    }

    public void handleSnowyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.SNOWY)) {
            RenderableHolder.clockSound.play();
            gameController.getBackgroundImage().changeWeather(Config.Weather.SNOWY);
            gameController.getHouse().changeWeather(Config.Weather.SNOWY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameController.getSnowyBackground(),gameScreen,new SnowyCanvas());
        }
        gameScreen.requestFocus();
    }

    public void handleRainyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.RAINY)) {
            RenderableHolder.clockSound.play();
            gameController.getBackgroundImage().changeWeather(Config.Weather.RAINY);
            gameController.getHouse().changeWeather(Config.Weather.SUNNY);
            gameScreenWithEffect.getChildren().clear();
            WhiteCanvas whiteCanvas = new WhiteCanvas(Config.GAMESCREENWIDTH, Config.GAMESCREENHEIGHT, gameController.getLevel(), (double) gameController.getGameTimer() /gameController.getMaxGameTimer());
            gameScreenWithEffect.getChildren().addAll(gameController.getRainyBackground(),gameScreen,whiteCanvas,new RainyCanvas());
            whiteCanvas.start();
        }
        gameScreen.requestFocus();
    }
}
