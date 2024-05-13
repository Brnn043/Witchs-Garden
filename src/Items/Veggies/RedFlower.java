package Items.Veggies;

import GUISharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

public class RedFlower extends BaseVeggies {

    public RedFlower() {
        super(10, 200, 7, 10, 18);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - (double) getWidth() / 2;
        double posY = getY() - (double) getHeight() / 2;
        gc.drawImage(RenderableHolder.rainbowDrakeIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
