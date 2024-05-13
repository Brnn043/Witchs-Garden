package Item.Inventory;

import GUISharedObject.Entity;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Game.Config;
import Game.GameController;
import Item.Character.Player;
import Item.Interface.Collectable;
import Item.Interface.WeatherEffectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

// this is broom which player use to attack slime
public class Broom extends Entity implements Collectable, WeatherEffectable {
    private boolean isCollected;
    private float durability;
    private int attackRange;
    private float damage;
    private double width, height;

    public Broom() {
        super();

        // randomly choose durability and attack range
        setDurability( (int) (Math.random() * (Config.BROOMMAXDURABILITY - Config.BROOMMINDURABILITY + 1) ) + Config.BROOMMINDURABILITY);
        setAttackRange( (int) (Math.random() * (Config.BROOMMAXATTACKRANGE - Config.BROOMMINATTACKRANGE + 1) ) + Config.BROOMMINATTACKRANGE);
        setDamage(Config.BROOMDAMAGEPERATTACK);

        width = Config.BROOMWIDTH;
        height = Config.BROOMHEIGHT;

        spawnOnMap();
        this.z = 300;
    }

    @Override
    public void weatherEffected() {
        Config.Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if (weatherNow == Config.Weather.SUNNY){
            setDamage( (float) ( 0.5 * Config.BROOMDAMAGEPERATTACK) );
        } else if (weatherNow == Config.Weather.RAINY) {
            setDamage( (float) ( 0.75 * Config.BROOMDAMAGEPERATTACK) );
        } else if (weatherNow == Config.Weather.SNOWY){
            setDamage(Config.BROOMDAMAGEPERATTACK);
        }
    }

    @Override
    public void collected() {
        // check condition
        if (!InputUtility.getKeyPressed(KeyCode.E)) return;

        // check distance
        Player player = GameController.getInstance().getPlayer();
        double disX = GameController.getInstance().getPlayer().getX() - this.getX();
        double disY = GameController.getInstance().getPlayer().getY() - this.getY();
        double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
        if (distance >= Config.PLAYERCOLLECTRANGE) return;

        if (player.getBroom() != null) {
            System.out.println("Player already have BROOM");
            return;
        }

        RenderableHolder.collectSound.play();
        player.setBroom(this);
        this.setCollected(true);
        RenderableHolder.getInstance().getEntities().remove(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.broomSprite, getX() - getWidth() / 2,
                getY() - getHeight() / 2, getWidth(), getHeight());
    }

    @Override
    public void spawnOnMap() {
        double posX = Config.SPAWNLEFTBOUND + Math.random() * (Config.SPAWNRIGHTBOUND - Config.SPAWNLEFTBOUND);
        double posY = Config.SPAWNTOPBOUND + Math.random() * (Config.SPAWNBOTTOMBOUND - Config.SPAWNTOPBOUND);

        // check if this can spawn on that position ,
        // otherwise it will keep spawning until find the proper position
        while (!GameController.getInstance().isPositionAccesible(posX, posY,
                getWidth(), getHeight(), false)) {
            posX = Config.SPAWNLEFTBOUND + Math.random() * (Config.SPAWNRIGHTBOUND - Config.SPAWNLEFTBOUND);
            posY = Config.SPAWNTOPBOUND + Math.random() * (Config.SPAWNBOTTOMBOUND - Config.SPAWNTOPBOUND);
            System.out.println("Broom cannot be spawn here. Find new pos...");
        }
        setX(posX);
        setY(posY);
        setCollected(false);
    }
    @Override
    public void setX(double x) {
        this.x = Math.max(getWidth() / 2, Math.min(x, Config.GAMESCREENWIDTH - getWidth() /2));
    }
    @Override
    public void setY(double y) {
        this.y = Math.max(getHeight() / 2, Math.min(y, Config.GAMESCREENHEIGHT - getHeight() /2));
    }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public float getDamage() { return damage; }

    public void setDamage(float damage) { this.damage = damage; }

    public float getDurability() { return durability; }

    public void setDurability(float durability) {
        this.durability = Math.max(0, durability);
    }

    public int getAttackRange() { return attackRange; }

    public void setAttackRange(int attackRange) {
        this.attackRange = Math.max(0, attackRange);
    }

    @Override
    public boolean isCollected() { return isCollected; }

    public void setCollected(boolean collected) { isCollected = collected; }

}
