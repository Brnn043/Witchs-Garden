package GUI;

import GUI.WeatherCanvas.BlurCanvas;
import GUI.WeatherCanvas.RainyCanvas;
import GUI.WeatherCanvas.SnowyCanvas;
import Games.Config;
import Games.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
        setWidth(Config.GAMEFRAMEWIDTH);
        Text text = new Text("Timer : ");
        timerBar = new ProgressBar();
        timerBar.setPrefWidth(Config.GAMEFRAMEWIDTH-50);
        timerBar.setStyle("-fx-accent: violet;");
        timerBar.setProgress(1);

        Button sunnyButton = new Button("Sunny");
        Button snowyButton = new Button("Snowy");
        Button rainyButton = new Button("Rainy");

        sunnyButton.setOnAction(event -> handleSunnyButton());
        snowyButton.setOnAction(event -> handleSnowyButton());
        rainyButton.setOnAction(event -> handleRainyButton());

        clockTimer  = new Text(Integer.toString(gameController.getClock().getTimer()));

        getChildren().addAll(text,timerBar,sunnyButton,snowyButton,rainyButton,clockTimer);
    }

    public void updateTimerBar(double time) {
        timerBar.setProgress(time/Config.GAMETIMER);
    }

    public void updateClockTimer() {
        clockTimer.setText(Integer.toString(gameController.getClock().getTimer()));
    }

    public void handleSunnyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.SUNNY)) {
            gameController.getBackgroundImage().changeWeather(Config.Weather.SUNNY);
            gameController.getHouse().changeWeather(Config.Weather.SUNNY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameScreen);
        }
        gameScreen.requestFocus();
    }

    public void handleSnowyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.SNOWY)) {
            gameController.getBackgroundImage().changeWeather(Config.Weather.SNOWY);
            gameController.getHouse().changeWeather(Config.Weather.SNOWY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameScreen,new SnowyCanvas());
        }
        gameScreen.requestFocus();
    }

    public void handleRainyButton() {
        if (gameController.getClock().changeSeason(Config.Weather.RAINY)) {
            gameController.getBackgroundImage().changeWeather(Config.Weather.RAINY);
            gameController.getHouse().changeWeather(Config.Weather.SUNNY);
            gameScreenWithEffect.getChildren().clear();
            gameScreenWithEffect.getChildren().addAll(gameScreen,new BlurCanvas(),new RainyCanvas());
        }
        gameScreen.requestFocus();
    }
}
