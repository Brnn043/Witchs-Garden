package Items.Character;

import GUISharedObject.RenderableHolder;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;

public class HitHardSlime extends Slime{
    public HitHardSlime() {
        super(Config.SLIMEMINSPEEDRATE, 12, 5);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.hitHardSlimeSprite, posX, posY, getWidth(), getHeight());
    }
}
