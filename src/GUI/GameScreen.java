package GUI;

import GUISharedObject.IRenderable;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

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
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());

        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity.isVisible() && !entity.isDestroyed()) {
                entity.draw(gc);
            }
        }

    }

}