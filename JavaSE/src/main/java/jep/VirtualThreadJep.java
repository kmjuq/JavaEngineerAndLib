package jep;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class VirtualThreadJep {

    private AtomicInteger count = new AtomicInteger(0);

    @Test
    public void demo1() throws InterruptedException {
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
        log.info("时间花费 毫秒{}", stopWatch.getLastTaskInfo().getTimeMillis());
        log.info("总计数为{}", count.get());

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                Thread.ofVirtual().start(() -> log.info("count {}",finalI));
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

}
