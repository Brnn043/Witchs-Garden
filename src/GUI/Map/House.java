package GUI.Map;

import GUISharedObject.CollidableEntity;
import GUISharedObject.Entity;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class House extends CollidableEntity {
    private String imagePath;

    public House() {
        super(-5, -50, 250, 200);
        changeWeather(Config.Weather.SUNNY);
    }

    @Override
    public int getZ() { return 0; }

    public void changeWeather(Config.Weather weather) {
        if (weather == Config.Weather.SUNNY) imagePath = "House/AnimatedHouse.gif";
        else if (weather == Config.Weather.RAINY) imagePath = "House/AnimatedHouse.gif";
        else imagePath = "House/AnimatedHouse.gif";
        //change later
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image houseImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH, Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(houseImage,getX(),getY(),getWidth(),getHeight());
    }
}