package Items.Character;

import Games.Config;
import Games.GameController;
import Items.Inventory.Stick;

public class Player extends BaseCharacter{
    private Stick stick;
    private int money;

    public Player(int positionX, int positionY, int maxspeedrate, int attackRange, int damage) {
        super(positionX, positionY, maxspeedrate, attackRange, damage);
        setStick(null);
        setMoney(0);
    }

    @Override
    public void attack(Object o) {
        // player attack slime
        if(getAttackCooldown()>0){
            return;
        }

        for(Slime slime : GameController.getInstance().getslimeList()) {
            double disX = GameController.getInstance().getPlayer().getPositionX() - slime.getPositionX();
            double disY = GameController.getInstance().getPlayer().getPositionY() - slime.getPositionY();
            double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
            if( distance <= stick.getAttackRange() ) {
                slime.setHp( slime.getHp() - stick.getDamage() );
                stick.setDurability(stick.getDurability() - Config.STICKDURABILITYPERATTACK);
                this.setAttackCooldown(Config.PLAYERCOOLDOWNTIME);
            }
        }
    }

    @Override
    public void walk() {
        // WASD implement after get GUI
        this.setPositionX(this.getPositionX()+(int)this.getSpeedRate());
    }

    public Stick getStick() {
        return stick;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = Math.max(0,money);
    }

}
