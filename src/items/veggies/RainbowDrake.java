package items.veggies;

import guiSharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

// this is RainbowDrake which is extended from BaseVeggie
// this veggie grows slowly
public class RainbowDrake extends BaseVeggie {
    public RainbowDrake() {
        super(15, 200, 6, 10, 42);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.rainbowDrakeIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
