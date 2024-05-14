package items.veggies;

import guiSharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

// this is RedFlower which is extended from BaseVeggie
// this veggie grows very fast
public class RedFlower extends BaseVeggie {

    public RedFlower() {
        super(10, 200, 7, 10, 18);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.redFlowerIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
