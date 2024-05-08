package Items.Character;

import Games.Config;
import Games.GameController;
import Items.Veggies.BaseVeggies;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class Slime extends BaseCharacter{
    private int Hp;
    private BaseVeggies targetVeggie;
    private final int SLIMEMAXHP;
    public Slime() {
        super(((float)Math.random()*100)*Config.GAMEFRAMEWIDTH/100
                , ((float)Math.random()*100)*Config.GAMEFRAMEHEIGHT/100
                , (int) ((float) Math.max(Config.SLIMEMINSPEEDRATE, (Math.random())*Config.SLIMEMAXSPEEDRATE))
                , (int) ((float)  Math.max(Config.SLIMEMAXSPEEDRATE, (Math.random())*Config.SLIMEMAXDAMAGERANGE))
                , (int) ((float) (Math.random())*Config.SLIMEMAXDAMAGE));
        this.SLIMEMAXHP = Math.max(10,(int) ((float)Math.random()*20));
        setHp(SLIMEMAXHP);
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
        double disX = this.getTargetVeggie().getX() - this.getX();
        double disY = this.getTargetVeggie().getY() - this.getY();
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
        this.Hp = Math.max(0, Math.min(hp, SLIMEMAXHP));
    }


    public BaseVeggies getTargetVeggie() {
        return targetVeggie;
    }

    public void setTargetVeggie(BaseVeggies targetVeggie) {
        this.targetVeggie = targetVeggie;
    }

    @Override
    public void walk() {

        double disX = this.getX() - this.getTargetVeggie().getX();
        double disY = this.getY() - this.getTargetVeggie().getY();
        int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));

        if( distance - this.getAttackRange() > Config.SLIMEWALKSTEP ){
            this.setX((float) (this.getX() - (Math.signum(disX))*(Config.SLIMEWALKSTEP * this.getSpeedRate())));
            this.setY((float) (this.getY() - (Math.signum(disY))*(Config.SLIMEWALKSTEP * this.getSpeedRate())));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Draw slime
        gc.setFill(Color.RED);
        gc.fillArc(getX() - 10, getY() - 10, 10 * 2, 10 * 2, 0, 360, ArcType.OPEN);

        // Calculate the width of the progress bar
        double HPPercentage = (double) getHp() / SLIMEMAXHP; // Get HP percentage
        double HPBarWidth = 20 * HPPercentage; // Calculate progress bar width

        // Draw the progress bar
        double HPBarX = getX() - 10; // Start of progress bar
        double HPBarY = getY() + 15; // Position below the circle

        gc.setFill(Color.GRAY);
        gc.fillRect(HPBarX, HPBarY, 20, 5);
        gc.setFill(Color.ORANGERED);
        gc.fillRect(HPBarX, HPBarY, HPBarWidth, 5);
    }
}
