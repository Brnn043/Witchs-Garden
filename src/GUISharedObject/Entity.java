package GUISharedObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

// the entity of the game
public class Entity{
    protected double x,y;
    protected int z;
    protected boolean visible,destroyed;

    protected Entity(double x,double y){
        setX(x);
        setY(y);
        visible = true;
        destroyed = false;
    }

    protected Entity(){
        visible = true;
        destroyed = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) { this.x = x; }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public boolean isVisible(){
        return visible;
    }

    public int getZ(){
        return z;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillArc(x - 5, y - 5, 5 * 2, 5 * 2, 0, 360, ArcType.OPEN);
        gc.translate(x, y);
        gc.setFill(Color.YELLOW);
    }
}
