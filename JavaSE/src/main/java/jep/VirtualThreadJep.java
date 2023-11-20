package jep;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class VirtualThreadJep {

    private AtomicInteger count  = new AtomicInteger(0);

    @Test
    public void demo1(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    count.incrementAndGet();
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        stopWatch.stop();
        log.info("时间花费 毫秒{}",stopWatch.getLastTaskInfo().getTimeMillis());
        log.info("总计数为{}",count.get());
    }

}
