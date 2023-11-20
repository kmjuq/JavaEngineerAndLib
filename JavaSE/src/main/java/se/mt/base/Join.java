package se.mt.base;

import com.pivovarit.function.ThrowingRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(ThrowingRunnable.unchecked(() -> {
            TimeUnit.SECONDS.sleep(2);
            log.info("thread");
        }));
        thread.start();
        thread.join();
        TimeUnit.SECONDS.sleep(1);
        log.info("main");
    }


}
