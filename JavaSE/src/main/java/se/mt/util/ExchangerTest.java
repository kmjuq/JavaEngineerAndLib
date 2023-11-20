package se.mt.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.pivovarit.function.ThrowingRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * exchanger 用于两个线程之前进行同步，并交换数据。
 */
@Slf4j
public class ExchangerTest {

    @Test
    public void demo(){
        Exchanger<String> stringExchanger = new Exchanger<>();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        ses.scheduleAtFixedRate(ThrowingRunnable.unchecked(()->{
            String str = RandomUtil.randomString(10);
            log.info("{}",stringExchanger.exchange(str));
        }),1,1,TimeUnit.SECONDS);
        ses.scheduleAtFixedRate(ThrowingRunnable.unchecked(()->{
            String str = RandomUtil.randomString(10);
            log.info("{}",stringExchanger.exchange(str));
        }),1,1,TimeUnit.SECONDS);
        ThreadUtil.sleep(10,TimeUnit.SECONDS);
    }



}
