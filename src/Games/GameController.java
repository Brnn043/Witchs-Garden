package Games;

import Items.Character.Player;
import Items.Character.Zombie;
import Items.Inventory.Clock;
import Items.Veggies.BaseVeggies;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private Player player;
    private ArrayList<Zombie> zombieList;
    private Clock clock;
    private ArrayList<BaseVeggies> veggiesList;


    public GameController() {
        // haven't implemented
        setPlayer(new Player(0,0,20,5,3));
        setZombieList(new ArrayList<Zombie>());
        setVeggiesList(new ArrayList<BaseVeggies>());
    }

    public static void main(String[] args) {

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
}
