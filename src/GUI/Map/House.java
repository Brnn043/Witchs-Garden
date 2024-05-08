package GUI.Map;

import GUISharedObject.CollidableEntity;
import GUISharedObject.Entity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class House extends CollidableEntity {

    public House() {
        super(-5, -50, 250, 200);
//        super(300, 300, 250, 200);
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        String imagePath = "House/AnimatedHouse.gif";
        Image houseImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(houseImage,getX(),getY(),getWidth(),getHeight());
    }
}