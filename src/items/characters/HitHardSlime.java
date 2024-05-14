package items.characters;

import guiSharedObject.RenderableHolder;
import game.Config;
import javafx.scene.canvas.GraphicsContext;

// this is HitHardSlime which is extended from Slime
// this slime has very strong damage
public class HitHardSlime extends Slime {
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
