package se.mt.util;

import cn.hutool.core.thread.ThreadUtil;
import com.pivovarit.function.ThrowingRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 同步工具，其他线程完成指定内容后通知当前线程。
 * java.util.concurrent.CountDownLatch#countDown() 方法执行后，后续内容会继续执行。
 */
@Slf4j
public class CountDownLatchTest {

    @Test
    public void demo1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
            TimeUnit.SECONDS.sleep(1);
            log.info("加载地图资源");
            countDownLatch.countDown();
        }));
        ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
            TimeUnit.SECONDS.sleep(2);
            log.info("加载人物资源");
            countDownLatch.countDown();
        }));
        ThreadUtil.execAsync(ThrowingRunnable.unchecked(()->{
            TimeUnit.SECONDS.sleep(3);
            log.info("加载场景资源");
            countDownLatch.countDown();
        }));
        countDownLatch.await();
        log.info("资源加载完成");
    }

}
