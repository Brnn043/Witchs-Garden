package Items.Character;

import Games.Config;

public class HitHardSlime extends Slime{
    public HitHardSlime() {
//        super((int) ((float) Math.max(Config.SLIMEMINSPEEDRATE, (Math.random())*Config.SLIMEMAXSPEEDRATE-5)));
        super(Config.SLIMEMINSPEEDRATE, 12, 5);
    }
}
