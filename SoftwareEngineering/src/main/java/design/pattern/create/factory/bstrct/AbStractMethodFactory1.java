package design.pattern.create.factory.bstrct;


import design.pattern.create.factory.common.ProductA;
import design.pattern.create.factory.common.ProductA1;
import design.pattern.create.factory.common.ProductB;
import design.pattern.create.factory.common.ProductB1;

public class AbStractMethodFactory1 implements AbstractMethodFactory {
    @Override
    public ProductA createA() {
        return new ProductA1();
    }

    @Override
    public ProductB createB() {
        return new ProductB1();
    }
}
