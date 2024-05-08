package Items.Inventory;

import Games.Config;

public class Clock {
    private Config.Weather weather;
    private int timer;

    public Clock() {
        this.setTimer(Config.CLOCKCOOLDOWNTIME);
        this.setWeather(Config.Weather.SUNNY);
        // by default , weather is summer.
    }

    // return boolean if weather has already been changed
    public boolean changeSeason(Config.Weather weather) {
        if (getTimer()>0) {
            System.out.println("Cooling down clock . . .");
            return false;
        }
        if(this.getWeather() == weather) {
            System.out.println("Cannot change into the same season!!");
            return false;
        }
        setWeather(weather);
        setTimer(Config.CLOCKCOOLDOWNTIME);
        return true;
    }

    public Config.Weather getWeather() { return weather; }

    private void setWeather(Config.Weather weather) { this.weather = weather; }

    public int getTimer() { return timer; }

    public void setTimer(int timer) { this.timer = Math.max(0, timer); }
}
