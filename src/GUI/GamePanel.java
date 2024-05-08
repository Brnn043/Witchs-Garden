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
    private GameController gameController;
    private GameScreen gameScreen;
    private StackPane gameScreenWithEffect;
    private ProgressBar timerBar;
    private Button sunnyButton,rainyButton,snowyButton;

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

        sunnyButton = new Button("Sunny");
        snowyButton = new Button("Snowy");
        rainyButton = new Button("Rainy");

        sunnyButton.setOnAction(event -> handleSunnyButton());
        snowyButton.setOnAction(event -> handleSnowyButton());
        rainyButton.setOnAction(event -> handleRainyButton());

        getChildren().addAll(text,timerBar,sunnyButton,snowyButton,rainyButton);
    }

    public void updateTimerBar(double time){
        timerBar.setProgress(time/Config.GAMETIMER);
    }

    public void handleSunnyButton() {
        gameController.getBackgroundImage().changeWeather(Config.Weather.SUNNY);
        gameScreenWithEffect.getChildren().clear();
        gameScreenWithEffect.getChildren().addAll(gameScreen);
        gameScreen.requestFocus();
    }

    public void handleSnowyButton() {
        gameController.getBackgroundImage().changeWeather(Config.Weather.SNOWY);
        gameScreenWithEffect.getChildren().clear();
        gameScreenWithEffect.getChildren().addAll(gameScreen,new SnowyCanvas());
        gameScreen.requestFocus();
    }

    public void handleRainyButton() {
        gameController.getBackgroundImage().changeWeather(Config.Weather.RAINY);
        gameScreenWithEffect.getChildren().clear();
        gameScreenWithEffect.getChildren().addAll(gameScreen,new BlurCanvas(),new RainyCanvas());
        gameScreen.requestFocus();
    }
}
