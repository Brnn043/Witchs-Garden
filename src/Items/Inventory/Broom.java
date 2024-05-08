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

public class Broom extends Entity implements Collectable {
    private boolean isCollected;
    private int durability;
    private int attackRange;
    private int damage;

    public Broom() { // this constructor will randomly choose durability and attack range
        super();
        this.setDurability((int) (Math.random() * (Config.BROOMMAXDURABILITY - Config.BROOMMINDURABILITY + 1)) + Config.BROOMMINDURABILITY);
        this.setAttackRange((int) (Math.random() * (Config.BROOMMAXATTACKRANGE - Config.BROOMMINATTACKRANGE + 1)) + Config.BROOMMINATTACKRANGE);
        this.setDamage(Config.BROOMDAMAGEPERATTACK);
        spawnOnMap();
    }

    public Broom(int durability, int attackRange) {
        super();
        this.setDurability(durability);
        this.setAttackRange(attackRange);
        this.setDamage(Config.BROOMDAMAGEPERATTACK);
        spawnOnMap();
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
        player.setBroom(this);
        this.setCollected(true);
        RenderableHolder.getInstance().getEntities().remove(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.broomSprite, getX() - 45, getY() - 20,70,35);
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
    public void spawnOnMap() {
        setX(((float)Math.random()*100)* Config.GAMEFRAMEWIDTH/100);
        setY(((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100);
        this.setCollected(false);
    }

    @Override
    public boolean isCollected() { return isCollected; }
    public void setCollected(boolean collected) { isCollected = collected; }

}
