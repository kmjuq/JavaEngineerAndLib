package se.mt;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/10/2 00:40
 */
@Slf4j
public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Thread ta = new Thread(() -> {
            log.info("interrupt status {}", Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()) {
            }
            log.info("中断了。");
        }, "A");
        ta.start();

        Thread tb = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ta.interrupt();
        }, "B");
        tb.start();
        TimeUnit.SECONDS.sleep(3);
    }

    class Run implements Runnable {


        @SneakyThrows
        @Override
        public void run() {
            TimeUnit.SECONDS.sleep(10);
        }
    }


}
