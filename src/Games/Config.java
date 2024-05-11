package Games;

public class Config {
    public enum Weather{
        SUNNY, SNOWY, RAINY
    }

    public static int GAMEFRAMEWIDTH = 1100;

    public static int GAMEFRAMEHEIGHT = 650;
    public static int GAMESCREENWIDTH = 1100;
    public static int GAMESCREENHEIGHT = 535;
    public static int GAMELABELWIDTH = 1100;
    public static int GAMELABELHEIGHT = 115;
    public static int WIDTHPERROW = 20;
    public static int HEIGHTPERROW = 20;
    public static int GAMETIMER = 60;
    public static int SLIMEMAXSPEEDRATE = 20;
    public static int SLIMEMINSPEEDRATE = 15;
    public static int SLIMEMAXDAMAGERANGE = 30;
    public static int SLIMEMAXDAMAGE = 5;
    public static float SLIMEWALKSTEP = 2;

    public static int SLIMEATTACKCOOLDOWN = 1;

    public static int SLIMESPAWNTIME = 3;
    public static final int PLAYERCOOLDOWNTIME = 1000; // 1000 ms
    public static final int BROOMMAXDURABILITY = 20;
    public static final int BROOMMINDURABILITY = 10;
    public static final int BROOMMAXATTACKRANGE = 100;
    public static final int BROOMMINATTACKRANGE = 60;
    public static final int BROOMDURABILITYPERATTACK = 1;
    public static final float BROOMDAMAGEPERATTACK = 3;
    public static final int BROOMSPAWNTIME = 15;
    public static final float VEGGIESMAXGROWTHPOINT = 25;
    public static final int CLOCKCOOLDOWNTIME = 5;

}
