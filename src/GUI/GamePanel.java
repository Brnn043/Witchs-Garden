package GUI;

import Games.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;

public class GamePanel extends HBox {
    private ProgressBar timerBar;
    public GamePanel() {
        super();
        setAlignment(Pos.CENTER);
        setWidth(Config.GAMEFRAMEWIDTH);
        Text text = new Text("Timer : ");
        timerBar = new ProgressBar();
        timerBar.setPrefWidth(Config.GAMEFRAMEWIDTH-50);
        timerBar.setStyle("-fx-accent: violet;");
        timerBar.setProgress(1);
        getChildren().addAll(text,timerBar);
    }

    public ProgressBar getTimerBar() {
        return timerBar;
    }

    public void setTimerBar(ProgressBar timerBar) {
        this.timerBar = timerBar;
    }
}
