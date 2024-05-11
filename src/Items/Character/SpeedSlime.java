package Items.Character;

import Games.Config;

public class SpeedSlime extends Slime{
    public SpeedSlime() {
//        super((int) ((float) Math.max(Config.SLIMEMINSPEEDRATE+5, (Math.random())*Config.SLIMEMAXSPEEDRATE)));
        super(Config.SLIMEMINSPEEDRATE + 10);
    }
}
