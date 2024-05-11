package GUI.Map;

import GUISharedObject.CollidableEntity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bush extends CollidableEntity implements WeatherChangeable {
    private String imagePath;
    private int option;

    public Bush(double x, double y, double width, double height,int z, int option) {
        super(x, y, width, height);
        this.z = z;
        this.option = option;
        changeWeather(Config.Weather.SUNNY);
    }

    public void changeWeather(Config.Weather weather) {
        if (weather == Config.Weather.SUNNY) imagePath = "Bush/Sunny_Bush"+option+".png";
        else if (weather == Config.Weather.RAINY) imagePath = "Bush/Sunny_Bush"+option+".png";
        else imagePath = "Bush/Snowy_Bush"+option+".png";
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image bushImage = new Image(ClassLoader.getSystemResource(imagePath).toString());
        gc.drawImage(bushImage,getX() - getWidth()/2,getY() - getHeight()/2,getWidth(),getHeight());
    }
}
