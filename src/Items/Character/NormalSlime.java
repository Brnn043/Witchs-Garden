package Items.Character;

import Games.Config;

public class NormalSlime extends Slime{
    public NormalSlime() {
//        super((int) ((float) Math.max(Config.SLIMEMINSPEEDRATE+3, (Math.random())*Config.SLIMEMAXSPEEDRATE-3)));
        super(Config.SLIMEMINSPEEDRATE);
    }
}
