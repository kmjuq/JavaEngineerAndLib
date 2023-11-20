package design.pattern.structure.bridge.simple;

public abstract class Company {

    private final Product product;

    public Company(Product product) {
        this.product = product;
    }

    void sell() {
        this.product.make();
        this.product.price();
    }
}
