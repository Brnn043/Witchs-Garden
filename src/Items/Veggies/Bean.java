package Items.Veggies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Bean extends BaseVeggies{
    public Bean() {
        super(10, 30, 2, 1, 5);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillArc(getPositionX() - 10, getPositionY() - 10, 10 * 2, 10 * 2, 0, 360, ArcType.OPEN);
    }
}
