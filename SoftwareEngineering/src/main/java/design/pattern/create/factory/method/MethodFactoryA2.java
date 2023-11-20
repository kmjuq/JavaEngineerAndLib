package design.pattern.create.factory.method;


import design.pattern.create.factory.common.ProductA;
import design.pattern.create.factory.common.ProductA2;

public class MethodFactoryA2 implements MethodFactory {
    @Override
    public ProductA create() {
        return new ProductA2();
    }
}
