package GUI.GameBackground;

import GUI.Map.WeatherChangeable;
import GUISharedObject.Entity;
import Game.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static GUISharedObject.RenderableHolder.getInstance;

// this is the background of game in sunny weather
public class SunnyBackground extends Canvas {
    public SunnyBackground(double width, double height) {
        super(width, height);
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < getInstance().getBackgroundEntities().size(); i+=1) {
            Entity entity = getInstance().getBackgroundEntities().get(i);
            ( (WeatherChangeable) entity ).changeWeather(Config.Weather.SUNNY);
            entity.draw(gc);
        }
        setVisible(true);
    }
}
