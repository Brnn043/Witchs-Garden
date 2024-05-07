package Items.Inventory;

import GUISharedObject.Entity;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Interfaces.Collectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Stick extends Entity implements Collectable {

    private float positionX;
    private float positionY;
    private boolean isCollected;
    private int durability;
    private int attackRange;
    private int damage;

    public Stick() { // this constructor will randomly choose durability and attack range
        this.setDurability((int) (Math.random() * (Config.STICKMAXDURABILITY - Config.STICKMINDURABILITY + 1)) + Config.STICKMINDURABILITY);
        this.setAttackRange((int) (Math.random() * (Config.STICKMAXATTACKRANGE - Config.STICKMINATTACKRANGE + 1)) + Config.STICKMINATTACKRANGE);
        this.setDamage(Config.STICKDAMAGEPERATTACK);
        this.setPositionX((float)Math.random()*100*Config.GAMEFRAMEWIDTH/100);
        this.setPositionY((float)Math.random()*100*Config.GAMEFRAMEHEIGHT/100);
    }

    public Stick(int durability,int attackRange) {
        this.setDurability(durability);
        this.setAttackRange(attackRange);
        this.setDamage(Config.STICKDAMAGEPERATTACK);
    }

    @Override
    public void collected() {
        if (! InputUtility.getKeyPressed(KeyCode.E)) {
           return;
        }
        Player player = GameController.getInstance().getPlayer();
        double disX = GameController.getInstance().getPlayer().getPositionX() - this.getPositionX();
        double disY = GameController.getInstance().getPlayer().getPositionY() - this.getPositionY();
        double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
        if( distance >= 60 ) {
            return;
        }
        if(player.getStick() != null) {
            System.out.println("Player already have stick");
            return;
        }
        player.setStick(this);
        this.setCollected(true);
        RenderableHolder.getInstance().getEntities().remove(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.stickSprite, getPositionX() - 45, getPositionY() - 20,30,45);
    }

    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }

    public int getDurability() { return durability; }

    public void setDurability(int durability) {
        this.durability = Math.max(0,durability);
    }

    public int getAttackRange() { return attackRange; }

    public void setAttackRange(int attackRange) {
        this.attackRange = Math.max(0,attackRange);
    }

    @Override
    public void setPositionX(float positionX) { this.positionX = positionX; }

    @Override
    public void setPositionY(float positionY) { this.positionY = positionY; }

    @Override
    public void spawnOnMap() {
        setPositionX(((float)Math.random()*100)* Config.GAMEFRAMEWIDTH/100);
        setPositionY(((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100);
        this.setCollected(false);
    }

    @Override
    public boolean isCollected() { return isCollected; }

    @Override
    public float getPositionX() { return this.positionX; }

    @Override
    public float getPositionY() { return this.positionY; }

    public void setCollected(boolean collected) { isCollected = collected; }

}
