package design.pattern.behave.memento;


public class App {

    public static void main(String[] args) {
        Originator originator = new Originator("1","1");
        originator.saveMemento();
        originator.setX("2").setY("2");
        originator.saveMemento();
        originator.setX("3").setY("3");
        originator.saveMemento();

        originator.render();
        originator.restoreMemento();
        originator.render();
        originator.restoreMemento();
        originator.render();
        originator.restoreMemento();
        originator.render();
    }

}
