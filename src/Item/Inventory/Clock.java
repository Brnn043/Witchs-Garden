package Item.Inventory;

import Game.Config;

// this is clock which controls the weather of game
public class Clock {
    private Config.Weather weather;
    private int timer;

    public Clock() {
        this.setTimer(Config.CLOCKCOOLDOWNTIME);
        this.weather = Config.Weather.SUNNY;
        // by default , weather is summer.
    }

    // return boolean if weather has already been changed
    public boolean changeSeason(Config.Weather weather) {
        if (getTimer() > 0 || this.getWeather() == weather) {
            return false;
        }
        this.weather = weather;
        setTimer(Config.CLOCKCOOLDOWNTIME);
        return true;
    }

    public Config.Weather getWeather() { return weather; }

    public int getTimer() { return timer; }

    public void setTimer(int timer) { this.timer = Math.max(0, timer); }
}
