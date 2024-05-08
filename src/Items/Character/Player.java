package Items.Character;

import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Games.Config;
import Games.GameController;
import Items.Inventory.Broom;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class Player extends BaseCharacter{
    private Broom broom;
    private int money;
    private boolean isWalk;
    private boolean isAttack;
//    private final int getHeight() = 70;
//    private final int getWidth() = 50;

    public Player(int positionX, int positionY, int maxSpeedRate, int attackRange, int damage) {
        super(positionX, positionY, maxSpeedRate, attackRange, damage);
        setBroom(null);
        setMoney(0);
        setWalk(false);
        setWidth(50);
        setHeight(70);
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

        if(this.getBroom() == null){
            return;
        }



        for(Slime slime : GameController.getInstance().getSlimeList()) {
            double disX = GameController.getInstance().getPlayer().getX() - slime.getX();
            double disY = GameController.getInstance().getPlayer().getY() - slime.getY();
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
            gc.drawImage(RenderableHolder.witchAttackSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            return;
        }

        if(isWalk()){
            if(getBroom()==null){
                gc.drawImage(RenderableHolder.witchWalkSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }else{
                gc.drawImage(RenderableHolder.witchWalkBroomSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }
        }else{
            if(getBroom()==null){
                gc.drawImage(RenderableHolder.witchSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }else{
                gc.drawImage(RenderableHolder.witchBroomSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }
        }
    }

    public Broom getBroom() {
        return broom;
    }

    public void setBroom(Broom broom) {
        this.broom = broom;
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