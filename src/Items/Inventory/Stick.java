package Items.Inventory;

import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Interfaces.Collectable;

public class Stick implements Collectable {

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
    }

    public Stick(int durability,int attackRange) {
        this.setDurability(durability);
        this.setAttackRange(attackRange);
        this.setDamage(Config.STICKDAMAGEPERATTACK);
    }

    @Override
    public void collected() {
        Player player = GameController.getInstance().getPlayer();
        if(player.getStick() != null) {
            System.out.println("Player already have stick");
            return;
        }
        player.setStick(this);
        this.setCollected(true);
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
        setPositionX(((float)Math.random()*100)* Config.gameFrameWidth/100);
        setPositionY(((float)Math.random()*100)*Config.gameFrameHeight/100);
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
