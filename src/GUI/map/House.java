package GUI.map;

import GUISharedObject.CollidableEntity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class House extends CollidableEntity implements WeatherChangeable {
    private String imagePath;

    public House(double x,double y,double width,double height) {
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
        Image houseImage = new Image(ClassLoader.getSystemResource(imagePath).toString());
        gc.drawImage(houseImage,getX() - getWidth()/2,getY() - getHeight()/2,getWidth(),getHeight());

    }
}