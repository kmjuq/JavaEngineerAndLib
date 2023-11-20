package se.mt.util;

import cn.hutool.core.thread.ThreadUtil;
import com.pivovarit.function.ThrowingRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore
 */
@Slf4j
public class SemaphoreTest {

    @Test
    public void SemaphoreTest() {
        Semaphore semaphore = new Semaphore(2);
        final int[] resources = {10};

        for (int i = 0; i < 10; i++) {
            new Thread(ThrowingRunnable.unchecked(() -> {
                if(resources[0]<0){ return; }
                semaphore.acquire();
                int num = resources[0];
                ThreadUtil.sleep(500,TimeUnit.MILLISECONDS);
                resources[0] = num - 1;
                log.info("当前资源数目为{}",resources[0]);
                ThreadUtil.sleep(1,TimeUnit.SECONDS);
                semaphore.release();
            })).start();
        }
        ThreadUtil.sleep(10, TimeUnit.SECONDS);
    }


}
