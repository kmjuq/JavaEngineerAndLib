package design.pattern.behave.command.base;

public class App {

    public static void main(String[] args) {
        ConcreteCommand concreteCommand = new ConcreteCommand();
        concreteCommand.setReceiver(new Receiver());
        Invoker invoker = new Invoker(concreteCommand);
        invoker.call();
    }

}
