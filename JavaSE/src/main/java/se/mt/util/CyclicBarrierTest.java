package se.mt.util;

import cn.hutool.core.thread.ThreadUtil;
import com.pivovarit.function.ThrowingRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 控制各线程进度，
 * java.util.concurrent.CyclicBarrier#await() 方法执行后会阻塞线程，
 * 当指定线程数目全部 await 之后，各自线程才会继续执行
 */
@Slf4j
public class CyclicBarrierTest {

    @Test
    public void demo1() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
            ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
                TimeUnit.SECONDS.sleep(1);
                log.info("加载地图资源");
                cyclicBarrier.await();
            }));
            ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
                TimeUnit.SECONDS.sleep(2);
                log.info("加载人物资源");
                cyclicBarrier.await();
            }));
            ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
                TimeUnit.SECONDS.sleep(3);
                log.info("加载场景资源");
                cyclicBarrier.await();
            }));
            TimeUnit.SECONDS.sleep(5);
            log.info("资源加载完成");
        }
    }

    @Test
    public void demo2() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
                log.info("资源加载完成");
            });
            ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
                cyclicBarrier.await();
                TimeUnit.SECONDS.sleep(1);
                log.info("加载地图资源");
            }));
            ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
                cyclicBarrier.await();
                TimeUnit.SECONDS.sleep(2);
                log.info("加载人物资源");
            }));
            ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
                cyclicBarrier.await();
                TimeUnit.SECONDS.sleep(3);
                log.info("加载场景资源");
            }));
            TimeUnit.SECONDS.sleep(5);
        }
    }


}
