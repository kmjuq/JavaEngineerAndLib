package design.pattern.behave.command.base;

public class Invoker {

    private final Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void call() {
        command.excute();
    }

}
