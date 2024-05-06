package GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Entity implements IRenderable{
    protected double x,y;
    protected int z;
    protected boolean visible,destroyed;

    protected Entity(){
        visible = true;
        destroyed = false;
    }

    @Override
    public boolean isDestroyed(){
        return destroyed;
    }

    @Override
    public boolean isVisible(){
        return visible;
    }

    @Override
    public int getZ(){
        return z;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillArc(x - 5, y - 5, 5 * 2, 5 * 2, 0, 360, ArcType.OPEN);
        gc.translate(x, y);
        gc.setFill(Color.YELLOW);
    }
}
