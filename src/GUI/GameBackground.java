package GUI;

import GUISharedObject.Entity;
import GUISharedObject.IRenderable;
import GUISharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import static GUISharedObject.RenderableHolder.getInstance;

public class GameBackground extends Canvas {
    public GameBackground(double width,double height) {
        super(width, height);
        setVisible(true);
        paintComponent();
    }

    public void paintComponent() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (int i =0; i< getInstance().getBackgroundEntities().size(); i+=1) {
            IRenderable entity = getInstance().getBackgroundEntities().get(i);
            entity.draw(gc);
        }
    }
}
