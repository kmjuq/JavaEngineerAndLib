package se.collection;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/9/7 19:48
 */
@Slf4j
public class HashMapTest {

    @Test
    public void demo1() {
        final HashMap<Object, Object> map = new HashMap<>(16, 0.75f);
        for (int i = 0; i < 20; i++) {
            map.put(RandomUtil.randomInt(100, 10000), "TEST");
        }
    }

    @Test
    public void demo2() {
        log.info("{}", Integer.numberOfLeadingZeros(17 - 1));
        log.info("{}", -1 >>> Integer.numberOfLeadingZeros(17 - 1));
    }

    @Test
    public void demo3() {
        HashMap<String, String> strmaps = new HashMap<>();
        strmaps.putIfAbsent("321", "111");
        System.out.println(strmaps.putIfAbsent("321", "456"));
        System.out.println(strmaps);

        System.out.println(strmaps.computeIfAbsent("456", str -> str + "321"));
        System.out.println(strmaps.computeIfAbsent("456", str -> str + "123"));
        System.out.println(strmaps.computeIfPresent("456", (key, oldValue) -> key + "-" + oldValue));
        System.out.println(strmaps);
    }

}
