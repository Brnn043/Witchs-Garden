package Games;

import Items.Character.Player;
import Items.Character.Zombie;
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
    private ArrayList<Zombie> zombieList;
    private Clock clock;
    private ArrayList<BaseVeggies> veggiesList;
    private ArrayList<Stick> stickOnGround;
    private boolean gameover;
    private int gameTimer;



    public GameController() {
        this.player = new Player(0,0,20,5,3);
        this.veggiesList = new ArrayList<BaseVeggies>();
        this.zombieList = new ArrayList<Zombie>();
        this.clock = new Clock();
        this.gameover = false;
        this.gameTimer = 5;
        initGames();
    }

    public static void play() throws InterruptedException {
        while(getInstance().getZombieList().size() < 5){
            getInstance().getZombieList().add(new Zombie());
        }
        // testing run
        System.out.println("---------- Timer : " + getInstance().getGameTimer() + "------------");
        System.out.println("Entities in game");
        Zombie zom1 = getInstance().getZombieList().get(0);
        System.out.println("Zombie 1,  X =" + zom1.getPositionX() + " Y = " + zom1.getPositionY());
        System.out.println("zombie 1's target veggie,  X =" + zom1.getTargetVeggie().getPositionX() + " Y = " + zom1.getTargetVeggie().getPositionY());

        // set player coolDown
        getInstance().getPlayer().setAttackCooldown(getInstance().getPlayer().getAttackCooldown() - 1);

        // decreasing coolDown for zombies, delete HP<0 zombie
        for(Zombie zombie: instance.getZombieList()){
            zombie.setAttackCooldown(zombie.getAttackCooldown() - 1);
            zombie.walk();
            // delete zombie if HP is < 0
            if(zombie.getHp() <= 0){
                getInstance().getZombieList().remove(zombie);
            }
        }

        // veggies :
        ArrayList<BaseVeggies> veggies = getInstance().getVeggiesList();
        for(BaseVeggies veggie : veggies) {
            veggie.setWaterPoint(veggie.getWaterPoint() - veggie.getWaterDroppingRate());
            veggie.setGrowthPoint(veggie.getGrowthPoint() + veggie.getGrowthRate());
            if(veggie.getWaterPoint() <= 0) {
                getInstance().getVeggiesList().remove(veggie);
                veggies.add(GameController.getInstance().getNewVeggie());
            }
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

    public ArrayList<Zombie> getZombieList() { return zombieList; }

    public void setZombieList(ArrayList<Zombie> zombieList) { this.zombieList = zombieList; }

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
