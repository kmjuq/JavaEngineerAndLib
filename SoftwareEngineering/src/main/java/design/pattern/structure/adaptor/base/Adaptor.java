package design.pattern.structure.adaptor.base;

public class Adaptor implements Target {

    private final Origin origin;

    public Adaptor(Origin origin) {
        this.origin = origin;
    }

    @Override
    public void execute() {
        origin.exec();
    }
}
