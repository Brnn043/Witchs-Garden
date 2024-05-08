package Games;

import GUI.GamePanel;
import javafx.scene.control.ProgressBar;

public class GamePanelController {
    private static GamePanelController instance;
    private final GamePanel gamePanel;

    public GamePanelController() {
        this.gamePanel = new GamePanel();
    }
    public static GamePanelController getInstance() {
        if(instance == null) instance = new GamePanelController();
        return instance;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void updateTimerBar(double time){
        ProgressBar timerBar = gamePanel.getTimerBar();
        timerBar.setProgress(time/Config.GAMETIMER);
    }
}
