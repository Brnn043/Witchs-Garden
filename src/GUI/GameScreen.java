package GUI;

import GUISharedObject.IRenderable;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.GameController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameScreen extends Canvas {

    public GameScreen(double width, double height) {
        super(width, height);
        this.setVisible(true);
        addListerner();
    }

    public void addListerner() {
        this.setOnKeyPressed((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), true);
        });

        this.setOnKeyReleased((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), false);
        });
    }

    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity.isVisible() && !entity.isDestroyed()) {
                entity.draw(gc);
            }
        }

    }

}