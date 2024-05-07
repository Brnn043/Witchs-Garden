package Items.Character;

import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Inventory.Stick;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Player extends BaseCharacter{
    private Stick stick;
    private int money;
    private boolean isWalk;
    private boolean isAttack;
    private final int witchHeight = 70;
    private final int witchWidth = 50;

    public Player(int positionX, int positionY, int maxSpeedRate, int attackRange, int damage) {
        super(positionX, positionY, maxSpeedRate, attackRange, damage);
        setStick(null);
        setMoney(0);
        setWalk(false);
        System.out.println(getSpeedRate());
    }

    @Override
    public void attack() {
        // player attack slime
        if(getAttackCooldown()>0){
            return;
        }

        if (! InputUtility.getKeyPressed(KeyCode.Q)) {
            return;
        }

        if(this.getStick() == null){
            return;
        }



        for(Slime slime : GameController.getInstance().getSlimeList()) {
            double disX = GameController.getInstance().getPlayer().getX() - slime.getX();
            double disY = GameController.getInstance().getPlayer().getY() - slime.getY();
            double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
            if( distance <= stick.getAttackRange() ) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            slime.setHp( slime.getHp() - stick.getDamage() );
                            stick.setDurability(stick.getDurability() - Config.STICKDURABILITYPERATTACK);
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
        }
    }

    public void action(){
        weatherEffected();
        walk();
        attack();
    }

//    @Override
//    public void walk() {
//        // WASD to walk in map
//        if (InputUtility.getKeyPressed(KeyCode.W)) {
//            setY(getY()-(int)this.getSpeedRate());
//            setWalk(true);
//        }else if (InputUtility.getKeyPressed(KeyCode.A)) {
//            setX(getX()-(int)this.getSpeedRate());
//            setWalk(true);
//        } else if (InputUtility.getKeyPressed(KeyCode.S)) {
//            setY(getY()+(int)this.getSpeedRate());
//            setWalk(true);
//        }else if (InputUtility.getKeyPressed(KeyCode.D)) {
//            setX(getX()+(int)this.getSpeedRate());
//            setWalk(true);
//        }else{
//            setWalk(false);
//        }
//    }
@Override
public void walk() {
    // WASD to walk in map
    if (InputUtility.getKeyPressed(KeyCode.W)) {
        if (collideWith(GameController.getInstance().getHouse(),0,-(int)this.getSpeedRate())) {
            setWalk(false);
            return;
        }
        setY(getY()-(int)this.getSpeedRate());
        setWalk(true);
    }else if (InputUtility.getKeyPressed(KeyCode.A)) {
        if (collideWith(GameController.getInstance().getHouse(),-(int)this.getSpeedRate(),0)) {
            setWalk(false);
            return;
        }
        setX(getX()-(int)this.getSpeedRate());
        setWalk(true);
    } else if (InputUtility.getKeyPressed(KeyCode.S)) {
        if (collideWith(GameController.getInstance().getHouse(),0,(int)this.getSpeedRate())) {
            setWalk(false);
            return;
        }
        setY(getY()+(int)this.getSpeedRate());
        setWalk(true);
    }else if (InputUtility.getKeyPressed(KeyCode.D)) {
        if (collideWith(GameController.getInstance().getHouse(),(int)this.getSpeedRate(),0)) {
            setWalk(false);
            return;
        }
        setX(getX()+(int)this.getSpeedRate());
        setWalk(true);
    }else{
        setWalk(false);
    }
}

    @Override
    public void draw(GraphicsContext gc) {
        if(isAttack()){
            gc.drawImage(RenderableHolder.witchAttackSprite, getX() - witchWidth/2, getY() - witchHeight/2,witchWidth,witchHeight);
            return;
        }

        if(isWalk()){
            if(getStick()==null){
                gc.drawImage(RenderableHolder.witchWalkSprite, getX() - witchWidth/2, getY() - witchHeight/2,witchWidth,witchHeight);
            }else{
                gc.drawImage(RenderableHolder.witchWalkBroomSprite, getX() - witchWidth/2, getY() - witchHeight/2,witchWidth,witchHeight);
            }
        }else{
            if(getStick()==null){
                gc.drawImage(RenderableHolder.witchSprite, getX() - witchWidth/2, getY() - witchHeight/2,witchWidth,witchHeight);
            }else{
                gc.drawImage(RenderableHolder.witchBroomSprite, getX() - witchWidth/2, getY() - witchHeight/2,witchWidth,witchHeight);
            }
        }
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