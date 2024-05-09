package GUI.Map;

import GUISharedObject.CollidableEntity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class House extends CollidableEntity {
    private String imagePath;

    public House(double x,double y,double width,double height) {
//        super(-5, -50, 250, 200);
        super(x,y,width,height);
        changeWeather(Config.Weather.SUNNY);
    }

    @Override
    public int getZ() { return 0; }

    public void changeWeather(Config.Weather weather) {
        if (weather == Config.Weather.SUNNY) imagePath = "House/Animated_House_Sunny.gif";
        else if (weather == Config.Weather.RAINY) imagePath = "House/Animated_House_Sunny.gif";
        else imagePath = "House/Animated_House_Snowy.gif";
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image houseImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(houseImage,getX(),getY(),getWidth(),getHeight());
    }
}