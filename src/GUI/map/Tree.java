package GUI.map;

import GUISharedObject.CollidableEntity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Tree extends CollidableEntity implements WeatherChangeable{

    private String imagePath;
    private int option;
    public Tree(double x, double y, double width, double height, int z, int option) {
        super(x, y, width, height);
        this.z = z;
        this.option = option;
        changeWeather(Config.Weather.SUNNY);
    }

    public void changeWeather(Config.Weather weather) {
        if (weather == Config.Weather.SUNNY) imagePath = "Tree/Sunny_Tree"+option+".png";
        else if (weather == Config.Weather.RAINY) imagePath = "Tree/Sunny_Tree"+option+".png";
        else imagePath = "Tree/Snowy_Tree"+option+".png";
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image treeImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(treeImage,getX(),getY(),getWidth(),getHeight());
    }
}
