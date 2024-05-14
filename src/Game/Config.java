package Game;

// constant value used in this game
public class Config {
    public enum Weather {
        SUNNY, SNOWY, RAINY
    }

    public enum WalkState {
        STAY, FRONT, RIGHT, LEFT
    }

    public static final int GAMEFRAMEWIDTH = 1100;
    public static final int GAMEFRAMEHEIGHT = 650;
    public static final int GAMESCREENWIDTH = 1100;
    public static final int GAMESCREENHEIGHT = 535;
    public static final int GAMELABELWIDTH = 1100;
    public static final int GAMELABELHEIGHT = 115;
    public static final int WIDTHPERROW = 20;
    public static final int HEIGHTPERROW = 20;
    public static final int GAMETIMER = 120;
    public static final int PLAYERCOLLECTRANGE = 70;
    public static final double PLAYERWIDTH = 90;
    public static final double PLAYERHEIGHT = 128.6;
    public static final double SLIMEWIDTH = 38;
    public static final double SLIMEHEIGHT = 34;
    public static final int SLIMEMAXSPEEDRATE = 20;
    public static final int SLIMEMINSPEEDRATE = 15;
    public static final int SLIMEMAXDAMAGERANGE = 30;
    public static final float SLIMEWALKSTEP = 2;
    public static final int SLIMEATTACKCOOLDOWN = 1;
    public static final int SLIMESPAWNTIME = 5;
    public static final int PLAYERCOOLDOWNTIME = 1000; // 1000 ms
    public static final double BROOMWIDTH = 90;
    public static final double BROOMHEIGHT = 45;
    public static final int BROOMMAXDURABILITY = 20;
    public static final int BROOMMINDURABILITY = 10;
    public static final int BROOMMAXATTACKRANGE = 100;
    public static final int BROOMMINATTACKRANGE = 60;
    public static final float BROOMDURABILITYPERATTACK = 1.5F;
    public static final float BROOMDAMAGEPERATTACK = 3;
    public static final int BROOMSPAWNTIME = 15;
    public static final int CLOCKCOOLDOWNTIME = 5;
    public static final double SPAWNLEFTBOUND = 80;
    public static final double SPAWNRIGHTBOUND = 980;
    public static final double SPAWNTOPBOUND = 50;
    public static final double SPAWNBOTTOMBOUND = GAMESCREENHEIGHT - 50;
    public static final double VEGGIESIZE = 40;

}
