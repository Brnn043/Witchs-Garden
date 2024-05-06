package GUI.Map;

import GUISharedObject.Entity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class House extends Entity {
    public House() {}

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        String imagePath = "House.png";
        Image houseImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(houseImage,-5,-50,250,200);
    }
}
