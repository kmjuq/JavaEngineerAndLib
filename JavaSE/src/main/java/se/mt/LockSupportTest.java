package se.mt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/9/23 21:47
 */
@Slf4j
public class LockSupportTest {

    private static volatile int res = 0;

    public void increment() throws InterruptedException {
        LockSupport.park();
        res++;
        Thread.sleep(1000);
        LockSupport.unpark(Thread.currentThread());
    }

    public void decrement() {
        LockSupport.park();
        res--;
        LockSupport.unpark(Thread.currentThread());
    }

    public static void main(String[] args) {

        final Thread producer = new Thread(() -> {
            while (res <= 10) {
            }
        }, "producer");
        final Thread consumer = new Thread(() -> {
            while (true) {
            }
        }, "consumer");
        producer.start();
        consumer.start();
        Thread.yield();
    }

}
