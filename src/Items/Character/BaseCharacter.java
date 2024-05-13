package Items.Character;

import GUISharedObject.CollidableEntity;
import Games.Config;
import Games.GameController;
import Items.Interfaces.Attackable;
import Items.Interfaces.Walkable;
import Items.Interfaces.WeatherEffectable;
import Games.Config.*;

public abstract class BaseCharacter extends CollidableEntity implements Walkable, Attackable, WeatherEffectable {
    private float speedRate;
    private final float maxSpeedRate;
    private final int attackRange;
    private float damage;
    private int attackCoolDown;
    public BaseCharacter(double positionX, double positionY, int maxSpeedRate, int attackRange, float damage) {
        super(positionX, positionY, 0, 0);
        this.maxSpeedRate = Math.max(2, maxSpeedRate);
        this.attackRange = Math.max(2, attackRange);
        this.damage = Math.max(2, damage);
    }

    public BaseCharacter(int maxSpeedRate, int attackRange, float damage) {
        super();
        this.maxSpeedRate = Math.max(2, maxSpeedRate);
        this.attackRange = Math.max(2, attackRange);
        this.damage = Math.max(2, damage);
    }

    @Override
    public void weatherEffected() {
        Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if(weatherNow == Weather.SUNNY){
            setSpeedRate((float) 0.75 * maxSpeedRate);
        } else if (weatherNow == Weather.RAINY) {
            setSpeedRate((float) 0.9 * maxSpeedRate);
        }else if (weatherNow == Weather.SNOWY){
            setSpeedRate((float) 0.5 * maxSpeedRate);
        }
    }

    @Override
    public void setX(double x) { this.x = Math.max(getWidth()/2,Math.min(x, Config.GAMESCREENWIDTH - getWidth()/2)); }

    @Override
    public void setY(double y) {
        this.y = Math.max(getHeight()/2,Math.min(y,Config.GAMESCREENHEIGHT - getHeight()/2));
    }

    public int getAttackCoolDown() { return attackCoolDown; }

    public void setAttackCoolDown(int attackCoolDown) {
        this.attackCoolDown = Math.max(0, attackCoolDown);
    }

    @Override
    public float getSpeedRate() { return speedRate; }

    @Override
    public void setSpeedRate(float speedRate) {
        this.speedRate = Math.max(0,Math.min(speedRate, maxSpeedRate));
    }

    @Override
    public int getAttackRange() {
        return attackRange;
    }

    @Override
    public void walk() {
        return;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = Math.max(0, damage);
    }
}
