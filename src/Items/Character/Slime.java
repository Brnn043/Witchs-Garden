package Items.Character;

import Games.Config;
import Games.GameController;
import Items.Veggies.BaseVeggies;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class Slime extends BaseCharacter{
    private int Hp;
    private BaseVeggies targetVeggie;
    public Slime() {
        super(((float)Math.random()*100)*Config.GAMEFRAMEWIDTH/100
                , ((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100
                , (int) ((float) (Math.random())*Config.SLIMEMAXSPEEDRATE)
                , (int) ((float) (Math.random())*Config.SLIMEMAXDAMAGERANGE)
                , (int) ((float) (Math.random())*Config.SLIMEMAXDAMAGE));
        setHp(Math.max(5,(int) ((float)Math.random()*25)));
        ArrayList<BaseVeggies> veggiesList= GameController.getInstance().getVeggiesList();
        setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
    }

    @Override
    public void attack() {
        if(getAttackCooldown()>0){
            return;
        }

        // check if target veggie still in game
        ArrayList<BaseVeggies> veggiesList= GameController.getInstance().getVeggiesList();
        if(!veggiesList.contains(this.getTargetVeggie())){
            this.setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
            System.out.println("slime find new target");
        }

        // calculate distance from target veggie
        double disX = this.getTargetVeggie().getPositionX() - this.getPositionX();
        double disY = this.getTargetVeggie().getPositionY() - this.getPositionY();
        int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));

        // attack veggie
        if( (distance - this.getAttackRange()) <= Config.SLIMEWALKSTEP ) {
            this.getTargetVeggie().setHp(this.getTargetVeggie().getHp()-this.getDamage());
            setAttackCooldown(5);
            System.out.println("slime ATTACK!!!");
        }

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

        double disX = this.getPositionX() - this.getTargetVeggie().getPositionX();
        double disY = this.getPositionY() - this.getTargetVeggie().getPositionY();
        int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));

        if( distance - this.getAttackRange() > Config.SLIMEWALKSTEP ){
            this.setPositionX((float) (this.getPositionX() - (Math.signum(disX))*(Config.SLIMEWALKSTEP * this.getSpeedRate())));
            this.setPositionY((float) (this.getPositionY() - (Math.signum(disY))*(Config.SLIMEWALKSTEP * this.getSpeedRate())));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillArc(getPositionX() - 10, getPositionY() - 10, 10 * 2, 10 * 2, 0, 360, ArcType.OPEN);
    }
}
