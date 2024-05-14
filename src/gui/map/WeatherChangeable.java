package gui.map;

import game.Config;

// this interface is apply to backgroundEntities that is depended on weather
public interface WeatherChangeable {
    void changeWeather(Config.Weather weather);
}
