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


public class Player extends BaseCharacter{
    private Broom broom;
    private int walk; // 0 = stay 1 = front 2 = right 3 = left
    private boolean isAttack;
    private int maxAttackCoolDown;

    public Player(int positionX, int positionY, int maxSpeedRate, int attackRange, int damage) {
        super(positionX, positionY, maxSpeedRate, attackRange, damage);
        setBroom(null);
        setWalk(0);
        setWidth(90);
        setHeight(128.6);
        this.z = 999;
    }

    @Override
    public void weatherEffected() {
        super.weatherEffected();
        Config.Weather weatherNow = GameController.getInstance().getClock().getWeather();
        if(weatherNow == Config.Weather.SUNNY){
            setMaxAttackCoolDown( (int) ( 0.5 * Config.PLAYERCOOLDOWNTIME) );
        } else if (weatherNow == Config.Weather.RAINY) {
            setMaxAttackCoolDown( (int) ( 0.7 * Config.PLAYERCOOLDOWNTIME) );
        }else if (weatherNow == Config.Weather.SNOWY){
            setMaxAttackCoolDown( (int) ( 1.0 * Config.PLAYERCOOLDOWNTIME) );
        }
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
                                setAttackCooldown(getMaxAttackCoolDown());
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
        setWalk(0);

        int nerfDiagonalWalk = 1;
        if((InputUtility.getKeyPressed(KeyCode.W) || InputUtility.getKeyPressed(KeyCode.S)) &&
                (InputUtility.getKeyPressed(KeyCode.A) || InputUtility.getKeyPressed(KeyCode.D))){
            nerfDiagonalWalk = 2;
        }

        if (InputUtility.getKeyPressed(KeyCode.W)) {
            posY -= (int) (this.getSpeedRate() / nerfDiagonalWalk);
            setWalk(1);
        }else if (InputUtility.getKeyPressed(KeyCode.S)) {
            posY += (int) (this.getSpeedRate() / nerfDiagonalWalk);
            setWalk(1);
        }

        if (InputUtility.getKeyPressed(KeyCode.A)) {
            posX -= (int) (this.getSpeedRate() / nerfDiagonalWalk);
            setWalk(3);
        } else  if (InputUtility.getKeyPressed(KeyCode.D)) {
            posX += (int) (this.getSpeedRate() / nerfDiagonalWalk);
            setWalk(2);
        }


        if (!GameController.getInstance().isPositionAccesible(posX,posY + getHeight()/4,getWidth()/2,getHeight()/2,true)) return;
        setX(posX);
        setY(posY);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(isAttack() && this.getBroom()!=null){
            gc.drawImage(RenderableHolder.witchAttackSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeOval(getX()- getBroom().getAttackRange(), getY()- getBroom().getAttackRange(), getBroom().getAttackRange() * 2, getBroom().getAttackRange() * 2);
            return;
        }

        if(getWalk()==0){
            if(getBroom()==null){
                gc.drawImage(RenderableHolder.witchSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }else{
                gc.drawImage(RenderableHolder.witchBroomSprite, getX() - getWidth()/2, getY() - getHeight()/2,getWidth(),getHeight());
            }
        }else if(getWalk()==1) {
            if (getBroom() == null) {
                gc.drawImage(RenderableHolder.witchWalkSprite, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
            } else {
                gc.drawImage(RenderableHolder.witchWalkBroomSprite, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
            }
        }else if(getWalk()==2){
            if (getBroom() == null) {
                gc.drawImage(RenderableHolder.witchRightSprite, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
            } else {
                gc.drawImage(RenderableHolder.witchRightBroomSprite, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
            }
        }else if(getWalk()==3){
            if (getBroom() == null) {
                gc.drawImage(RenderableHolder.witchLeftSprite, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
            } else {
                gc.drawImage(RenderableHolder.witchLeftBroomSprite, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
            }
        }

        if(getBroom() != null){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            float broomDegree = ((float) getMaxAttackCoolDown() - getAttackCooldown())/getMaxAttackCoolDown() * 360;
            gc.strokeArc(getX() - getBroom().getAttackRange(), getY()- getBroom().getAttackRange(),
                    getBroom().getAttackRange() * 2, getBroom().getAttackRange() * 2,
                    0,broomDegree, ArcType.OPEN );
        }
    }

    public int getMaxAttackCoolDown() { return maxAttackCoolDown; }

    public void setMaxAttackCoolDown(int maxAttackCoolDown) { this.maxAttackCoolDown = maxAttackCoolDown; }

    public Broom getBroom() {
        return broom;
    }

    public void setBroom(Broom broom) {
        this.broom = broom;
    }

    public int getWalk() {
        return walk;
    }

    public void setWalk(int walk) {
        this.walk = walk;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }
}