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

    public Player(int positionX, int positionY, int maxSpeedRate, int attackRange, int damage) {
        super(positionX, positionY, maxSpeedRate, attackRange, damage);
        setStick(null);
        setMoney(0);
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
            double disX = GameController.getInstance().getPlayer().getPositionX() - slime.getPositionX();
            double disY = GameController.getInstance().getPlayer().getPositionY() - slime.getPositionY();
            double distance = Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) );
            if( distance <= stick.getAttackRange() ) {
                slime.setHp( slime.getHp() - stick.getDamage() );
                stick.setDurability(stick.getDurability() - Config.STICKDURABILITYPERATTACK);
                this.setAttackCooldown(Config.PLAYERCOOLDOWNTIME);
            }
        }
    }

    public void action(){
        weatherEffected();
        walk();
        attack();
    }

    @Override
    public void walk() {
        // WASD to walk in map
        if (InputUtility.getKeyPressed(KeyCode.W)) {
            setPositionY(getPositionY()-(int)this.getSpeedRate());
        }else if (InputUtility.getKeyPressed(KeyCode.A)) {
            setPositionX(getPositionX()-(int)this.getSpeedRate());
        } else if (InputUtility.getKeyPressed(KeyCode.S)) {
            setPositionY(getPositionY()+(int)this.getSpeedRate());
        }else if (InputUtility.getKeyPressed(KeyCode.D)) {
            setPositionX(getPositionX()+(int)this.getSpeedRate());
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
//        gc.fillOval(getPositionX() - 10, getPositionY() - 10, 20, 20);
        gc.drawImage(RenderableHolder.playerSprite, getPositionX() - 30, getPositionY() - 50,30,50);
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
