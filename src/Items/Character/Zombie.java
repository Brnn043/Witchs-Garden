package Items.Character;

import Games.Config;
import Games.GameController;
import Items.Veggies.BaseVeggies;

import java.util.ArrayList;

public class Zombie extends BaseCharacter{
    private int Hp;
    private BaseVeggies targetVeggie;
    public Zombie() {
        super((float) (Math.random()*100*Config.gameFrameHeight/100)
                , (float) (Math.random()*100*Config.gameFrameHeight/100)
                , (int) ((float) (Math.random())*Config.ZOMBIEMAXSPEEDRATE)
                , (int) ((float) (Math.random())*Config.ZOMBIEMAXDAMAGERANGE)
                , (int) ((float) (Math.random())*Config.ZOMBIEMAXDAMAGE));
        setHp(Math.max(5,(int) ((float)Math.random()*25)));
        ArrayList<BaseVeggies> veggiesList= GameController.getInstance().getVeggiesList();
        setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
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
                veggie.setHp(veggie.getHp()-this.getDamage());
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
