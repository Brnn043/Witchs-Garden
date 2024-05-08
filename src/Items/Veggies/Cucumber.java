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

        // Calculate the width of the progress bar
        double HPPercentage = (double) getHp() / getMAXHP(); // Get HP percentage
        double HPBarWidth = 20 * HPPercentage; // Calculate progress bar width

        // Draw the progress bar
        double HPBarX = getX() - 10; // Start of progress bar
        double HPBarY = getY() + 15; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(HPBarX, HPBarY, 20, 5);
        gc.setFill(Color.ORANGERED);
        gc.fillRect(HPBarX, HPBarY, HPBarWidth, 5);


        // Calculate the width of the water bar
        double waterPercentage = (double) getWaterPoint() / getMAXWATER(); // Get HP percentage
        double waterBarWidth = 20 * waterPercentage; // Calculate progress bar width

        // Draw the progress bar
        double waterBarX = getX() - 10; // Start of progress bar
        double waterBarY = getY() + 22; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(waterBarX, waterBarY, 20, 5);
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(waterBarX, waterBarY, waterBarWidth, 5);
    }
}
