package Item.Veggie;

import GUISharedObject.Entity;
import GUISharedObject.RenderableHolder;
import Game.Config;
import Game.GameController;
import Item.Interface.Collectable;
import Item.Interface.WeatherEffectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

// this is base veggie which has water point ,growth rate ,and hp
public abstract class BaseVeggie extends Entity implements WeatherEffectable, Collectable {
    private boolean isCollected;
    private float growthPoint;
    private final float maxGrowthPoint;
    private float growthRate;
    private final float maxGrowthRate;
    private float Hp;
    private float waterPoint;
    private float waterDroppingRate;
    private final float maxWaterDroppingRate;
    private final float maxWater;
    private final int maxHp;
    private final double width;
    private final double height;

    public BaseVeggie(int hp, float maxWater, float growthRate, float waterDroppingRate, int maxGrowthPoint){
        super();

        maxHp = hp;
        setHp(maxHp);
        this.maxWater = maxWater;
        setWaterPoint(this.maxWater);
        maxGrowthRate = growthRate;
        setGrowthRate(maxGrowthRate);
        maxWaterDroppingRate = waterDroppingRate;
        setWaterDroppingRate(maxWaterDroppingRate);
        setGrowthPoint(0);
        this.maxGrowthPoint = maxGrowthPoint;

        spawnOnMap();
        z = 600;

        width = Config.VEGGIESIZE;
        height = Config.VEGGIESIZE;
    }

    @Override
    public void spawnOnMap() {
        double posX = Config.SPAWNLEFTBOUND + Math.random() * (Config.SPAWNRIGHTBOUND - Config.SPAWNLEFTBOUND);
        double posY = Config.SPAWNTOPBOUND + Math.random() * (Config.SPAWNBOTTOMBOUND - Config.SPAWNTOPBOUND);

        // check if this can spawn on that position ,
        // otherwise it will keep spawning until find the proper position
        while (!GameController.getInstance().isPositionAccesible(posX, posY,
                getWidth(), getHeight(),false)) {
            posX = Config.SPAWNLEFTBOUND + Math.random() * (Config.SPAWNRIGHTBOUND - Config.SPAWNLEFTBOUND);
            posY = Config.SPAWNTOPBOUND + Math.random() * (Config.SPAWNBOTTOMBOUND - Config.SPAWNTOPBOUND);
            System.out.println("Veggie cannot be spawn here. Find new pos...");
        }
        setX(posX);
        setY(posY);
        setCollected(false);
    }

    @Override
    public void collected() {
        if(getGrowthPoint() < maxGrowthPoint) return;
        GameController.getInstance().collectVeggie(this);
        RenderableHolder.collectSound.play();
    }

    @Override
    public boolean isCollected() { return this.isCollected; }

    @Override
    public void weatherEffected() {
        Config.Weather weather = GameController.getInstance().getClock().getWeather();
        if ( weather == Config.Weather.SUNNY ) {
            this.setGrowthRate(maxGrowthRate * (float) 0.5);
            this.setWaterDroppingRate(maxWaterDroppingRate);
        } else if ( weather == Config.Weather.SNOWY ) {
            this.setGrowthRate(maxGrowthRate * (float) 0.2);
            this.setWaterDroppingRate(maxWaterDroppingRate * (float) 0.4);
        } else if ( weather == Config.Weather.RAINY) {
            this.setGrowthRate(maxGrowthRate * (float) 0.7);
            this.setWaterDroppingRate(0);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        drawGrowthDegree(gc);
        drawBar(gc);
    }

    private void drawGrowthDegree(GraphicsContext gc) {
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        float growthDegree = (maxGrowthPoint - getGrowthPoint())/ maxGrowthPoint * 360;
        gc.strokeArc(getX() - getWidth(), getY() - getHeight(),
                getWidth() * 2, getHeight() * 2,
                0,growthDegree, ArcType.OPEN );
        drawBar(gc);
    }
    private void drawBar(GraphicsContext gc) {
        // Calculate the width of the progress bar
        double HPPercentage = (double) getHp() / getMaxHp(); // Get HP percentage
        double HPBarWidth = 30 * HPPercentage; // Calculate progress bar width

        // Draw the progress bar
        double HPBarX = getX() - 17; // Start of progress bar
        double HPBarY = getY() + (double) getHeight() / 2; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(HPBarX, HPBarY, 30, 5);
        gc.setFill(Color.ORANGERED);
        gc.fillRect(HPBarX, HPBarY, HPBarWidth, 5);


        // Calculate the width of the water bar
        double waterPercentage = (double) getWaterPoint() / getMaxWater(); // Get HP percentage
        double waterBarWidth = 30 * waterPercentage; // Calculate progress bar width

        // Draw the progress bar
        double waterBarX = getX() - 17; // Start of progress bar
        double waterBarY = getY() + (double) getHeight() / 2 + 8; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(waterBarX, waterBarY, 30, 5);
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(waterBarX, waterBarY, waterBarWidth, 5);
    }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public float getGrowthRate() { return growthRate; }

    public void setGrowthRate(float growthRate) { this.growthRate = Math.min(growthRate, maxGrowthRate); }

    public void setCollected(boolean collected) { isCollected = collected; }

    public float getHp() { return Hp; }

    public void setHp(float hp) { Hp = hp; }

    public float getWaterPoint() { return waterPoint; }

    public void setWaterPoint(float waterPoint) { this.waterPoint = waterPoint; }

    public float getGrowthPoint() { return growthPoint; }

    public void setGrowthPoint(float growthPoint) { this.growthPoint = Math.min(growthPoint, maxGrowthPoint); }

    public float getWaterDroppingRate() { return waterDroppingRate; }

    public void setWaterDroppingRate(float waterDroppingRate) { this.waterDroppingRate = waterDroppingRate; }

    public int getMaxHp() {
        return maxHp;
    }

    public float getMaxWater() {
        return maxWater;
    }
}
