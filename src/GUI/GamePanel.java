package GUI;

import GUI.weatherCanvas.BlurCanvas;
import GUI.weatherCanvas.RainyCanvas;
import GUI.weatherCanvas.SnowyCanvas;
import Games.Config;
import Games.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        timerBar.setProgress(1);

        Text gameModeLabel = new Text("Potion no.2");

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

        sunnyButton.setOnAction(event -> handleSunnyButton());
        snowyButton.setOnAction(event -> handleSnowyButton());
        rainyButton.setOnAction(event -> handleRainyButton());

        getChildren().addAll(gameModeandTimer,weatherContainer);

        setBackground(new Background(new BackgroundFill(Color.web("#8F6F5C"), CornerRadii.EMPTY, Insets.EMPTY)));

        clockTimer.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
        clockTimer.setFill(Color.WHEAT);

        // Set font and color for timer text
        timerText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
        timerText.setFill(Color.WHEAT);

        // Set font and color for game mode label
        gameModeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
        gameModeLabel.setFill(Color.WHEAT);

        // Set cute style for buttons
        sunnyButton.setStyle("-fx-background-color: #F2E1C2; -fx-text-fill: #8F6F5C; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");
        snowyButton.setStyle("-fx-background-color: #C2F2F1; -fx-text-fill: #8F6F5C; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");
        rainyButton.setStyle("-fx-background-color: #D7C2F2; -fx-text-fill: #8F6F5C; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");

        // Set spacing between elements
        setSpacing(20);
        gameModeandTimer.setSpacing(10);
        buttonContainer.setSpacing(20);
        weatherContainer.setSpacing(10);
    }

    public void updateTimerBar(double time) {
        timerBar.setProgress(time/Config.GAMETIMER);
    }

    public void updateClockTimer() {
        clockTimer.setText("You can change the weather in "
                + gameController.getClock().getTimer()
                + " second(s).");
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
            gameScreenWithEffect.getChildren().addAll(gameController.getRainyBackground(),gameScreen,new BlurCanvas(),new RainyCanvas());
        }
        gameScreen.requestFocus();
    }
}
