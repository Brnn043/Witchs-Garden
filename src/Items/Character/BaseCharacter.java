package Items.Character;

import GUISharedObject.Entity;
import Games.Config;
import Games.GameController;
import Items.Interfaces.Attackable;
import Items.Interfaces.Walkable;
import Items.Interfaces.WeatherEffectable;
import Games.Config.*;

public abstract class BaseCharacter extends Entity implements Walkable, Attackable, WeatherEffectable {
    private float speedRate;
    private final int MAXSPEEDRATE;
    private int attackRange;
    private int damage;
    private int attackCooldown;

    protected BaseCharacter(float positionX, float positionY, int maxspeedrate, int attackRange, int damage) {
        super(positionX,positionY);
        this.MAXSPEEDRATE = Math.max(2,maxspeedrate);
        this.attackRange = Math.max(2,attackRange);
        this.damage = Math.max(2,damage);
    }

    @Override
    public void weatherEffected() {
        Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if(weatherNow == Weather.SUNNY){
            setSpeedRate((float) 0.5*MAXSPEEDRATE);
        } else if (weatherNow == Weather.RAINY) {
            setSpeedRate((float) 0.7*MAXSPEEDRATE);
        }else if (weatherNow == Weather.SNOWY){
            setSpeedRate((float) 1*MAXSPEEDRATE);
        }
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = Math.max(0,attackCooldown);
    }

    @Override
    public float getSpeedRate() {
        return speedRate;
    }

    @Override
    public void setSpeedRate(float speedRate) {
        this.speedRate = Math.max(0,Math.min(speedRate, MAXSPEEDRATE));
    }

    @Override
    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public abstract void attack();

    @Override
    public void walk() {
        return;
    }

    public int getMAXSPEEDRATE() {
        return MAXSPEEDRATE;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
