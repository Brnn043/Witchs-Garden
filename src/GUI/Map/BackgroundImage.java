package GUI.Map;

import GUISharedObject.Entity;
import GUISharedObject.RenderableHolder;
import Games.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundImage extends Entity {
    private String imagePath;

    public BackgroundImage() {
        super(0,0);
        changeWeather(Config.Weather.SUNNY);
    }

    public void changeWeather(Config.Weather weather) {
        if (weather == Config.Weather.SUNNY) imagePath = "Background/Sunny_Background.png";
        else if (weather == Config.Weather.RAINY) imagePath = "Background/Sunny_Background.png";
        else imagePath = "Background/Sunny_Background.png";
        //change later
    }

    @Override
    public int getZ() {
        return -999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image backgroundImage = new Image(ClassLoader.getSystemResource(imagePath).toString(), Config.GAMEFRAMEWIDTH,Config.GAMEFRAMEHEIGHT,true,false);
        gc.drawImage(backgroundImage,getX(),getY(),Config.GAMEFRAMEWIDTH,Config.GAMEFRAMEHEIGHT);
    }
}
