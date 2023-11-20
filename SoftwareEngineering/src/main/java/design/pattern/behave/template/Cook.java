package design.pattern.behave.template;

public class Cook {

    private final AbstractCook cook;

    public Cook(AbstractCook cook) {
        this.cook = cook;
    }

    void cook() {
        cook.wash();
        cook.cook();
        cook.dishUp();
    }

}
