package Item.Character;

import GUISharedObject.CollidableEntity;
import Game.Config;
import Game.GameController;
import Item.Interface.Attackable;
import Item.Interface.Walkable;
import Item.Interface.WeatherEffectable;
import Game.Config.*;

// this is the base character of the game which can walk, attack ,and is affected by weather
public abstract class BaseCharacter extends CollidableEntity implements Walkable, Attackable, WeatherEffectable {
    private float speedRate;
    private final float MAXSPEEDRATE;
    private int attackRange;
    private float damage;
    private int attackCoolDown;

    public BaseCharacter(double positionX, double positionY, int maxSpeedRate, int attackRange, float damage) {
        super(positionX, positionY, 0, 0);
        this.MAXSPEEDRATE = Math.max(2, maxSpeedRate);
        this.attackRange = Math.max(2, attackRange);
        this.damage = Math.max(2, damage);
    }

    public BaseCharacter(int maxSpeedRate, int attackRange, float damage) {
        super();
        this.MAXSPEEDRATE = Math.max(2, maxSpeedRate);
        this.attackRange = Math.max(2, attackRange);
        this.damage = Math.max(2, damage);
    }

    // the speed rate of character is based on weather
    @Override
    public void weatherEffected() {
        Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if (weatherNow == Weather.SUNNY){
            setSpeedRate((float) 0.75 * MAXSPEEDRATE);
        } else if (weatherNow == Weather.RAINY) {
            setSpeedRate((float) 0.9 * MAXSPEEDRATE);
        } else if (weatherNow == Weather.SNOWY){
            setSpeedRate((float) 0.5 * MAXSPEEDRATE);
        }
    }

    @Override
    public void setX(double x) {
        this.x = Math.max(getWidth() / 2, Math.min(x, Config.GAMESCREENWIDTH - getWidth() / 2)); }

    @Override
    public void setY(double y) {
        this.y = Math.max(getHeight() / 2, Math.min(y, Config.GAMESCREENHEIGHT - getHeight() / 2));
    }

    public int getAttackCoolDown() { return attackCoolDown; }

    public void setAttackCoolDown(int attackCoolDown) {
        this.attackCoolDown = Math.max(0, attackCoolDown);
    }

    @Override
    public float getSpeedRate() { return speedRate; }

    @Override
    public void setSpeedRate(float speedRate) {
        this.speedRate = Math.max(0,Math.min(speedRate, MAXSPEEDRATE));
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
