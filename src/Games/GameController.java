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

public class GameController {
    private static GameController instance;
    private Player player;
    private ArrayList<Zombie> zombieList;
    private Clock clock;
    private ArrayList<BaseVeggies> veggiesList;
    private ArrayList<Stick> stickOnGround;
    private boolean gameover;


    public GameController() {
        this.player = new Player(0,0,20,5,3);
        this.veggiesList = new ArrayList<BaseVeggies>();
        this.zombieList = new ArrayList<Zombie>();
        this.clock = new Clock();
        this.gameover = false;
        initGames();
    }

    public static void play() {

    }

    public void initGames(){
        getVeggiesList().add(getNewVeggie());
        getVeggiesList().add(getNewVeggie());
        getVeggiesList().add(getNewVeggie());
        getVeggiesList().add(getNewVeggie());
        getZombieList().add(new Zombie());
        getZombieList().add(new Zombie());
        getZombieList().add(new Zombie());
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
}
