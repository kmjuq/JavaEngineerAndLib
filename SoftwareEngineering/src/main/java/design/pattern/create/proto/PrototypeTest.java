package design.pattern.create.proto;

import cn.hutool.core.date.StopWatch;

import java.io.*;
import java.util.ArrayList;

/**
 * <p>
 * 没啥卵用的原型模式，在java领域
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/10 01:03
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        final PrototypeObjectY prototypeObjectY = new PrototypeObjectY().setN1("n1").setN2("n2")
                .setN3("n3").setN4("n4").setN5("n5").setN6("n6").setN7("n7").setN8("n9")
                .setN10("n10").setN11("n11").setN12("12");
        final PrototypeObjectX prototypeObjectX = new PrototypeObjectX().setN1("n1").setN2("n2")
                .setN3("n3").setN4("n4").setN5("n5").setN6("n6").setN7("n7").setN8("n9")
                .setN10("n10").setN11("n11").setN12("12").setPrototypeObjectY(prototypeObjectY);


        final ArrayList<PrototypeObjectX> prototypeObjects = new ArrayList<>();
        final StopWatch stopwatch = new StopWatch("计数");
        stopwatch.start("原型对象(浅复制)创建");
        for (int i = 0; i < 1000000; i++) {
            final PrototypeObjectX clone = (PrototypeObjectX) prototypeObjectX.clone();
            clone.setN12("clone");
            prototypeObjects.add(clone);
        }
        stopwatch.stop();
        stopwatch.start("原型对象(深复制)创建");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        for (int i = 0; i < 100000; i++) {
            out.writeObject(prototypeObjectX);
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            PrototypeObjectX ret = (PrototypeObjectX) in.readObject();
            prototypeObjects.add(ret);
            in.close();
        }
        out.close();
        stopwatch.stop();
        stopwatch.start("NEW 对象创建");
        for (int i = 0; i < 1000000; i++) {
            PrototypeObjectY temp = new PrototypeObjectY().setN1("n1").setN2("n2")
                    .setN3("n3").setN4("n4").setN5("n5").setN6("n6").setN7("n7").setN8("n9")
                    .setN10("n10").setN11("n11").setN12("12");
            prototypeObjects.add(new PrototypeObjectX().setN1("n1").setN2("n2")
                    .setN3("n3").setN4("n4").setN5("n5").setN6("n6").setN7("n7").setN8("n9")
                    .setN10("n10").setN11("n11").setN12("clone").setPrototypeObjectY(temp));
        }
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
    }

}
