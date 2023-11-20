package design.pattern.behave.state;

public class App {

    public static void main(String[] args) {
        Context context = new Context();
        NewState newState = new NewState(context);
        context.setState(newState);

        context.handle();
        context.setFlag(true);
        context.handle();
        context.handle();
    }

}
