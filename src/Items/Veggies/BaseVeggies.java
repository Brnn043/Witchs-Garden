package Items.Veggies;

import GUISharedObject.Entity;
import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Interfaces.Collectable;
import Items.Interfaces.WeatherEffectable;

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

    // haven't implemented veggieHpBar yet


    public BaseVeggies(int hp,float maxWater,float growthRate,float waterDroppingRate,int price){
        super();
        this.MAXHP = hp;
        this.setHp(this.MAXHP);
        this.MAXWATER = maxWater;
        this.setWaterPoint(MAXWATER);
        this.MAXGROWTHRATE = growthRate;
        this.setGrowthRate(MAXGROWTHRATE);
        this.MAXWATERDROPPINGRATE = waterDroppingRate;
        this.setWaterDroppingRate(MAXWATERDROPPINGRATE);
        this.setPrice(price);
        spawnOnMap();
    }

    @Override
    public void spawnOnMap() {
        setX(((float)Math.random()*100)*Config.GAMEFRAMEWIDTH/100);
        setY(((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100);
        this.setCollected(false);
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
