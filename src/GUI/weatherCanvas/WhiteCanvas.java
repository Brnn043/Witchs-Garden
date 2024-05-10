package GUI.weatherCanvas;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WhiteCanvas extends Canvas {
    private final GraphicsContext gc;
    private double alpha = 0.0; // Initial alpha value
    private double speed = 0.1; // Initial speed of change
    public WhiteCanvas(double width, double height) {
        super(width, height);
        gc = getGraphicsContext2D();
    }
    public void start() {
        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                // Calculate elapsed time
                double elapsedTime = (now - lastUpdate) / 1_000_000_000.0; // Convert nanoseconds to seconds

                // Update alpha value
                alpha += speed * elapsedTime;

                // Clear canvas
                gc.clearRect(0, 0, getWidth(), getHeight());

                // Fill canvas with white color and adjusted transparency
                gc.setFill(Color.rgb(255, 255, 255, Math.min(1.0, alpha)));
                gc.fillRect(0, 0, getWidth(), getHeight());

                lastUpdate = now;
            }
        };

        // Start the animation timer
        timer.start();
    }
}
