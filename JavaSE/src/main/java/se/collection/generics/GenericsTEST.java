package se.collection.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/7/11 09:29
 */
public class GenericsTEST {

    class Food {
    }

    class Fruit extends Food {
    }

    class Apple extends Fruit {
    }

    class Banner extends Fruit {
    }

    @Test
    public void demo1() {
        Apple[] as = new Apple[10];
        Fruit[] fs = as; // fs 的本质还是 Apple 数组
        fs[0] = new Banner(); // Apple数组无法存储 Banner
    }

    @Test
    public void demo2() {
        Fruit[] fs = new Fruit[10];
        fs[0] = new Banner();
    }

    @Test
    public void demo3() {
        demo3Method(new ArrayList<Apple>() {{
            add(new Apple());
        }});
//        demo3Method(new ArrayList<Food>(){{
//            add(new Food());
//        }});
    }

    private void demo3Method(List<? extends Fruit> lf) {
//        lf.add(new Apple());
//        lf.add(new Fruit());
//        lf.add(new Banner());
//        lf.add(new Food());
        final Fruit fruit = lf.get(0);
        System.out.println(fruit
                .getClass()
                .getName());
    }

    @Test
    public void demo4() {
        demo4Method(new ArrayList<Fruit>() {{
            add(new Fruit());
        }});
        demo4Method(new ArrayList<Food>() {{
            add(new Food());
        }});
//        demo4Method(new ArrayList<Apple>(){{
//            add(new Apple());
//        }});
    }

    private void demo4Method(List<? super Fruit> lf) {
//        lf.add(new Apple());
//        lf.add(new Fruit());
//        lf.add(new Banner());
//        lf.add(new Food());
        final Object object = lf.get(0);
        System.out.println(object.getClass().getName());
    }

}
