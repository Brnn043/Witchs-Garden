package Item.Veggie;

import GUISharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

// this is Daffodil which is extended from BaseVeggie
public class Daffodil extends BaseVeggie {
    public Daffodil() {
        super(20, 200, 6, 20, 33);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.redFlowerIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
