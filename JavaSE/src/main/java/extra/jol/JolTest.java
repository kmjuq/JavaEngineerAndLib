package extra.jol;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import se.optional.Fruit;
import zzz.Node;

import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/8/28 13:29
 */
public class JolTest {

    @Test
    public void demo1() {
        final Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        final Node node = Node.of(1);
        System.out.println(ClassLayout.parseInstance(node).toPrintable());
        final Fruit fruit = new Fruit();
        fruit.setAmount(Long.MAX_VALUE);
        Stream.generate(RandomUtil::randomChar).limit(Long.MAX_VALUE);
        fruit.setName("");
        fruit.setType(Stream.generate(RandomUtil::randomChar).limit(Long.MAX_VALUE).toString());
        System.out.println(fruit);
        System.out.println(ClassLayout.parseInstance(fruit).toPrintable());
    }

}
