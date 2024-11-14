package jep;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class InstanceofJep {

    @Test
    public void demo1() {
        Object[] objs = {new NullPointerException(), new IllegalAccessException()};
        if (objs[RandomUtil.randomInt(0, 2)] instanceof NullPointerException e) {
            log.info(e.toString());
        }
    }


}
