package Items.Inventory;

import Games.Config;
import Games.GameController;
import Items.Character.Player;
import Items.Character.Zombie;
import Items.Interfaces.Collectable;

public class Stick implements Collectable {

    private int positionX;
    private int positionY;
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
        this.setDurabilty((int) (Math.random() * (maxDurability - minDurability + 1)) + minDurability);
        this.setAttackRange((int) (Math.random() * (maxAttackRange - minAttackRange + 1)) + minAttackRange);
        this.setCooldown(cooldownTime);
        this.setDamage(3);
    }

    public Stick(int durability,int attackRange) {
        this.setDurabilty(durability);
        this.setAttackRange(attackRange);
        this.setCooldown(cooldownTime);
        this.setDamage(3);
    }

    public boolean attack() {
        if(this.getCooldown() > 0) return false;
        Player player = GameController.getInstance().getPlayer();
        for(Zombie zombie : GameController.getInstance().getZombieList()) {
            double disX = player.getPositionX() - zombie.getPositionX();
            double disY = player.getPositionY() - zombie.getPositionY();
            double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
            if( distance <= this.getAttackRange() ) {
                zombie.setHp( zombie.getHp() - this.getDamage() );
                this.setDurabilty(this.getDurability() - durabilityPerAttack);
                this.setCooldown(cooldownTime);
            }
        }
    }

    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }

    public int getCooldown() { return cooldown; }

    public void setCooldown(int cooldown) { this.cooldown = cooldown; }

    public int getDurability() { return durability; }

    public void setDurabilty(int durabilty) {
        this.durability = Math.max(0,durabilty);
    }

    public int getAttackRange() { return attackRange; }

    public void setAttackRange(int attackRange) {
        this.attackRange = Math.max(0,attackRange);
    }

    @Override
    public void setPositionX(int positionX) { this.positionX = positionX; }

    @Override
    public void setPositionY(int positionY) { this.positionY = positionY; }

    @Override
    public void spawnOnMap() {
        setPositionX((int) ((float)Math.random()*100)* Config.gameFrameWidth/100);
        setPositionY((int) ((float)Math.random()*100)*Config.gameFrameHeight/100);
        this.setCollected(false);
    }

    @Override
    public void collected() {
        // haven't implement
    }

    @Override
    public boolean isCollected() { return isCollected; }

    @Override
    public int getPositionX() { return this.positionX; }

    @Override
    public int getPositionY() { return this.positionY; }

    public void setCollected(boolean collected) { isCollected = collected; }
}
