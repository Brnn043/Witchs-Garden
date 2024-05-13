package Game;

import GUI.GameBackground.RainyBackground;
import GUI.GameBackground.SnowyBackground;
import GUI.GameBackground.SunnyBackground;
import GUI.Map.BackgroundImage;
import GUI.Map.Bush;
import GUI.Map.House;
import GUI.Map.Tree;
import GUISharedObject.Entity;
import GUISharedObject.InputUtility;
import GUISharedObject.RenderableHolder;
import Item.Character.*;
import Item.Inventory.Broom;
import Item.Inventory.Clock;
import Item.Veggie.BaseVeggie;
import Item.Veggie.RedFlower;
import Item.Veggie.RainbowDrake;
import Item.Veggie.Daffodil;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private Player player;
    private ArrayList<Slime> slimeList;
    private Clock clock;
    private ArrayList<BaseVeggie> veggiesList;
    private ArrayList<Broom> broomOnGround;
    private boolean isGameOver;
    private int gameTimer;
    private int maxGameTimer;
    private final BackgroundImage backgroundImage;
    private final House house;
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
    private int level;

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

        bushes.add(new Bush(65, 410, 85, 45, 21, 3));
        bushes.add(new Bush(95, 530, 110, 60, 26, 1));

        //right side
        trees.add(new Tree(1050, 75, 90, 110, 20, 2));
        trees.add(new Tree(1075, 200, 150, 180, 22, 1));
        trees.add(new Tree(1029.5, 322, 99, 144, 24, 3));
        trees.add(new Tree(940, 420, 105, 160, 28, 5));
        trees.add(new Tree(1050,425,130,170,29,3));

        bushes.add(new Bush(999.5, 160, 95, 60, 21, 1));
        bushes.add(new Bush(994.5, 255, 95, 50, 23, 2));
        bushes.add(new Bush(1090, 320, 95, 60, 23, 4));
        bushes.add(new Bush(895, 515, 100, 60, 34, 4));
        bushes.add(new Bush(1060, 525, 100, 60, 36, 3));
        bushes.add(new Bush(1000, 525, 80, 50, 36, 1));

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
            } else {
                getInstance().getPlayer().getBroom().weatherEffected();
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
            try {
                BaseVeggie veggie = getInstance().getVeggiesList().get(i);
                if (veggie.getWaterPoint() <= 0 || veggie.getHp() <= 0) {
                    deleteVeggie(veggie);
                } else {
                    i++;
                }
                veggie.weatherEffected();
            } catch (Exception ignored) {}
        }


        for (Broom broom : GameController.getInstance().broomOnGround) {
            broom.collected();
        }
    }

    public void initGames() {
        for (int i = 0; i < level + 2; i++) {
            getNewVeggie();
        }
    }

    public void getNewVeggie() {
        // Check if the player has already collected the maximum number of each veggie type
        boolean isRedFlowerMaxed = getRedFlowerCount() >= getMaxRedFlower();
        boolean isRainbowDrakeMaxed = getRainbowDrakeCount() >= getMaxRainbowDrake();
        boolean isDaffodilMaxed = getDaffodilCount() >= getMaxDaffodil();

        // Randomly choose the type of veggie to spawn
        int veggieType;
        do {
            veggieType = (int) (Math.random() * 3);
        } while ((veggieType == 0 && isRedFlowerMaxed) || (veggieType == 1 && isRainbowDrakeMaxed) || (veggieType == 2 && isDaffodilMaxed));

        BaseVeggie veggie = switch (veggieType) {
            case 0 -> new RedFlower();
            case 1 -> new RainbowDrake();
            default -> new Daffodil();
        };
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
            slime = new HitHardSlime();
        } else {
            slime = new SpeedSlime();
        }
        getInstance().getSlimeList().add(slime);
        RenderableHolder.getInstance().add(slime);
    }

    public void collectVeggie(BaseVeggie veggie){
        if(veggie instanceof RainbowDrake){
            setRainbowDrakeCount(getRainbowDrakeCount() + 1);
        }
        if(veggie instanceof RedFlower){
            setRedFlowerCount(getRedFlowerCount() + 1);
        }
        if(veggie instanceof Daffodil){
            setDaffodilCount(getDaffodilCount() + 1);
        }
        deleteVeggie(veggie);
    }

    public static void deleteVeggie(BaseVeggie veggie){
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
        ArrayList<Entity> delEntities = new ArrayList<>();
        for(Entity entity: RenderableHolder.getInstance().getEntities()){
            if(entity instanceof BaseVeggie || entity instanceof Slime || entity instanceof Broom || entity instanceof Player){
                delEntities.add(entity);
            }
        }
        for(Entity delEntity: delEntities){
            RenderableHolder.getInstance().getEntities().remove(delEntity);
        }

        // set stat
        this.player = new Player(400, 300, 5, 0, 0);
        RenderableHolder.getInstance().add(player);
        veggiesList = new ArrayList<>();
        slimeList = new ArrayList<>();
        clock = new Clock();
        isGameOver = false;
        maxGameTimer = Config.GAMETIMER * level /2;
        gameTimer = maxGameTimer;
        broomOnGround = new ArrayList<>();

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
        Thread timer = new Thread(() -> {
            while (!game.isGameOver()){
                Clock clock = game.getClock();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // spawn broom every 15 second
                if(game.getGameTimer() % Config.BROOMSPAWNTIME == 0){
                    Broom broom = new Broom();
                    game.getBroomOnGround().add(broom);
                    RenderableHolder.getInstance().add(broom);
                }

                // spawn slime every 3 second
                if(game.getGameTimer() % Config.SLIMESPAWNTIME == 0 && game.getSlimeList().size() <= 2 * getLevel() + 3){
                    game.getNewSlime();
                }

                // set clock timer coolDown
                clock.setTimer(clock.getTimer() - 1);

                // decrease slime attack coolDown
                for(Slime slime: game.getSlimeList()) {
                    slime.setAttackCoolDown(slime.getAttackCoolDown() - 1);
                    slime.attack();
                }

                // decrease veggie water & add growth point
                for(BaseVeggie veggie : game.getVeggiesList()) {
                    if (game.getClock().getWeather() == Config.Weather.RAINY) {
                        veggie.setWaterPoint(veggie.getMaxWater());
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
                game.setGameTimer(game.getGameTimer() - 1);
                if(game.getGameTimer() == 0){
                    game.setGameover(true);
                }

                System.out.println("TIMER : " + game.getGameTimer());
            }
        });

        // Thread for Player's action
        Thread playerAction = new Thread(() -> {
            while (!game.isGameOver()){
                try {
                    Thread.sleep(20);
                    game.getPlayer().action();
                    game.getPlayer().setAttackCoolDown(game.getPlayer().getAttackCoolDown() - 20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Thread for slime walk
        Thread slimeWalk = new Thread(()->{
            while (!game.isGameOver()) {
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

    public int getMaxRedFlower() { return maxRedFlower; }

    public int getMaxRainbowDrake() { return maxRainbowDrake; }

    public int getMaxDaffodil() { return maxDaffodil; }

    public ArrayList<Slime> getSlimeList() { return slimeList; }

    public Player getPlayer() { return player; }

    public Clock getClock() { return clock; }

    public ArrayList<BaseVeggie> getVeggiesList() { return veggiesList; }

    public ArrayList<Broom> getBroomOnGround() { return broomOnGround; }

    public void setGameover(boolean isGameOver) { this.isGameOver = isGameOver; }

    public boolean isGameOver() { return isGameOver; }

    public int getGameTimer() { return gameTimer; }

    public void setGameTimer(int gameTimer) { this.gameTimer = Math.max(0, gameTimer); }

    public House getHouse() { return house; }

    public BackgroundImage getBackgroundImage() { return backgroundImage; }

    public SunnyBackground getSunnyBackground() { return sunnyBackground; }

    public SnowyBackground getSnowyBackground() { return snowyBackground; }

    public RainyBackground getRainyBackground() { return rainyBackground; }

    public int getRedFlowerCount() { return redFlowerCount; }

    public void setRedFlowerCount(int redFlowerCount) { this.redFlowerCount = Math.min(redFlowerCount, maxRedFlower); }

    public int getRainbowDrakeCount() { return rainbowDrakeCount; }

    public void setRainbowDrakeCount(int rainbowDrakeCount) { this.rainbowDrakeCount = Math.min(rainbowDrakeCount, maxRainbowDrake); }

    public int getDaffodilCount() { return daffodilCount; }

    public void setDaffodilCount(int daffodilCount) { this.daffodilCount = Math.min(daffodilCount, maxDaffodil); }
}
