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
    private final float MAXGROWTHPOINT;
    private float growthRate;
    private final float MAXGROWTHRATE;
    private float Hp;
    private float waterPoint;
    private float waterDroppingRate;
    private final float MAXWATERDROPPINGRATE;
    private final float MAXWATER;
    private final int MAXHP;
    private double width;
    private double height;

    public BaseVeggie(int hp, float maxWater, float growthRate, float waterDroppingRate, int maxGrowthPoint) {
        super();
        MAXHP = hp;
        setHp(MAXHP);
        MAXWATER = maxWater;
        setWaterPoint(this.MAXWATER);
        MAXGROWTHRATE = growthRate;
        setGrowthRate(MAXGROWTHRATE);
        MAXWATERDROPPINGRATE = waterDroppingRate;
        setWaterDroppingRate(MAXWATERDROPPINGRATE);
        setGrowthPoint(0);
        this.MAXGROWTHPOINT = maxGrowthPoint;

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
        }
        setX(posX);
        setY(posY);
        setCollected(false);
    }

    @Override
    public void collected() {
        if (getGrowthPoint() < MAXGROWTHPOINT) return;
        GameController.getInstance().collectVeggie(this);
        RenderableHolder.collectSound.play();
    }

    @Override
    public boolean isCollected() { return this.isCollected; }

    @Override
    public void weatherEffected() {
        Config.Weather weather = GameController.getInstance().getClock().getWeather();
        if (weather == Config.Weather.SUNNY) {
            this.setGrowthRate(MAXGROWTHRATE * (float) 0.5);
            this.setWaterDroppingRate(MAXWATERDROPPINGRATE);
        } else if (weather == Config.Weather.SNOWY) {
            this.setGrowthRate(MAXGROWTHRATE * (float) 0.2);
            this.setWaterDroppingRate(MAXWATERDROPPINGRATE * (float) 0.4);
        } else if (weather == Config.Weather.RAINY) {
            this.setGrowthRate(MAXGROWTHRATE * (float) 0.7);
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
        float growthDegree = (MAXGROWTHPOINT - getGrowthPoint())/ MAXGROWTHPOINT * 360;
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
        double HPBarY = getY() + getHeight() / 2; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(HPBarX, HPBarY, 30, 5);
        gc.setFill(Color.ORANGERED);
        gc.fillRect(HPBarX, HPBarY, HPBarWidth, 5);


        // Calculate the width of the water bar
        double waterPercentage = (double) getWaterPoint() / getMaxWater(); // Get HP percentage
        double waterBarWidth = 30 * waterPercentage; // Calculate progress bar width

        // Draw the progress bar
        double waterBarX = getX() - 17; // Start of progress bar
        double waterBarY = getY() + getHeight() / 2 + 8; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(waterBarX, waterBarY, 30, 5);
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(waterBarX, waterBarY, waterBarWidth, 5);
    }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public float getGrowthRate() { return growthRate; }

    public void setGrowthRate(float growthRate) { this.growthRate = Math.min(growthRate, MAXGROWTHRATE); }

    public void setCollected(boolean collected) { isCollected = collected; }

    public float getHp() { return Hp; }

    public void setHp(float hp) { Hp = hp; }

    public float getWaterPoint() { return waterPoint; }

    public void setWaterPoint(float waterPoint) { this.waterPoint = waterPoint; }

    public float getGrowthPoint() { return growthPoint; }

    public void setGrowthPoint(float growthPoint) { this.growthPoint = Math.min(growthPoint, MAXGROWTHPOINT); }

    public float getWaterDroppingRate() { return waterDroppingRate; }

    public void setWaterDroppingRate(float waterDroppingRate) { this.waterDroppingRate = waterDroppingRate; }

    public int getMaxHp() {
        return MAXHP;
    }

    public float getMaxWater() {
        return MAXWATER;
    }
}
