package se.collection;

import org.junit.jupiter.api.Test;
import zzz.Node;

import java.util.HashSet;

/**
 * <p>
 * 用来测试 Set 哈希表判断相等的条件
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/6/20 09:56
 */
public class SetObjectEquals {

    @Test
    public void demo1() {
        final Node a = new Node(1);
        final Node b = new Node(1);
        final HashSet<Node> set = new HashSet<>();
        set.add(a);
        set.add(b);
        /**
         * 如果只重写equals方法，set的容器里面有两个对象
         * 如果equals和hashcode方法都重写了，set的容器里面则是1个对象
         */
        System.out.println(set.size());
    }
}
