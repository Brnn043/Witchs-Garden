package Item.Inventory;

import Game.Config;

// this is clock which controls the weather of game
public class Clock {
    private Config.Weather weather;
    private int timer;

    public Clock() {
        this.setTimer(Config.CLOCKCOOLDOWNTIME);
        this.weather = Config.Weather.SUNNY;
        System.out.println("Weather is set to " + getWeather());
        // by default , weather is summer.
    }

    // return boolean if weather has already been changed
    public boolean changeSeason(Config.Weather weather) {
        if (getTimer() > 0) {
            System.out.println("Cooling down clock . . .");
            return false;
        }
        if (this.getWeather() == weather) {
            System.out.println("Cannot change into the same season!!");
            return false;
        }
        this.weather = weather;
        setTimer(Config.CLOCKCOOLDOWNTIME);
        System.out.println("Weather is changed to " + getWeather());
        return true;
    }

    public Config.Weather getWeather() { return weather; }

    public int getTimer() { return timer; }

    public void setTimer(int timer) { this.timer = Math.max(0, timer); }
}
