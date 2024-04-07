package Items.Inventory;

import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Character.Zombie;
import Items.Interfaces.Collectable;

public class Stick implements Collectable {

    private float positionX;
    private float positionY;
    private boolean isCollected;
    private int durability;
    private int attackRange;
    private int damage;
    private int cooldown;
    private final int cooldownTime = 1;
    private final int maxDurability = 10;
    private final int minDurability = 5;
    private final int maxAttackRange = 10;
    private final int minAttackRange = 2;
    private final int durabilityPerAttack = 1;

    public Stick() {
        this.setDurability((int) (Math.random() * (maxDurability - minDurability + 1)) + minDurability);
        this.setAttackRange((int) (Math.random() * (maxAttackRange - minAttackRange + 1)) + minAttackRange);
        this.setCooldown(cooldownTime);
        this.setDamage(3);
    }

    public Stick(int durability,int attackRange) {
        this.setDurability(durability);
        this.setAttackRange(attackRange);
        this.setCooldown(cooldownTime);
        this.setDamage(3);
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

    public int getCooldown() { return cooldown; }

    public void setCooldown(int cooldown) { this.cooldown = cooldown; }

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
        setPositionX((int) ((float)Math.random()*100)* Config.gameFrameWidth/100);
        setPositionY((int) ((float)Math.random()*100)*Config.gameFrameHeight/100);
        this.setCollected(false);
    }

    @Override
    public boolean isCollected() { return isCollected; }

    @Override
    public float getPositionX() { return this.positionX; }

    @Override
    public float getPositionY() { return this.positionY; }

    public void setCollected(boolean collected) { isCollected = collected; }

    public int getDurabilityPerAttack() {
        return durabilityPerAttack;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }
}
