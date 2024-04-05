package Items.Veggies;

import Games.Config;
import Items.Interfaces.Collectable;
import Items.Interfaces.WeatherEffectable;

public abstract class BaseVeggies implements WeatherEffectable, Collectable {

    private int positionX;
    private int positionY;
    private float growthRate;
    boolean isCollected;
    private final int MAXGROWTHRATE;

    public BaseVeggies(){

    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public void setPositionX(int positionX) {
        this.positionX = Math.max(0,Math.min(positionX, Config.gameFrameWidth));
    }

    @Override
    public void setPositionY(int positionY) {
        this.positionY = Math.max(0,Math.min(positionY, Config.gameFrameHeight));
    }

    @Override
    public void spawnOnMap() {
        setPositionX((int) ((float)Math.random()*100)*Config.gameFrameWidth/100);
        setPositionY((int) ((float)Math.random()*100)*Config.gameFrameHeight/100);
        this.isCollected = false;
    }

    @Override
    public void collected() {

    }

    @Override
    public boolean isCollected() {
        return this.isCollected;
    }

    @Override
    public void weatherEffected() {

    }
}
