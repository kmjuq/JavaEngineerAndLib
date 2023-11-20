package design.pattern.structure.bridge.simple;

public class Jacket extends Cloth {
    @Override
    public void make() {
        System.out.println("jacket make");
    }

    @Override
    public void price() {
        System.out.println("jacket price");
    }
}
