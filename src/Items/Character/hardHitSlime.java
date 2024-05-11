package Items.Character;

import Games.Config;

public class hardHitSlime extends Slime{
    public hardHitSlime() {
//        super((int) ((float) Math.max(Config.SLIMEMINSPEEDRATE, (Math.random())*Config.SLIMEMAXSPEEDRATE-5)));
        super(Config.SLIMEMINSPEEDRATE);
    }
}
