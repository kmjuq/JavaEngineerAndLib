package design.pattern.behave.memento.base;

import org.junit.jupiter.api.Test;

public class App {

    @Test
    public void standard() {
        Originator originator = new Originator("state 1");
        System.out.println(originator.getState());

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.setState("state 2");
        System.out.println(originator.getState());

        originator.restoreMemento(caretaker.getMemento());
        System.out.println(originator.getState());
    }

}
