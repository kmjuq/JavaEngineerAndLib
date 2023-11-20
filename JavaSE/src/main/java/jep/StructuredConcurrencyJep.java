package jep;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * 通过两个样例的执行过程可以得之，使用结构化并发，当其中一个线程有问题时，可以直接关闭相关线程。
 * {@link StructuredTaskScope}
 */
@Slf4j
public class StructuredConcurrencyJep {

    @Test
    public void demo1() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<String> user = scope.fork(this::findUser);
            StructuredTaskScope.Subtask<Integer> order = scope.fork(this::fetchOrder);

            scope.join();
            scope.throwIfFailed();

        }

        log.info(" TimeUnit.MILLISECONDS start");
        TimeUnit.MILLISECONDS.sleep(3000);
        log.info(" TimeUnit.MILLISECONDS end");
    }

    @Test
    public void demo2() throws InterruptedException {
        // 使用twr语法后，会等executorService内线程执行完然后关闭executorService才会继续执行
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            executorService.submit(this::findUser);
            executorService.submit(this::fetchOrder);
        }

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(this::findUser);
//        executorService.submit(this::fetchOrder);

        log.info(" TimeUnit.MILLISECONDS start");
        TimeUnit.MILLISECONDS.sleep(3000);
        log.info(" TimeUnit.MILLISECONDS end");
    }

    public String findUser() throws InterruptedException {
        log.info("findUser TimeUnit.MILLISECONDS start");
        TimeUnit.MILLISECONDS.sleep(1000);
        log.info("findUser TimeUnit.MILLISECONDS end");
        log.info("findUser {}", 1 / 0);
        log.info("findUser end");
        return "findUser";
    }

    public int fetchOrder() throws InterruptedException {
        log.info("fetchOrder TimeUnit.MILLISECONDS start");
        TimeUnit.MILLISECONDS.sleep(2000);
        log.info("fetchOrder TimeUnit.MILLISECONDS end");
        log.info("fetchOrder end");
        return 1;
    }
}
