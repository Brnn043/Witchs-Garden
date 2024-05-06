package GUI.Map;

import GUISharedObject.Entity;
import GUISharedObject.RenderableHolder;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundImage extends Entity {
    private String imagePath;

    public BackgroundImage() {
        this.imagePath = "Sunny_Background.png";
    }

    public BackgroundImage(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int getZ() {
        return -999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image backgroundImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH,Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(backgroundImage,0,0,Config.GAMEFRAMEWIDTH,Config.GAMEFRAMEHEIGHT);
    }
}
