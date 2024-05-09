package GUI.weatherCanvas;

import Games.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;

public class BlurCanvas extends Canvas {
    public BlurCanvas() {
        super(Config.GAMEFRAMEWIDTH,Config.GAMEFRAMEHEIGHT);
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.rgb(255,255,255,0.75));
        gc.fillRect(0,0,getWidth(),getHeight());
        BoxBlur blur = new BoxBlur(getWidth(),getHeight(),0);
        setEffect(blur);
    }
}
