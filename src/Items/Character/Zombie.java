package Items.Character;

import Games.GameController;
import Items.Veggies.BaseVeggies;

public class Zombie extends BaseCharacter{
    private int Hp;

    private BaseVeggies targetVeggie;
    public Zombie(int positionX, int positionY, int maxspeedrate, int attackRange, int damage) {
        super(positionX, positionY, maxspeedrate, attackRange, damage);
        setHp(((int) ((float)Math.random()*25)));
//        setTargetVeggie(GameController.getInstance().getVeggies()); : implement later

    }

    @Override
    public void attack(Object o) {
        if(getAttackCooldown()>0){
            return;
        }
        // zombie attack veggie
        if(o instanceof BaseVeggies){
            BaseVeggies veggie = (BaseVeggies) o;
            if(Math.pow(this.getPositionX()-veggie.getPositionX(),2)
                    + Math.pow(this.getPositionY()-veggie.getPositionY(),2)
                    <= Math.pow(this.getAttackRange(),2)){
                veggie.setHp(veggie.getHp()-this.damage);
            }else{
                System.out.println("Can't attack, out of attack range.");
            }
        }
        setAttackCooldown(5);
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        this.Hp = Math.max(0, hp);
    }


    public BaseVeggies getTargetVeggie() {
        return targetVeggie;
    }

    public void setTargetVeggie(BaseVeggies targetVeggie) {
        this.targetVeggie = targetVeggie;
    }
}
