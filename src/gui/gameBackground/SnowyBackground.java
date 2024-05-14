package gui.gameBackground;

import gui.map.WeatherChangeable;
import guiSharedObject.Entity;
import game.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static guiSharedObject.RenderableHolder.getInstance;

// this is the background of game in snowy weather
public class SnowyBackground extends Canvas {
    public SnowyBackground(double width, double height) {
        super(width, height);
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (Entity entity : getInstance().getBackgroundEntities()) {
            ( (WeatherChangeable) entity ).changeWeather(Config.Weather.SNOWY);
            entity.draw(gc);
        }
        setVisible(true);
    }
}
