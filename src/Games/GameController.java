package Games;

import Items.Character.Player;
import Items.Character.Slime;
import Items.Inventory.Clock;
import Items.Inventory.Stick;
import Items.Veggies.BaseVeggies;
import Items.Veggies.Bean;
import Items.Veggies.Cucumber;
import Items.Veggies.Rice;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GameController {
    private static GameController instance;
    private Player player;
    private ArrayList<Slime> slimeList;
    private Clock clock;
    private ArrayList<BaseVeggies> veggiesList;
    private ArrayList<Stick> stickOnGround;
    private boolean gameover;
    private int gameTimer;



    public GameController() {
        this.player = new Player(0,0,20,5,3);
        this.veggiesList = new ArrayList<BaseVeggies>();
        this.slimeList = new ArrayList<Slime>();
        this.clock = new Clock();
        this.gameover = false;
        this.gameTimer = 60;
        initGames();
    }

    public static void play() throws InterruptedException {
        while(getInstance().getslimeList().size() < 5){
            getInstance().getslimeList().add(new Slime());
        }
        // testing run
        System.out.println("---------- Timer : " + getInstance().getGameTimer() + "------------");
        System.out.println("Entities in game");
        Slime slime1 = getInstance().getslimeList().get(0);
        System.out.println("slime 1,  X =" + slime1.getPositionX() + " Y = " + slime1.getPositionY());
        System.out.println("slime 1's target veggie,  X =" + slime1.getTargetVeggie().getPositionX() + " Y = " + slime1.getTargetVeggie().getPositionY() + " HP :" + slime1.getTargetVeggie().getHp());

        // set player coolDown
        getInstance().getPlayer().setAttackCooldown(getInstance().getPlayer().getAttackCooldown() - 1);

        // decreasing coolDown for slimes, delete HP<0 slime
        for(Slime slime: instance.getslimeList()){
            // decrease attack cooldown
            slime.setAttackCooldown(slime.getAttackCooldown() - 1);

            // delete slime if HP is < 0
            if(slime.getHp() <= 0){
                getInstance().getslimeList().remove(slime);
                continue;
            }

            // walk to target veggie & attack
            double disX = slime.getTargetVeggie().getPositionX() - slime.getPositionX();
            double disY = slime.getTargetVeggie().getPositionY() - slime.getPositionY();
            int distance = (int) Math.floor(Math.sqrt( Math.pow(disX,2) + Math.pow(disY,2) ));
            if( (distance - slime.getAttackRange()) <= Config.SLIMEWALKSTEP ) {
                ArrayList<BaseVeggies> veggiesList= GameController.getInstance().getVeggiesList();
                if(veggiesList.contains(slime.getTargetVeggie())){
                    slime.attack(slime.getTargetVeggie());
                    System.out.println("slime ATTACK!!!");
                }else{
                    slime.setTargetVeggie(veggiesList.get((int) (Math.random()*veggiesList.size())));
                    System.out.println("slime find new target");
                }
            }else{
                slime.walk();
            }
        }

        // veggies :
        ArrayList<BaseVeggies> veggies = getInstance().getVeggiesList();
        ArrayList<BaseVeggies> delVeggie = new ArrayList<BaseVeggies>();

        for(BaseVeggies veggie : getInstance().getVeggiesList()) {
            veggie.setWaterPoint(veggie.getWaterPoint() - veggie.getWaterDroppingRate());
            veggie.setGrowthPoint(veggie.getGrowthPoint() + veggie.getGrowthRate());
            if(veggie.getWaterPoint() <= 0 || veggie.getHp() <= 0) {
                delVeggie.add(veggie);
            }

        }

        // delete dead veggie
        for(BaseVeggies veggie : delVeggie){
            getInstance().getVeggiesList().remove(veggie);
            getInstance().getVeggiesList().add(GameController.getInstance().getNewVeggie());
            System.out.println("veggie dead");
        }

        // clock :
        // haven't implemented changing the season when user click the key
        Clock clock = getInstance().getClock();
        clock.setTimer(clock.getTimer()-1);

        // check gameTimer that will decrease every 1 second
        TimeUnit.SECONDS.sleep(1);
        getInstance().setGameTimer(getInstance().getGameTimer()-1);
        if(getInstance().getGameTimer() == 0){
            getInstance().setGameover(true);
        }
    }

    public void initGames(){
        getVeggiesList().add(getNewVeggie());
        getVeggiesList().add(getNewVeggie());
        getVeggiesList().add(getNewVeggie());
        getVeggiesList().add(getNewVeggie());
    }
    public BaseVeggies getNewVeggie(){
        int veggieType = (int) (Math.random()*3);
        if (veggieType == 0) {
            return new Bean();
        }else if(veggieType == 1){
            return new Cucumber();
        }else{
            return new Rice();
        }
    }

    public static GameController getInstance() {
        if(instance == null) instance = new GameController();
        return instance;
    }

    public ArrayList<Slime> getslimeList() { return slimeList; }

    public void setslimeList(ArrayList<Slime> slimeList) { this.slimeList = slimeList; }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void setInstance(GameController instance) {
        GameController.instance = instance;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public ArrayList<BaseVeggies> getVeggiesList() {
        return veggiesList;
    }

    public void setVeggiesList(ArrayList<BaseVeggies> veggiesList) {
        this.veggiesList = veggiesList;
    }

    public ArrayList<Stick> getStickOnGround() {
        return stickOnGround;
    }

    public void setStickOnGround(ArrayList<Stick> stickOnGround) {
        this.stickOnGround = stickOnGround;
    }

    public boolean getGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public boolean isGameover() {
        return gameover;
    }

    public int getGameTimer() {
        return gameTimer;
    }

    public void setGameTimer(int gameTimer) {
        this.gameTimer = Math.max(0, gameTimer);
    }
}
