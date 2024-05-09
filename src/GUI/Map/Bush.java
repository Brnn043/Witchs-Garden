package GUI.Map;

import GUISharedObject.CollidableEntity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bush extends CollidableEntity {
    private String imagePath;

    public Bush(double x, double y, double width, double height,int z, int option) {
        super(x, y, width, height);
        this.z = z;
        imagePath = "Bush/Bush"+Integer.toString(option)+".png";
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image bushImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(bushImage,getX(),getY(),getWidth(),getHeight());
    }
}
