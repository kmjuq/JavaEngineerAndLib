package se.lang.array;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import zzz.Node;

import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/6/25 18:00
 */
public class ArrayTest {

    @Test
    public void demo1() {
        Object[] objs = new Object[5];
        System.out.println(objs.length);
    }

    @Test
    public void demo2() {
        System.out.println(Object.class);
        System.out.println(Object[].class);
    }

    @Test
    public void demo3() {
        ArrayList<Node> list = new ArrayList<>();
        while (true) {
            list.add(Node.of(RandomUtil.randomInt()));
        }
    }

}
