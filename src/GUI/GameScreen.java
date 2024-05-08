package GUI;

import GUISharedObject.IRenderable;
import GUISharedObject.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import static GUISharedObject.RenderableHolder.*;

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
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        for (int i =0; i< getInstance().getEntities().size(); i+=1) {
            IRenderable entity = getInstance().getEntities().get(i);
            if (entity.isVisible() && !entity.isDestroyed()) {
                entity.draw(gc);
            }
        }

    }

}