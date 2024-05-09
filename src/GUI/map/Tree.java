package GUI.map;

import GUISharedObject.CollidableEntity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Tree extends CollidableEntity implements WeatherChangeable{

    private String imagePath;
    public Tree(double x, double y, double width, double height,int z,int option) {
        super(x, y, width, height);
        this.z = z;
        imagePath = "Tree/Tree"+Integer.toString(option)+".png";
    }

    public void changeWeather(Config.Weather weather) {

    }

    @Override
    public void draw(GraphicsContext gc) {
        Image treeImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(treeImage,getX(),getY(),getWidth(),getHeight());
    }
}
