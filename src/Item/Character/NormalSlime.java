package Item.Character;

import GUISharedObject.RenderableHolder;
import Game.Config;
import javafx.scene.canvas.GraphicsContext;

// this is NormalSlime which is extended from Slime
public class NormalSlime extends Slime {
    public NormalSlime() {

        super(Config.SLIMEMINSPEEDRATE, 5, 2);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.normalSlimeSprite, posX, posY, getWidth(), getHeight());
    }
}
