package design.pattern.create.factory.method;


import design.pattern.create.factory.common.ProductA;
import design.pattern.create.factory.common.ProductA1;

public class MethodFactoryA1 implements MethodFactory {
    @Override
    public ProductA create() {
        return new ProductA1();
    }
}
