package design.pattern.create.factory.bstrct;

import design.pattern.create.factory.common.ProductA;
import design.pattern.create.factory.common.ProductB;

public interface AbstractMethodFactory {

    ProductA createA();

    ProductB createB();

}