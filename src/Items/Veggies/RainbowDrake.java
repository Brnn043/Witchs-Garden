package Items.Veggies;

import GUISharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

public class RainbowDrake extends BaseVeggies {
    public RainbowDrake() {
        super(15, 200, 6, 10, 42);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - (double) getWidth() / 2;
        double posY = getY() - (double) getHeight() / 2;
        gc.drawImage(RenderableHolder.rainbowDrakeIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
