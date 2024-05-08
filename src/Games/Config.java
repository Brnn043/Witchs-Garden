package Games;

public class Config {
    public enum Weather{
        SUNNY, SNOWY, RAINY
    }


//    public static int GAMEFRAMEWIDTH = 640;
//    public static int GAMEFRAMEHEIGHT = 480;
    public static int GAMEFRAMEWIDTH = 750;
    public static int GAMEFRAMEHEIGHT = 412;
    public static int GAMETIMER = 30;
    public static float SLIMEMAXSPEEDRATE = (float) 0.12;
    public static int SLIMEMAXDAMAGERANGE = 4;
    public static int SLIMEMAXDAMAGE = 5;
    public static float SLIMEWALKSTEP = 2;

    public static final int PLAYERCOOLDOWNTIME = 1;
    public static final int BROOMMAXDURABILITY = 10;
    public static final int BROOMMINDURABILITY = 5;
    public static final int BROOMMAXATTACKRANGE = 300;
    public static final int BROOMMINATTACKRANGE = 100;
    public static final int BROOMDURABILITYPERATTACK = 1;
    public static final int BROOMDAMAGEPERATTACK = 3;
    public static final float VEGGIESMAXGROWTHPOINT = 25;
    public static final int CLOCKCOOLDOWNTIME = 15;

}
