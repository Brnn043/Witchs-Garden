package Games;

public class Config {
    public enum Weather{
        SUNNY, SNOWY, RAINY
    }

    public static int gameFrameWidth = 800;
    public static int gameFrameHeight = 500;
    public static int SLIMEMAXSPEEDRATE = 12;
    public static int SLIMEMAXDAMAGERANGE = 4;
    public static int SLIMEMAXDAMAGE = 5;
    public static float SLIMEWALKSTEP = 2;

    public static final int PLAYERCOOLDOWNTIME = 1;
    public static final int STICKMAXDURABILITY = 10;
    public static final int STICKMINDURABILITY = 5;
    public static final int STICKMAXATTACKRANGE = 10;
    public static final int STICKMINATTACKRANGE = 2;
    public static final int STICKDURABILITYPERATTACK = 1;
    public static final int STICKDAMAGEPERATTACK = 3;
    public static final float VEGGIESMAXGROWTHPOINT = 25;
    public static final int CLOCKCOOLDOWNTIME = 15;

}
