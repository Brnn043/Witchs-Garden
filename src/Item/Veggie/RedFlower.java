package Item.Veggie;

import GUISharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

// this is RedFlower which is extended from BaseVeggie
public class RedFlower extends BaseVeggie {

    public RedFlower() {
        super(10, 200, 7, 10, 18);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.rainbowDrakeIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
