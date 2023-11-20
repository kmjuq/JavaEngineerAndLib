package se.mt.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Volatile {

    public static void main(String[] args) throws InterruptedException {
        TestThread tt = new TestThread();
        tt.start();
        AtomicInteger atomicInteger = new AtomicInteger();
        while (true) {
            // 加了改行 主线程能读取flag, 没加不行.
            // log.info("循环次数 {}",atomicInteger.getAndIncrement());
            if (tt.isFlag()) {
                log.info("有点东西");
            }
            // 加了改行 主线程能读取flag, 没加不行.
            // TimeUnit.SECONDS.sleep(1);
        }
    }

    static class TestThread extends Thread {

        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
            log.info("thread flag {}", flag);
        }
    }
}
