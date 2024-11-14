package design.pattern.behave.observe.simple;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hobbits implements WeatherObserver {

    @Override
    public void update(WeatherType currentWeather) {
        log.info("The hobbits are facing {} weather now", currentWeather.getDescription());
    }
}