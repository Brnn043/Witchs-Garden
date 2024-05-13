package GUI.WeatherCanvas;
import Game.Config;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowyCanvas extends Canvas {
    private static final int NUM_SNOWFLAKES = 300;
    private List<Snowflake> snowflakes = new ArrayList<>();

    public SnowyCanvas() {
        super(Config.GAMESCREENWIDTH, Config.GAMESCREENHEIGHT);

        // Generate initial snowflakes
        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            snowflakes.add(new Snowflake());
        }

        // Animation timer to update snowflakes and render them
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateSnowflakes();
                render();
            }
        }.start();
    }

    private void updateSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.update();
        }
    }

    private void render() {
        // Get the graphics context
        GraphicsContext gc = getGraphicsContext2D();

        // Clear the canvas
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Render each snowflake
        for (Snowflake snowflake : snowflakes) {
            snowflake.render(gc);
        }
    }

    private static class Snowflake {
        private double x;
        private double y;
        private double speed;
        private double size;

        private Snowflake() {
            Random random = new Random();
            x = random.nextDouble() * Config.GAMESCREENWIDTH;
            y = random.nextDouble() * Config.GAMESCREENHEIGHT;
            speed = random.nextDouble() * 2 + 1;
            size = random.nextDouble() * 4 + 1;
        }

        private void update() {
            y += speed;
            if (y > Config.GAMESCREENHEIGHT) {
                y = 0;
            }
        }

        private void render(GraphicsContext gc) {
            gc.setFill(Color.WHITE);
            gc.fillOval(x, y, size, size);
        }
    }
}
