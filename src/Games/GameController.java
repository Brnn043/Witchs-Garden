package Games;

import GUI.GameBackground.RainyBackground;
import GUI.GameBackground.SnowyBackground;
import GUI.GameBackground.SunnyBackground;
import GUI.Map.BackgroundImage;
import GUI.Map.Bush;
import GUI.Map.House;
import GUI.Map.Tree;
import GUISharedObject.IRenderable;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Items.Character.*;
import Items.Inventory.Broom;
import Items.Inventory.Clock;
import Items.Veggies.BaseVeggies;
import Items.Veggies.RedFlower;
import Items.Veggies.RainbowDrake;
import Items.Veggies.Daffodil;

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
    private int maxGameTimer;
    private BackgroundImage backgroundImage;
    private House house;
    private final ArrayList<Tree> trees;
    private final ArrayList<Bush> bushes;
    private final SunnyBackground sunnyBackground;
    private final SnowyBackground snowyBackground;
    private final RainyBackground rainyBackground;
    private int maxRedFlower;
    private int maxRainbowDrake;
    private int maxDaffodil;
    private int redFlowerCount;
    private int rainbowDrakeCount;
    private int daffodilCount;
    private int level = 1;

    public GameController() {
        // add background
        backgroundImage = new BackgroundImage();
        house = new House(135, 72, 280, 225);

        trees = new ArrayList<>();
        bushes = new ArrayList<>();

        //left side
        trees.add(new Tree(30, 327.5, 120, 155, 20, 4));
        trees.add(new Tree(130, 440, 110, 160, 24, 3));
        trees.add(new Tree(15, 500, 110, 160, 27, 2));

        //right side
        trees.add(new Tree(1050, 75, 90, 110, 20, 2));
        trees.add(new Tree(1075, 200, 150, 180, 22, 1));
        trees.add(new Tree(1029.5, 322, 99, 144, 24, 3));
        trees.add(new Tree(940, 420, 105, 160, 28, 5));
        trees.add(new Tree(1050,425,130,170,29,3));

        bushes.add(new Bush(999.5, 160, 95, 60, 21, 1));
        bushes.add(new Bush(994.5, 255, 95, 50, 23, 2));
        bushes.add(new Bush(850, 510, 110, 60, 32, 1));
        bushes.add(new Bush(900, 515, 100, 60, 34, 4));
        bushes.add(new Bush(1060, 525, 100, 60, 36, 3));

        RenderableHolder.getInstance().addBackground(backgroundImage);
        RenderableHolder.getInstance().addBackground(house);
        for (Tree tree : trees) RenderableHolder.getInstance().addBackground(tree);
        for (Bush bush : bushes) RenderableHolder.getInstance().addBackground(bush);

        sunnyBackground = new SunnyBackground(Config.GAMESCREENWIDTH,Config.GAMESCREENHEIGHT);
        snowyBackground = new SnowyBackground(Config.GAMESCREENWIDTH,Config.GAMESCREENHEIGHT);
        rainyBackground = new RainyBackground(Config.GAMESCREENWIDTH,Config.GAMESCREENHEIGHT);
    }

    public static void play() throws InterruptedException {
        // check if broom's duration == 0
        if (getInstance().getPlayer().getBroom() != null) {
            if (getInstance().getPlayer().getBroom().getDurability() == 0) {
                getInstance().getPlayer().setBroom(null);
            }
        }


        for (int i = 0; i < instance.getSlimeList().size(); i++) {
            // delete slime if HP is < 0
            Slime slime = instance.getSlimeList().get(i);
            if (slime.getHp() <= 0) {
                getInstance().getSlimeList().remove(slime);
                RenderableHolder.getInstance().getEntities().remove(slime);
                continue;
            }
            slime.weatherEffected();
        }

        // veggies : check if veggie is dead
        for (int i = 0; i < getInstance().getVeggiesList().size();) {
            BaseVeggies veggie = getInstance().getVeggiesList().get(i);
            if (veggie.getWaterPoint() <= 0 || veggie.getHp() <= 0) {
                deleteVeggie(veggie);
            }else{
                i = i+1;
            }
            veggie.weatherEffected();
        }


        for (Broom broom : GameController.getInstance().broomOnGround) {
            broom.collected();
        }
    }

    public void initGames() {
        getNewVeggie();
        getNewVeggie();
        getNewVeggie();
        getNewVeggie();
        getNewVeggie();
    }

    public void getNewVeggie() {
        int veggieType = (int) (Math.random() * 3);
        BaseVeggies veggie;
        if (veggieType == 0) {
            veggie = new RedFlower();
        } else if (veggieType == 1) {
            veggie = new RainbowDrake();
        } else {
            veggie = new Daffodil();
        }
        getVeggiesList().add(veggie);
        RenderableHolder.getInstance().add(veggie);
    }

    public void getNewSlime() {
        int slimeType = (int) (Math.random() * 3);
        Slime slime;
        System.out.println(slimeType);
        if (slimeType == 0) {
            slime = new NormalSlime();
        } else if (slimeType == 1) {
            slime = new hardHitSlime();
        } else {
            slime = new SpeedSlime();
        }
        getInstance().getSlimeList().add(slime);
        RenderableHolder.getInstance().add(slime);
    }

    public void collectVeggie(BaseVeggies veggie){
        if(veggie instanceof RainbowDrake){
            setRainbowDrakeCount(getRainbowDrakeCount()+1);
        }
        if(veggie instanceof RedFlower){
            setRedFlowerCount(getRedFlowerCount()+1);
        }
        if(veggie instanceof Daffodil){
            setDaffodilCount(getDaffodilCount()+1);
        }
        deleteVeggie(veggie);
    }

    public static void deleteVeggie(BaseVeggies veggie){
        RenderableHolder.getInstance().getEntities().remove(veggie);
        getInstance().getVeggiesList().remove(veggie);
        getInstance().getNewVeggie();
    }

    public static GameController getInstance() {
        if (instance == null) instance = new GameController();
        return instance;
    }

    public void clearStats(int level){
        // clear keyboard input
        InputUtility.clearKeyPressed();

        // delete old entity
        ArrayList<IRenderable> delEntities = new ArrayList<IRenderable>();
        for(IRenderable entity: RenderableHolder.getInstance().getEntities()){
            if(entity instanceof BaseVeggies || entity instanceof Slime || entity instanceof Broom || entity instanceof Player){
                delEntities.add(entity);
            }
        }
        for(IRenderable delEntity: delEntities){
            RenderableHolder.getInstance().getEntities().remove(delEntity);
        }

        // set stat
        this.player = new Player(400, 300, 5, 5, 50);
        RenderableHolder.getInstance().add(player);
        veggiesList = new ArrayList<BaseVeggies>();
        slimeList = new ArrayList<Slime>();
        clock = new Clock();
        gameover = false;
        maxGameTimer = Config.GAMETIMER * level /2;
        gameTimer = maxGameTimer;
        broomOnGround = new ArrayList<Broom>();

        // based on each game
        maxRedFlower = 2 * level;
        maxRainbowDrake = 2 * level;
        maxDaffodil = 2 * level;
        setRedFlowerCount(0);
        setRainbowDrakeCount(0);
        setDaffodilCount(0);

        this.level = level;

        startThread();
    }

    public void startThread(){
        GameController game = GameController.getInstance();
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!game.isGameover()){
                    Clock clock = game.getClock();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // spaw broom every 10 second
                    if(game.getGameTimer()%15 == 0){
                        Broom broom = new Broom();
                        game.getBroomOnGround().add(broom);
                        RenderableHolder.getInstance().add(broom);
                    }

                    // spaw slime every 7 second
                    if(game.getGameTimer()%8 == 0){
                        game.getNewSlime();
                    }

                    // set clock timer coolDown
                    clock.setTimer(clock.getTimer()-1);

                    // decrease slime attack coolDown
                    for(Slime slime: game.getSlimeList()) {
                        slime.setAttackCooldown(slime.getAttackCooldown() - 1);
                        slime.attack();
                    }

                    // decrease veggie water & add growth point
                    for(BaseVeggies veggie : game.getVeggiesList()) {
                        if (game.getClock().getWeather() == Config.Weather.RAINY) {
                            veggie.setWaterPoint(veggie.getMAXWATER());
                        } else {
                            veggie.setWaterPoint(veggie.getWaterPoint() - veggie.getWaterDroppingRate());
                        }
                        veggie.setGrowthPoint(veggie.getGrowthPoint() + veggie.getGrowthRate());
                    }

                    // check if witch collect all veggie
                    if(game.getRainbowDrakeCount() == maxRainbowDrake &&
                            game.getRedFlowerCount() == maxRedFlower &&
                            game.getDaffodilCount() == maxDaffodil){
                        game.setGameover(true);
                    }
                    // check if gameTimer == 0
                    game.setGameTimer(game.getGameTimer()-1);
                    if(game.getGameTimer() == 0){
                        game.setGameover(true);
                    }

                    System.out.println("TIMER : "+ game.getGameTimer());
                }
            }
        });

        // Thread for Player's action
        Thread playerAction = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!game.isGameover()){
                    try {
                        Thread.sleep(20);
                        game.getPlayer().action();
                        game.getPlayer().setAttackCooldown(game.getPlayer().getAttackCooldown() - 20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        // Thread for slime walk
        Thread slimeWalk = new Thread(()->{
            while (!game.isGameover()) {
                try {
                    Thread.sleep(100);
                    for (Slime slime : game.getSlimeList()) {
                        slime.walk();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        // clear stat
        timer.start();
        playerAction.start();
        slimeWalk.start();
    }

    public boolean isPositionAccesible(double x, double y, double width, double height, boolean isPlayer) {
        for (Tree tree : trees) {
            if (tree.collideWith(x, y, width, height)) return false;
        }
        for (Bush bush : bushes) {
            if (bush.collideWith(x, y, width, height)) return false;
        }
        if (!isPlayer && player.collideWith(x,y,width,height)) return false;
        return !house.collideWith(x, y, width, height);
    }

    public int getLevel() { return level; }

    public int getMaxGameTimer() { return maxGameTimer; }

    public int getmaxRedFlower() { return maxRedFlower; }

    public int getmaxRainbowDrake() { return maxRainbowDrake; }

    public int getmaxDaffodil() { return maxDaffodil; }

    public ArrayList<Slime> getSlimeList() {
        return slimeList;
    }

    public void setSlimeList(ArrayList<Slime> slimeList) {
        this.slimeList = slimeList;
    }

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

    public SunnyBackground getSunnyBackground() {
        return sunnyBackground;
    }

    public SnowyBackground getSnowyBackground() {
        return snowyBackground;
    }

    public RainyBackground getRainyBackground() {
        return rainyBackground;
    }

    public int getRedFlowerCount() {
        return redFlowerCount;
    }

    public void setRedFlowerCount(int redFlowerCount) {
        this.redFlowerCount = Math.min(redFlowerCount, maxRedFlower);
    }

    public int getRainbowDrakeCount() {
        return rainbowDrakeCount;
    }

    public void setRainbowDrakeCount(int rainbowDrakeCount) {
        this.rainbowDrakeCount = Math.min(rainbowDrakeCount, maxRainbowDrake);
    }

    public int getDaffodilCount() {
        return daffodilCount;
    }

    public void setDaffodilCount(int daffodilCount) {
        this.daffodilCount = Math.min(daffodilCount, maxDaffodil);
    }
}
