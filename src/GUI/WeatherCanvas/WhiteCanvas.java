package GUI.WeatherCanvas;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WhiteCanvas extends Canvas {
    private final GraphicsContext gc;
    private double alpha = 0.0; // Initial alpha value
    private final double minSpeed = 0.1; // Initial speed of change
    private final double maxSpeed = 0.5;
    private double speed;
    private int level;
    private double ratioTime;
    private final double speedScaleFactor = 0.6; // Scale factor for speed increase

    public WhiteCanvas(double width, double height, int level, double ratioTime) {
        super(width, height);
        this.level = level;
        this.ratioTime = ratioTime;
        this.speed = minSpeed;
        gc = getGraphicsContext2D();
        adjustSpeed();
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

    private void adjustSpeed() {
        // Calculate the maximum additional speed
        double maxAdditionalSpeed = maxSpeed - speed;

        // Calculate the speed factor based on level and time ratio
        double speedFactor = 0.1 * level + 0.4 * (1 - ratioTime);

        // Scale the speed factor to increase more slowly
        speedFactor *= speedScaleFactor;

        // Ensure the speed factor doesn't exceed the maximum additional speed
        speedFactor = Math.min(speedFactor, maxAdditionalSpeed);

        // Set the new speed
        speed += speedFactor;

//        System.out.println("Speed of WhiteCanvas : "+speed);
    }
}
