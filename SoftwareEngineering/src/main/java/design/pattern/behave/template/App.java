package design.pattern.behave.template;

public class App {

    public static void main(String[] args) {
        Cook cook = new Cook(new BoilCook());
        cook.cook();
    }

}
