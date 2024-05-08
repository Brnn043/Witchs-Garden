package Items.Character;

import Games.Config;

public class TeleportSlime extends Slime{
    public TeleportSlime() {
        super((int) ((float) Math.max(Config.SLIMEMINSPEEDRATE, (Math.random())*Config.SLIMEMAXSPEEDRATE-5)));
    }
}
