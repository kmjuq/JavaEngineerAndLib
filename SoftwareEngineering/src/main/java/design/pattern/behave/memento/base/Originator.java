package design.pattern.behave.memento.base;

public class Originator {

    private String state;

    public Originator(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(this);
    }

    public void restoreMemento(Memento m) {
        state = m.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
