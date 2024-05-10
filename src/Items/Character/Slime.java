package Items.Character;

import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Veggies.BaseVeggies;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public abstract class Slime extends BaseCharacter{
    private int Hp;
    private BaseVeggies targetVeggie;
    private final int SLIMEMAXHP;
    public Slime(int speedRate) {
        super(speedRate
                ,(int) ( (float) Math.max(Config.SLIMEMAXSPEEDRATE, (Math.random())*Config.SLIMEMAXDAMAGERANGE) )
                , (int) ( (float) (Math.random())*Config.SLIMEMAXDAMAGE)  );
        this.SLIMEMAXHP = Math.max(10,(int) ((float)Math.random()*20));
        setHp(SLIMEMAXHP);
        ArrayList<BaseVeggies> veggiesList= GameController.getInstance().getVeggiesList();
        setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
        setWidth(30);
        setHeight(30);
        double posX = (Math.random()*100)*Config.GAMEFRAMEWIDTH/100;
        double posY = (Math.random()*100)*Config.GAMEFRAMEHEIGHT/100;
        while (!GameController.getInstance().isPositionAccesible(posX-getWidth()/2,posY-getHeight()/2,getWidth(),getHeight(),false)){
            posX = (Math.random()*100)*Config.GAMEFRAMEWIDTH/100;
            posY = (Math.random()*100)*Config.GAMEFRAMEHEIGHT/100;
            System.out.println("Slime cannot be spawn here. Find new pos...");
        }
        setX(posX);
        setY(posY);
        this.z = getZ() + 400;
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
            double posX = (float) (this.getX() - (Math.signum(disX))*(Config.SLIMEWALKSTEP * this.getSpeedRate() * 0.2));
            double posY = (float) (this.getY() - (Math.signum(disY))*(Config.SLIMEWALKSTEP * this.getSpeedRate() * 0.2));

            if (!GameController.getInstance().isPositionAccesible(posX-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight(),false)) posX=getX();
            if (!GameController.getInstance().isPositionAccesible(getX()-getWidth()/2,posY-getHeight()/2,getWidth(),getHeight(),false)) posY=getY();

            this.setX(posX);
            this.setY(posY);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Draw slime
        if(this instanceof NormalSlime){
            gc.drawImage(RenderableHolder.normalSlimeSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
        }
        if(this instanceof hardHitSlime){
            gc.drawImage(RenderableHolder.hardHitSlimeSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
        }
        if(this instanceof SpeedSlime){
            gc.drawImage(RenderableHolder.speedSlimeSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
        }

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
