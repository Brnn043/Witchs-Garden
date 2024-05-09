package Items.Veggies;

import GUISharedObject.Entity;
import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Interfaces.Collectable;
import Items.Interfaces.WeatherEffectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public abstract class BaseVeggies extends Entity implements WeatherEffectable, Collectable {
    private boolean isCollected;
    private float growthPoint;
    private float growthRate;
    private final float MAXGROWTHRATE;
    private int Hp;
    private float waterPoint;
    private float waterDroppingRate;
    private final float MAXWATERDROPPINGRATE;
    private final float MAXWATER;
    private int price;
    private final int MAXHP;
    private final int width;
    private final int height;

    // haven't implemented veggieHpBar yet


    public BaseVeggies(int hp,float maxWater,float growthRate,float waterDroppingRate,int price){
        super();
        MAXHP = hp;
        setHp(MAXHP);
        MAXWATER = maxWater;
        setWaterPoint(MAXWATER);
        MAXGROWTHRATE = growthRate;
        setGrowthRate(MAXGROWTHRATE);
        MAXWATERDROPPINGRATE = waterDroppingRate;
        setWaterDroppingRate(MAXWATERDROPPINGRATE);
        setPrice(price);
        spawnOnMap();
        z = getZ() + 600;
        width = 10 * 2;
        height = 10 * 2;
    }

    @Override
    public void spawnOnMap() {
        double posX = ((float)Math.random()*100)* Config.GAMEFRAMEWIDTH/100;
        double posY = ((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100;
        while (!GameController.getInstance().isPositionAccesible(posX- (double) getWidth() /2,posY- (double) getHeight() /2,getWidth(),getHeight(),false)) {
            posX = ((float)Math.random()*100)* Config.GAMEFRAMEWIDTH/100;
            posY = ((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100;
            System.out.println("Veggie cannot be spawn here. Find new pos...");
        }
        setX(posX);
        setY(posY);
        setCollected(false);
    }

    @Override
    public void collected() {
        if(this.getHp()>0) return;
        Player player = GameController.getInstance().getPlayer();
        player.setMoney(player.getMoney()+this.getPrice());
        // this will automatically be deleted by GameController
    }

    @Override
    public boolean isCollected() { return this.isCollected; }

    @Override
    public void weatherEffected() {
        Config.Weather weather = GameController.getInstance().getClock().getWeather();
        if( weather == Config.Weather.SUNNY ) {
            this.setGrowthRate(MAXGROWTHRATE * (float) 0.5);
            this.setWaterDroppingRate(MAXWATERDROPPINGRATE * (float) 1.0);
        } else if( weather == Config.Weather.SNOWY ) {
            this.setGrowthRate(MAXGROWTHRATE * (float) 0.2);
            this.setWaterDroppingRate(MAXWATERDROPPINGRATE * (float) 0.3);
        } else if( weather == Config.Weather.RAINY) {
            this.setGrowthRate(MAXGROWTHRATE * (float) 0.7);
            this.setWaterDroppingRate(MAXWATERDROPPINGRATE * (float) 0.0);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(this instanceof Bean){ gc.setFill(Color.GREEN); }
        if(this instanceof Cucumber){ gc.setFill(Color.LIGHTGREEN); }
        if(this instanceof Rice){ gc.setFill(Color.DARKGREEN); }

        gc.fillArc(getX() - getWidth(), getY() - getHeight(), getWidth(), getHeight(), 0, 360, ArcType.OPEN);

        // Calculate the width of the progress bar
        double HPPercentage = (double) getHp() / getMAXHP(); // Get HP percentage
        double HPBarWidth = 20 * HPPercentage; // Calculate progress bar width

        // Draw the progress bar
        double HPBarX = getX() - 20; // Start of progress bar
        double HPBarY = getY() + 12; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(HPBarX, HPBarY, 20, 5);
        gc.setFill(Color.ORANGERED);
        gc.fillRect(HPBarX, HPBarY, HPBarWidth, 5);


        // Calculate the width of the water bar
        double waterPercentage = (double) getWaterPoint() / getMAXWATER(); // Get HP percentage
        double waterBarWidth = 20 * waterPercentage; // Calculate progress bar width

        // Draw the progress bar
        double waterBarX = getX() - 20; // Start of progress bar
        double waterBarY = getY() + 20; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(waterBarX, waterBarY, 20, 5);
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(waterBarX, waterBarY, waterBarWidth, 5);
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public float getGrowthRate() { return growthRate; }

    public void setGrowthRate(float growthRate) { this.growthRate = growthRate; }

    public void setCollected(boolean collected) { isCollected = collected; }

    public int getHp() { return Hp; }

    public void setHp(int hp) { Hp = hp; }

    public float getWaterPoint() { return waterPoint; }

    public void setWaterPoint(float waterPoint) { this.waterPoint = waterPoint; }

    public float getGrowthPoint() { return growthPoint; }

    public void setGrowthPoint(float growthPoint) { this.growthPoint = growthPoint; }

    public float getWaterDroppingRate() { return waterDroppingRate; }

    public void setWaterDroppingRate(float waterDroppingRate) { this.waterDroppingRate = waterDroppingRate; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public int getMAXHP() {
        return MAXHP;
    }
    public float getMAXWATER() {
        return MAXWATER;
    }
}
