package GUI;

import Games.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GamePanel extends Canvas {
    public GamePanel() {
        super(Config.GAMEFRAMEWIDTH, 100);
        GraphicsContext gc = getGraphicsContext2D();

        // Fill background color
        gc.setFill(Color.rgb(128, 88, 63));
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Set font and draw text
        gc.setFont(Font.font("Arial", 20));
        gc.setFill(Color.BLACK); // Set text color
        gc.fillText("Game Panel", 150, 50);
    }

}
