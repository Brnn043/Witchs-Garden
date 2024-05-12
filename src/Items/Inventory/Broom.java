package Items.Inventory;

import GUISharedObject.Entity;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Interfaces.Collectable;
import Items.Interfaces.WeatherEffectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class Broom extends Entity implements Collectable, WeatherEffectable {
    private boolean isCollected;
    private int durability;
    private int attackRange;
    private float damage;
    private int width,height;

    public Broom() { // this constructor will randomly choose durability and attack range
        super();
        setDurability( (int) (Math.random() * (Config.BROOMMAXDURABILITY - Config.BROOMMINDURABILITY + 1) ) + Config.BROOMMINDURABILITY);
        setAttackRange( (int) (Math.random() * (Config.BROOMMAXATTACKRANGE - Config.BROOMMINATTACKRANGE + 1) ) + Config.BROOMMINATTACKRANGE);
        setDamage(Config.BROOMDAMAGEPERATTACK);
        width = 90;
        height = 45;
        spawnOnMap();
        this.z = this.getZ() + 300;
    }

    @Override
    public void weatherEffected() {
        Config.Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if(weatherNow == Config.Weather.SUNNY){
            setDamage( (float) ( 0.5 * Config.BROOMDAMAGEPERATTACK) );
        } else if (weatherNow == Config.Weather.RAINY) {
            setDamage( (float) ( 0.75 * Config.BROOMDAMAGEPERATTACK) );
        }else if (weatherNow == Config.Weather.SNOWY){
            setDamage( (float) ( 1.0 * Config.BROOMDAMAGEPERATTACK) );
        }
    }

    @Override
    public void collected() {
        if (! InputUtility.getKeyPressed(KeyCode.E)) {
           return;
        }
        Player player = GameController.getInstance().getPlayer();
        double disX = GameController.getInstance().getPlayer().getX() - this.getX();
        double disY = GameController.getInstance().getPlayer().getY() - this.getY();
        double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
        if( distance >= 60 ) {
            return;
        }
        if(player.getBroom() != null) {
            System.out.println("Player already have BROOM");
            return;
        }
        RenderableHolder.collectSound.play();
        player.setBroom(this);
        this.setCollected(true);
        RenderableHolder.getInstance().getEntities().remove(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.broomSprite, getX() - (double) getWidth() /2, getY() - (double) getHeight() /2,getWidth(),getHeight());
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public float getDamage() { return damage; }

    public void setDamage(float damage) { this.damage = damage; }

    public int getDurability() { return durability; }

    public void setDurability(int durability) {
        this.durability = Math.max(0,durability);
    }

    public int getAttackRange() { return attackRange; }

    public void setAttackRange(int attackRange) {
        this.attackRange = Math.max(0,attackRange);
    }

    @Override
    public void spawnOnMap() {
        double posX = ((float)Math.random()*100)* Config.GAMESCREENWIDTH/100;
        double posY = ((float)Math.random()*100)*Config.GAMESCREENHEIGHT/100;
        while (!GameController.getInstance().isPositionAccesible(posX, posY, getWidth(), getHeight(), false)) {
            posX = ((float)Math.random()*100)* Config.GAMESCREENWIDTH/100;
            posY = ((float)Math.random()*100)*Config.GAMESCREENHEIGHT/100;
            System.out.println("Broom cannot be spawn here. Find new pos...");
        }
        setX(posX);
        setY(posY);
        setCollected(false);
    }

    @Override
    public boolean isCollected() { return isCollected; }
    public void setCollected(boolean collected) { isCollected = collected; }

}
