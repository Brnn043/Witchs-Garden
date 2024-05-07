package Items.Veggies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Cucumber extends BaseVeggies{
    public Cucumber() {
        super(15, 20, 6, 2, 30);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillArc(getX() - 10, getY() - 10, 10 * 2, 10 * 2, 0, 360, ArcType.OPEN);
    }
}
