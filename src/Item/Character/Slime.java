package Item.Character;

import Game.Config;
import Game.GameController;
import Item.Veggie.BaseVeggie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

// this is base class of slime which is extended from BaseCharacter
public abstract class Slime extends BaseCharacter{
    private float Hp;
    private float maxHp;
    private BaseVeggie targetVeggie;
    private float maxDamage;

    public Slime(int speedRate, float maxHp, float maxDamage) {
        super(speedRate
                ,(int) ( (float) Math.max(Config.SLIMEMAXSPEEDRATE, (Math.random()) * Config.SLIMEMAXDAMAGERANGE) )
                , maxDamage);
        setMaxHp(maxHp);
        setHp(maxHp);
        setMaxDamage(maxDamage);

        ArrayList<BaseVeggie> veggiesList= GameController.getInstance().getVeggiesList();
        setTargetVeggie(veggiesList.get((int) (Math.random() * veggiesList.size())));

        setWidth(Config.SLIMEWIDTH);
        setHeight(Config.SLIMEHEIGHT);

        spawnOnMap();

        this.z = 400;
    }

    private void spawnOnMap() {
        double posX = Config.SPAWNLEFTBOUND + Math.random() * (Config.SPAWNRIGHTBOUND - Config.SPAWNLEFTBOUND);
        double posY = Config.SPAWNTOPBOUND + Math.random() * (Config.SPAWNBOTTOMBOUND - Config.SPAWNTOPBOUND);

        // check if this can spawn on that position ,
        // otherwise it will keep spawning until find the proper position
        while (!GameController.getInstance().isPositionAccesible(posX - getWidth() / 2,
                posY - getHeight() / 2, getWidth(), getHeight(),false)){
            posX = Config.SPAWNLEFTBOUND + Math.random() * (Config.SPAWNRIGHTBOUND - Config.SPAWNLEFTBOUND);
            posY = Config.SPAWNTOPBOUND + Math.random() * (Config.SPAWNBOTTOMBOUND - Config.SPAWNTOPBOUND);
            System.out.println("Slime cannot be spawn here. Find new pos...");
        }
        setX(posX);
        setY(posY);
    }

    // the speed rate and damage of slime is based on weather
    @Override
    public void weatherEffected() {
        super.weatherEffected();
        Config.Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if(weatherNow == Config.Weather.SUNNY){
            setDamage((float) 0.6 * maxDamage);
        } else if (weatherNow == Config.Weather.RAINY) {
            setDamage((float) 0.7 * maxDamage);
        }else if (weatherNow == Config.Weather.SNOWY){
            setDamage((float) 0.5 * maxDamage);
        }
    }

    @Override
    public void attack() {
        if(getAttackCoolDown()>0){
            return;
        }

        // check if target veggie still in game
        ArrayList<BaseVeggie> veggiesList= GameController.getInstance().getVeggiesList();
        if(!veggiesList.contains(this.getTargetVeggie())){
            this.setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
            System.out.println("slime find new target");
        }

        // calculate distance from target veggie
        double disX = this.getTargetVeggie().getX() - this.getX();
        double disY = this.getTargetVeggie().getY() - this.getY();
        int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));

        // attack veggie
        if( (distance - this.getAttackRange()) <= Config.SLIMEWALKSTEP ) {
            this.getTargetVeggie().setHp(this.getTargetVeggie().getHp()-this.getDamage());
            setAttackCoolDown(Config.SLIMEATTACKCOOLDOWN);
            System.out.println("slime ATTACK!!!");
        }

    }

    public float getMaxHp() { return maxHp; }

    public void setMaxHp(float maxHp) { this.maxHp = maxHp; }

    public float getMaxDamage() { return maxDamage; }

    public void setMaxDamage(float maxDamage) { this.maxDamage = maxDamage; }

    public float getHp() {
        return Hp;
    }

    public void setHp(float hp) { this.Hp = Math.max(0, Math.min(hp, maxHp)); }

    public BaseVeggie getTargetVeggie() {
        return targetVeggie;
    }

    public void setTargetVeggie(BaseVeggie targetVeggie) {
        this.targetVeggie = targetVeggie;
    }

    @Override
    public void walk() {

        double disX = this.getX() - this.getTargetVeggie().getX();
        double disY = this.getY() - this.getTargetVeggie().getY();
        int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));

        if( distance - this.getAttackRange() > Config.SLIMEWALKSTEP ){
            double posX = (float) (this.getX() - (Math.signum(disX))*(Config.SLIMEWALKSTEP * this.getSpeedRate() * 0.2));
            double posY = (float) (this.getY() - (Math.signum(disY))*(Config.SLIMEWALKSTEP * this.getSpeedRate() * 0.2));

            if (!GameController.getInstance().isPositionAccesible(posX ,getY() ,getWidth(), getHeight(),false)) posX=getX();
            if (!GameController.getInstance().isPositionAccesible(getX() ,posY ,getWidth(),getHeight(),false)) posY=getY();

            this.setX(posX);
            this.setY(posY);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        // Calculate the width of the progress bar
        double HPPercentage = (double) getHp() / getMaxHp(); // Get HP percentage
        double HPBarWidth = 20 * HPPercentage; // Calculate progress bar width

        // Draw the progress bar
        double HPBarX = getX() - 10; // Start of progress bar
        double HPBarY = getY() + getHeight() / 2; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(HPBarX, HPBarY, 20, 5);
        gc.setFill(Color.ORANGERED);
        gc.fillRect(HPBarX, HPBarY, HPBarWidth, 5);
    }
}
