package se.spi;

public class Log4j implements ILog {
    @Override
    public void log(String content) {
        System.out.println("log4j log");
    }
}