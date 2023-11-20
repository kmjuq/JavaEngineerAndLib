package design.pattern.create.factory.simple;

import design.pattern.create.factory.common.ProductA;
import design.pattern.create.factory.common.ProductA1;
import design.pattern.create.factory.common.ProductA2;

public class SimpleFactory {

    public ProductA createProduct(String words) {
        switch (words) {
            case "1":
                return new ProductA1();
            case "2":
                return new ProductA2();
            default:
                return new ProductA();
        }
    }

}
