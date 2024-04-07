package Items.Character;

import Games.Config;
import Games.GameController;
import Items.Interfaces.Attackable;
import Items.Interfaces.Collectable;
import Items.Interfaces.Walkable;
import Items.Interfaces.WeatherEffectable;
import Games.Config.*;
import Items.Veggies.BaseVeggies;

import javax.print.attribute.standard.JobKOctets;

public abstract class BaseCharacter implements Walkable, Attackable, WeatherEffectable {
    private int positionX;
    private int positionY;
    private float speedRate;
    private final int MAXSPEEDRATE;
    private int attackRange;
    private int damage;
    private int attackCooldown;

    protected BaseCharacter(int positionX, int positionY, int maxspeedrate, int attackRange, int damage) {
        this.MAXSPEEDRATE = Math.max(0,maxspeedrate);
        this.attackRange = Math.max(0,attackRange);
        this.damage = Math.max(0,damage);
        setPositionX(positionX);
        setPositionY(positionY);
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
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public float getSpeedRate() {
        return speedRate;
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
