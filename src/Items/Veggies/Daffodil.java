package Items.Veggies;

import GUISharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

public class Daffodil extends BaseVeggies {
    public Daffodil() {
        super(20, 200, 6, 20, 33);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - (double) getWidth() / 2;
        double posY = getY() - (double) getHeight() / 2;
        gc.drawImage(RenderableHolder.redFlowerIdleSprite, posX, posY, getWidth(), getHeight());
    }
}
