package Games;

import GUISharedObject.RenderableHolder;
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
        this.player = new Player(50,50,20,5,3);
        this.veggiesList = new ArrayList<BaseVeggies>();
        this.slimeList = new ArrayList<Slime>();
        this.clock = new Clock();
        this.gameover = false;
        this.gameTimer = 10;
        initGames();

        // add player in GUI
        RenderableHolder.getInstance().add(this.player);
    }

    public static void play() throws InterruptedException {

        // testing run
//        System.out.println("---------- Timer : " + getInstance().getGameTimer() + "------------");
//        System.out.println("Entities in game");
//        Slime slime1 = getInstance().getslimeList().get(0);
//        System.out.println("slime 1,  X =" + slime1.getPositionX() + " Y = " + slime1.getPositionY());
//        System.out.println("slime 1's target veggie,  X =" + slime1.getTargetVeggie().getPositionX() + " Y = " + slime1.getTargetVeggie().getPositionY() + " HP :" + slime1.getTargetVeggie().getHp());

        GameController.getInstance().getPlayer().action();

        // add a slime
        while(getInstance().getSlimeList().size() < 5){
            getInstance().getSlimeList().add(new Slime());
        }

        // decreasing coolDown for slimes, delete HP<0 slime
        for(Slime slime: instance.getSlimeList()){
            // delete slime if HP is < 0
            if(slime.getHp() <= 0){
                getInstance().getSlimeList().remove(slime);
                continue;
            }

            // walk to target veggie & attack
            slime.walk();
            slime.attack();
        }

        // veggies :
        ArrayList<BaseVeggies> veggies = getInstance().getVeggiesList();
        ArrayList<BaseVeggies> delVeggie = new ArrayList<BaseVeggies>();

        for(BaseVeggies veggie : getInstance().getVeggiesList()) {
            if(veggie.getWaterPoint() <= 0 || veggie.getHp() <= 0) {
                delVeggie.add(veggie);
            }

        }

        // delete dead veggie
        for(BaseVeggies veggie : delVeggie){
            getInstance().getVeggiesList().remove(veggie);
            getInstance().getVeggiesList().add(GameController.getInstance().getNewVeggie());
//            System.out.println("veggie dead");
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

    public ArrayList<Slime> getSlimeList() { return slimeList; }

    public void setSlimeList(ArrayList<Slime> slimeList) { this.slimeList = slimeList; }

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
