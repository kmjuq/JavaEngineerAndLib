package design.pattern.create.factory.bstrct;


import design.pattern.create.factory.common.ProductA;
import design.pattern.create.factory.common.ProductA2;
import design.pattern.create.factory.common.ProductB;
import design.pattern.create.factory.common.ProductB2;

public class AbstractMethodFactory2 implements AbstractMethodFactory {
    @Override
    public ProductA createA() {
        return new ProductA2();
    }

    @Override
    public ProductB createB() {
        return new ProductB2();
    }
}
