package Games;

public class Config {
    public enum Weather {
        SUNNY, SNOWY, RAINY
    }

    public enum WalkState {
        STAY, FRONT, RIGHT, LEFT
    }

    public static int GAMEFRAMEWIDTH = 1100;
    public static int GAMEFRAMEHEIGHT = 650;
    public static int GAMESCREENWIDTH = 1100;
    public static int GAMESCREENHEIGHT = 535;
    public static int GAMELABELWIDTH = 1100;
    public static int GAMELABELHEIGHT = 115;
    public static int WIDTHPERROW = 20;
    public static int HEIGHTPERROW = 20;
    public static int GAMETIMER = 120;
    public static final int PLAYERCOLLECTRANGE = 70;
    public static final double PLAYERWIDTH = 90;
    public static final double PLAYERHEIGHT = 128.6;
    public static final double SLIMEWIDTH = 38;
    public static final double SLIMEHEIGHT = 34;
    public static int SLIMEMAXSPEEDRATE = 20;
    public static int SLIMEMINSPEEDRATE = 15;
    public static int SLIMEMAXDAMAGERANGE = 30;
    public static float SLIMEWALKSTEP = 2;
    public static int SLIMEATTACKCOOLDOWN = 1;
    public static int SLIMESPAWNTIME = 5;
    public static final int PLAYERCOOLDOWNTIME = 1000; // 1000 ms
    public static final int BROOMWIDTH = 90;
    public static final int BROOMHEIGHT = 45;
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

}
