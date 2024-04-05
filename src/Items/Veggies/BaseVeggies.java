package Items.Veggies;

import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Interfaces.Collectable;
import Items.Interfaces.WeatherEffectable;

public abstract class BaseVeggies implements WeatherEffectable, Collectable {

    private int positionX;
    private int positionY;
    private float growthRate;
    private boolean isCollected;
    private static final int MAXGROWTHRATE = 25;
    private int Hp;
    private float growthPoint; // float might probably be better than using int?
    private int waterPoint;
    private int waterDroppingRate;
    private int waterDroppingRateNow; // in case waterDroppingRate is affected by weather
    private static int MAXWATER;
    private int price;

    // haven't implement veggieHpBar yet


    public BaseVeggies(int hp,int maxWater,float growthRate,int waterDroppingRate,int price){
        this.setHp(hp);
        MAXWATER = maxWater;
        this.setGrowthRate(growthRate);
        this.setWaterDroppingRate(waterDroppingRate);
        this.setWaterDroppingRateNow(waterDroppingRate);
        this.setPrice(price);
    }

    @Override
    public int getPositionX() { return positionX; }

    @Override
    public int getPositionY() { return positionY; }

    @Override
    public void setPositionX(int positionX) { this.positionX = Math.max(0,Math.min(positionX, Config.gameFrameWidth)); }

    @Override
    public void setPositionY(int positionY) { this.positionY = Math.max(0,Math.min(positionY, Config.gameFrameHeight)); }

    @Override
    public void spawnOnMap() {
        setPositionX((int) ((float)Math.random()*100)*Config.gameFrameWidth/100);
        setPositionY((int) ((float)Math.random()*100)*Config.gameFrameHeight/100);
        this.setCollected(false);
    }

    @Override
    public void collected() {
        if(this.getHp()>0) return;
        Player player = GameController.getInstance().getPlayer();
        player.setMoney(player.getMoney()+this.getPrice());
        // this will automatically be deleted by GameController?
    }

    @Override
    public boolean isCollected() { return this.isCollected; }

    @Override
    public void weatherEffected() {
        // haven't implement

    }

    public float getGrowthRate() { return growthRate; }

    public void setGrowthRate(float growthRate) { this.growthRate = growthRate; }

    public void setCollected(boolean collected) { isCollected = collected; }

    public int getHp() { return Hp; }

    public void setHp(int hp) { Hp = hp; }

    public int getWaterPoint() { return waterPoint; }

    public void setWaterPoint(int waterPoint) { this.waterPoint = waterPoint; }

    public float getGrowthPoint() { return growthPoint; }

    public void setGrowthPoint(float growthPoint) { this.growthPoint = growthPoint; }

    public int getWaterDroppingRate() { return waterDroppingRate; }

    public void setWaterDroppingRate(int waterDroppingRate) { this.waterDroppingRate = waterDroppingRate; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public int getWaterDroppingRateNow() { return waterDroppingRateNow; }

    public void setWaterDroppingRateNow(int waterDroppingRateNow) { this.waterDroppingRateNow = waterDroppingRateNow; }

}
