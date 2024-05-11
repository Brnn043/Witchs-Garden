package GUI.WeatherCanvas;

import Games.Config;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RainyCanvas extends Canvas {

    private static final double MAX_SPEED = 15.0;
    private static final int NUM_RAINDROPS = 200;

    private List<Raindrop> raindrops = new ArrayList<>();

    public RainyCanvas() {
        super(Config.GAMESCREENWIDTH, Config.GAMESCREENHEIGHT);

        // Generate initial raindrops
        for (int i = 0; i < NUM_RAINDROPS; i++) {
            raindrops.add(new Raindrop());
        }

        // Animation timer to update raindrops and render them
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateRaindrops();
                render();
            }
        }.start();
    }

    private void updateRaindrops() {
        for (Raindrop raindrop : raindrops) {
            raindrop.update();
        }
    }

    private void render() {
        // Get the graphics context
        GraphicsContext gc = getGraphicsContext2D();

        // Clear the canvas
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Render each raindrop
        for (Raindrop raindrop : raindrops) {
            raindrop.render(gc);
        }
    }

    private static class Raindrop {
        private double x;
        private double y;
        private double speed;

        private Raindrop() {
            Random random = new Random();
            x = random.nextDouble() * Config.GAMESCREENWIDTH; // Adjust width as needed
            y = random.nextDouble() * Config.GAMESCREENHEIGHT; // Adjust height as needed
            speed = random.nextDouble() * MAX_SPEED + 1;
        }

        private void update() {
            y += speed;
            if (y > 600) { // Adjust height as needed
                y = 0;
            }
        }

        private void render(GraphicsContext gc) {
            gc.setFill(Color.WHITE);
            gc.fillRect(x, y, 2, 10); // Adjust size as needed
        }
    }
}