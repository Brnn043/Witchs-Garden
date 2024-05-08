package Games;

import GUI.Map.BackgroundImage;
import GUI.Map.House;
import GUI.Map.Tree;
import GUISharedObject.RenderableHolder;
import Items.Character.*;
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
    private final ArrayList<Broom> broomOnGround;
    private boolean gameover;
    private int gameTimer;
    private BackgroundImage backgroundImage;
    private House house;
    private ArrayList<Tree> trees;


    public GameController() {
        player = new Player(400,300,5,5,3);
        veggiesList = new ArrayList<BaseVeggies>();
        slimeList = new ArrayList<Slime>();
        clock = new Clock();
        gameover = false;
        gameTimer = Config.GAMETIMER;
        backgroundImage = new BackgroundImage();
        house = new House();
        broomOnGround = new ArrayList<Broom>();
        trees = new ArrayList<>();
        trees.add(new Tree(500,400,100,120,1));

        // add player in GUI
        RenderableHolder.getInstance().add(player);
        RenderableHolder.getInstance().add(backgroundImage);
        RenderableHolder.getInstance().add(house);
        for (Tree tree:trees) RenderableHolder.getInstance().add(tree);

        initGames();
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

        // check if broom's duration == 0
        if(getInstance().getPlayer().getBroom() != null){
            if(getInstance().getPlayer().getBroom().getDurability() == 0){
                getInstance().getPlayer().setBroom(null);
            }
        }
        // add a slime
        while(getInstance().getSlimeList().size() < 5){
            getInstance().getNewSlime();
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
                RenderableHolder.getInstance().getEntities().remove(veggie);
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
        BaseVeggies veggie;
        if (veggieType == 0) {
            veggie = new Bean();
        }else if(veggieType == 1){
            veggie = new Cucumber();
        }else{
            veggie = new Rice();
        }
        getVeggiesList().add(veggie);
        RenderableHolder.getInstance().add(veggie);
    }

    public void getNewSlime(){
        int slimeType = (int) (Math.random()*3);
        Slime slime;
        System.out.println(slimeType);
        if (slimeType == 0) {
            slime = new NormalSlime();
        }else if(slimeType == 1){
            slime = new TeleportSlime();
        }else{
            slime = new SpeedSlime();
        }
        getInstance().getSlimeList().add(slime);
        RenderableHolder.getInstance().add(slime);
    }

    public static GameController getInstance() {
        if(instance == null) instance = new GameController();
        return instance;
    }
    public boolean isPositionAccesible(double x,double y,double width,double height){
        for (Tree tree:trees) {
            if (tree.collideWith(x,y,width,height)) return false;
        }
        return !house.collideWith(x,y,width,height);
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

    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setHouse(House house) {
        this.house = house;
    }

}
