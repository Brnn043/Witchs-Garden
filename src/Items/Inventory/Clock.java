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
        if(this.getWeather() == weather) {
            System.out.println("Cannot change into the same season!!");
            return false;
        }
        if(this.getTimer()<=0) {
            this.setWeather(weather);
            this.setTimer(Config.CLOCKCOOLDOWNTIME);
            return true;
        } else {
            System.out.println("Cooling down clock . . .");
            return false;
        }
    }

    public Config.Weather getWeather() { return weather; }

    public void setWeather(Config.Weather weather) { this.weather = weather; }

    public int getTimer() { return timer; }

    public void setTimer(int timer) { this.timer = Math.max(0, timer); }
}
