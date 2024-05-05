package Items.Character;

import Games.Config;
import Games.GameController;
import Items.Veggies.BaseVeggies;

import java.util.ArrayList;

public class Slime extends BaseCharacter{
    private int Hp;
    private BaseVeggies targetVeggie;
    public Slime() {
        super(((float)Math.random()*100)*Config.gameFrameWidth/100
                , ((float)Math.random()*100)*Config.gameFrameHeight/100
                , (int) ((float) (Math.random())*Config.SLIMEMAXSPEEDRATE)
                , (int) ((float) (Math.random())*Config.SLIMEMAXDAMAGERANGE)
                , (int) ((float) (Math.random())*Config.SLIMEMAXDAMAGE));
        setHp(Math.max(5,(int) ((float)Math.random()*25)));
        ArrayList<BaseVeggies> veggiesList= GameController.getInstance().getVeggiesList();
        setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
    }

    @Override
    public void attack(Object o) {
        if(getAttackCooldown()>0){
            return;
        }
        // SLIME attack veggie
        if(o instanceof BaseVeggies){
            BaseVeggies veggie = (BaseVeggies) o;
            veggie.setHp(veggie.getHp()-this.getDamage());

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

    @Override
    public void walk() {
        int walkCount = 0;
        double disX = this.getPositionX() - this.getTargetVeggie().getPositionX();
        double disY = this.getPositionY() - this.getTargetVeggie().getPositionY();
        int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));

        while( (distance - this.getAttackRange()) > Config.SLIMEWALKSTEP & walkCount < 10) {
            disX = this.getPositionX() - this.getTargetVeggie().getPositionX();
            disY = this.getPositionY() - this.getTargetVeggie().getPositionY();
            distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));
            this.setPositionX((float) (this.getPositionX() - (Math.signum(disX))*(Config.SLIMEWALKSTEP)));
            this.setPositionY((float) (this.getPositionY() - (Math.signum(disY))*(Config.SLIMEWALKSTEP)));
            walkCount += 1;
        }
    }
}
