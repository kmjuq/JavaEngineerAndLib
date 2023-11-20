package design.pattern.behave.observe.simple;

public class App {

    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.addObserver(new Hobbits());
        weather.addObserver(new Orcs());

        weather.changeWeather(WeatherType.COLD);
    }

}
