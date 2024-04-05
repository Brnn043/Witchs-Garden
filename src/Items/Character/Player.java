package Items.Character;

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
        // player attack zombie
        if(getAttackCooldown()>0){
            return;
        }
        if(o instanceof Zombie){
            Zombie character = (Zombie) o;
            if(Math.pow(this.getPositionX()-character.getPositionX(),2)
                    + Math.pow(this.getPositionY()-character.getPositionY(),2)
                    <= Math.pow(this.getAttackRange(),2)){
                character.setHp(character.getHp()-this.damage);
            }else{
                System.out.println("Can't atttack, out of attack range.");
            }
            setAttackCooldown(3);
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
