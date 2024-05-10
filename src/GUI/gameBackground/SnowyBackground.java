package GUI.gameBackground;

import GUI.map.WeatherChangeable;
import GUISharedObject.IRenderable;
import Games.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static GUISharedObject.RenderableHolder.getInstance;

public class SnowyBackground extends Canvas {
    public SnowyBackground(double width, double height) {
        super(width, height);
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (int i =0; i< getInstance().getBackgroundEntities().size(); i+=1) {
            IRenderable entity = getInstance().getBackgroundEntities().get(i);
            ( (WeatherChangeable) entity ).changeWeather(Config.Weather.SNOWY);
            entity.draw(gc);
        }
        setVisible(true);
    }
}