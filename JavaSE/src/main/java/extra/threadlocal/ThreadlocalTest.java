package extra.threadlocal;

import com.alibaba.ttl.threadpool.TtlForkJoinPoolHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

@Slf4j
public class ThreadlocalTest {

    @Test
    public void demo1() throws InterruptedException {
        IntStream.range(1,100).parallel().forEach(i1 -> {
            log.info("{}",Thread.currentThread().getName());
        });

        ForkJoinPool.ForkJoinWorkerThreadFactory d = TtlForkJoinPoolHelper.getDefaultDisableInheritableForkJoinWorkerThreadFactory();
        ForkJoinPool forkJoinPool = new ForkJoinPool(3, d, null, false);
        IntStream.range(1,100).parallel().forEach(i1 -> {
            forkJoinPool.execute(() -> {
                log.info("{}",Thread.currentThread().getName());
            });
        });

    }

}
