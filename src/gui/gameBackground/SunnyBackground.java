package gui.gameBackground;

import gui.map.WeatherChangeable;
import guiSharedObject.Entity;
import game.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static guiSharedObject.RenderableHolder.getInstance;

// this is the background of game in sunny weather
public class SunnyBackground extends Canvas {
    public SunnyBackground(double width, double height) {
        super(width, height);
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (Entity entity : getInstance().getBackgroundEntities()) {
            ( (WeatherChangeable) entity ).changeWeather(Config.Weather.SUNNY);
            entity.draw(gc);
        }
        setVisible(true);
    }
}
