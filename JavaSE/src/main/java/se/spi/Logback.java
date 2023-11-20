package se.spi;

public class Logback implements ILog{
    @Override
    public void log(String content) {
        System.out.println("logback log");
    }
}
