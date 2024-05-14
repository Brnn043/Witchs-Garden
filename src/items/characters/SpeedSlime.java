package items.characters;

import guiSharedObject.RenderableHolder;
import game.Config;
import javafx.scene.canvas.GraphicsContext;

// this is SpeedSlime which is extended from Slime
// this slime walks very fast
public class SpeedSlime extends Slime{
    public SpeedSlime() {
        super(Config.SLIMEMINSPEEDRATE + 10, 5, 1);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        double posX = getX() - getWidth() / 2;
        double posY = getY() - getHeight() / 2;
        gc.drawImage(RenderableHolder.speedSlimeSprite, posX, posY, getWidth(), getHeight());
    }
}
