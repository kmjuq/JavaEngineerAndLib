package se.mt.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeUnitTest {

    @Test
    public void demo1() {
        // 将1分钟转换为秒，将1小时转换为秒
        log.info("{}", TimeUnit.SECONDS.convert(1L, TimeUnit.MINUTES));
        log.info("{}", TimeUnit.SECONDS.convert(1L, TimeUnit.HOURS));
    }

    @Test
    public void demo2() {
        // 将1分钟转换为秒，将1小时转换为秒
        log.info("{}", TimeUnit.SECONDS.convert(Duration.ofMinutes(1L)));
        log.info("{}", TimeUnit.SECONDS.convert(Duration.ofHours(1L)));
    }

}
