package design.pattern.behave.strategy;

public class Context {

    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    void excute() {
        this.strategy.strategy();
    }

}
