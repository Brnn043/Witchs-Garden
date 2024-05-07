package Games;

import GUI.Map.BackgroundImage;
import GUI.Map.House;
import GUISharedObject.RenderableHolder;
import Items.Character.Player;
import Items.Character.Slime;
import Items.Inventory.Broom;
import Items.Inventory.Clock;
import Items.Veggies.BaseVeggies;
import Items.Veggies.Bean;
import Items.Veggies.Cucumber;
import Items.Veggies.Rice;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private Player player;
    private ArrayList<Slime> slimeList;
    private Clock clock;
    private ArrayList<BaseVeggies> veggiesList;
    private ArrayList<Broom> broomOnGround;
    private boolean gameover;
    private int gameTimer;
    private BackgroundImage backgroundImage;
    private House house;


    public GameController() {
        this.player = new Player(400,300,5,5,3);
        this.veggiesList = new ArrayList<BaseVeggies>();
        this.slimeList = new ArrayList<Slime>();
        this.clock = new Clock();
        this.gameover = false;
        this.gameTimer = 30;
        this.backgroundImage = new BackgroundImage();
        this.house = new House();
        this.broomOnGround = new ArrayList<Broom>();
        initGames();

        // add player in GUI
        RenderableHolder.getInstance().add(this.player);
        RenderableHolder.getInstance().add(this.backgroundImage);
        RenderableHolder.getInstance().add(this.house);
    }

    public static void play() throws InterruptedException {

        // testing run

//        try{
//            Slime slime1 = getInstance().getSlimeList().get(0);
//            System.out.println("slime 1,  X =" + slime1.getPositionX() + " Y = " + slime1.getPositionY());
//            System.out.println("slime 1's target veggie,  X =" + slime1.getTargetVeggie().getPositionX() + " Y = " + slime1.getTargetVeggie().getPositionY() + " HP :" + slime1.getTargetVeggie().getHp());
//        }catch (Exception e){
//            System.out.println("");
//        }


        // add a slime
        while(getInstance().getSlimeList().size() < 5){
            Slime slime = new Slime();
            getInstance().getSlimeList().add(slime);
            RenderableHolder.getInstance().add(slime);
        }


        for (int i = 0; i < instance.getSlimeList().size(); i++) {
            // delete slime if HP is < 0
            Slime slime = instance.getSlimeList().get(i);
            if(slime.getHp() <= 0){
                getInstance().getSlimeList().remove(slime);
                RenderableHolder.getInstance().getEntities().remove(slime);
                continue;
            }
            slime.weatherEffected();
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
            getInstance().getNewVeggie();
        }


        for(Broom broom : GameController.getInstance().broomOnGround){
            broom.collected();
        }
    }

    public void initGames(){
        getNewVeggie();
        getNewVeggie();
        getNewVeggie();
        getNewVeggie();
    }
    public void getNewVeggie(){
        int veggieType = (int) (Math.random()*3);
        if (veggieType == 0) {
            Bean bean = new Bean();
            getVeggiesList().add(bean);
            RenderableHolder.getInstance().add(bean);
        }else if(veggieType == 1){
            Cucumber cucumber = new Cucumber();
            getVeggiesList().add(cucumber);
            RenderableHolder.getInstance().add(cucumber);
        }else{
            Rice rice = new Rice();
            getVeggiesList().add(rice);
            RenderableHolder.getInstance().add(rice);
        }
        return ;
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

    public ArrayList<Broom> getBroomOnGround() {
        return broomOnGround;
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

    public House getHouse() {
        return house;
    }
}
