package design.pattern.structure.bridge.simple;

public class Shirt extends Cloth {
    @Override
    public void make() {
        System.out.println("shirt make");
    }

    @Override
    public void price() {
        System.out.println("shirt price");
    }
}
