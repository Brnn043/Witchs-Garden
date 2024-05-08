package Games;

import GUI.GamePanel;

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
}
