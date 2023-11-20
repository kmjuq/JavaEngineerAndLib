package design.pattern.structure.bridge.simple;

public class ClothCompany extends Company {

    public ClothCompany(Product product) {
        super(product);
    }

    @Override
    void sell() {
        System.out.println("cloth company");
        super.sell();
    }

}
