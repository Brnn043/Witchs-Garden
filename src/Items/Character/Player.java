package Items.Character;

import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Inventory.Broom;
import Items.Veggies.BaseVeggies;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class Player extends BaseCharacter{
    private Broom broom;
    private boolean isWalk;
    private boolean isAttack;

    public Player(int positionX, int positionY, int maxSpeedRate, int attackRange, int damage) {
        super(positionX, positionY, maxSpeedRate, attackRange, damage);
        setBroom(null);
        setWalk(false);
        setWidth(90);
        setHeight(128.6);
        this.z = 999;
    }

    @Override
    public void attack() {
        // player attack slime
        if(getAttackCooldown()>0){
            return;
        }

        if (! InputUtility.getKeyPressed(KeyCode.SPACE)) {
            return;
        }

        if(this.getBroom() == null){
            return;
        }



        for(Slime slime : GameController.getInstance().getSlimeList()) {
            try{
                double disX = this.getX() - slime.getX();
                double disY = this.getY() - slime.getY();
                double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
                if( distance <= broom.getAttackRange() ) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                slime.setHp( slime.getHp() - broom.getDamage() );
                                broom.setDurability(broom.getDurability() - Config.BROOMDURABILITYPERATTACK);
                                setAttackCooldown(Config.PLAYERCOOLDOWNTIME);
                                setAttack(true);
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            setAttack(false);
                        }
                    }).start();
                }
            } catch (Exception e){
                System.out.println(e);
            }

        }
    }

    public void collectVeggie() {
        // player collect veggie
        if (! InputUtility.getKeyPressed(KeyCode.E)) {
            return;
        }

        if(GameController.getInstance().getVeggiesList().isEmpty()){
            return;
        }
        for(BaseVeggies veggie : GameController.getInstance().getVeggiesList()) {
            double disX = this.getX() - veggie.getX();
            double disY = this.getY() - veggie.getY();
            double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
            if( distance <= 70 ) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        veggie.collected();
                    }
                }).start();
            }
        }
    }

    public void action(){
        weatherEffected();
        walk();
        attack();
        collectVeggie();
    }
    @Override
    public void walk() {
        // WASD to walk in map
        double posX = getX();
        double posY = getY();
        setWalk(false);

        if (InputUtility.getKeyPressed(KeyCode.W)) {
            posY -= (int)this.getSpeedRate();
            setWalk(true);
        }else if (InputUtility.getKeyPressed(KeyCode.S)) {
            posY += (int)this.getSpeedRate();
            setWalk(true);
        }
        if (InputUtility.getKeyPressed(KeyCode.A)) {
            posX -= (int)this.getSpeedRate();
            setWalk(true);
        } else  if (InputUtility.getKeyPressed(KeyCode.D)) {
            posX += (int)this.getSpeedRate();
            setWalk(true);
        }

        if (!GameController.getInstance().isPositionAccesible(posX,posY+getHeight(),getWidth(),getHeight(),true)) return;
        setX(posX);
        setY(posY);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
        if(isAttack() && getBroom()!=null){
            gc.drawImage(RenderableHolder.witchAttackSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeOval(getX()- getBroom().getAttackRange(), getY()- getBroom().getAttackRange(), getBroom().getAttackRange() * 2, getBroom().getAttackRange() * 2);
            return;
        }

        if(isWalk()){
            if(getBroom()==null){
                gc.drawImage(RenderableHolder.witchWalkSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }else{
                gc.drawImage(RenderableHolder.witchWalkBroomSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(2);
                float broomDegree = ((float) Config.PLAYERCOOLDOWNTIME - getAttackCooldown())/Config.PLAYERCOOLDOWNTIME * 360;
                gc.strokeArc(getX() - getBroom().getAttackRange(), getY()- getBroom().getAttackRange(),
                        getBroom().getAttackRange() * 2, getBroom().getAttackRange() * 2,
                        0,broomDegree, ArcType.OPEN );
            }
        }else{
            if(getBroom()==null){
                gc.drawImage(RenderableHolder.witchSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }else{
                gc.drawImage(RenderableHolder.witchBroomSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(2);
                float broomDegree = ((float) Config.PLAYERCOOLDOWNTIME - getAttackCooldown())/Config.PLAYERCOOLDOWNTIME * 360;
                gc.strokeArc(getX() - getBroom().getAttackRange(), getY()- getBroom().getAttackRange(),
                        getBroom().getAttackRange() * 2, getBroom().getAttackRange() * 2,
                        0,broomDegree, ArcType.OPEN );            }
        }
    }

    public Broom getBroom() {
        return broom;
    }

    public void setBroom(Broom broom) {
        this.broom = broom;
    }

    public boolean isWalk() {
        return isWalk;
    }

    public void setWalk(boolean walk) {
        isWalk = walk;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }
}