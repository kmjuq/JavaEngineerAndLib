package se.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-07-19 00:49
 */
public class FutureTest {

    @Test
    public void demo1() {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Future<Double> future = executor.submit(this::doSomeLongComputation);
        doSomethingElse();

        try {
            final Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //当前线程在等待过程中被中断
        } catch (ExecutionException e) {
            //计算抛出一个异常
        } catch (TimeoutException e) {
            // 超时
        }
    }

    private Double doSomeLongComputation() {
        return 0D;
    }

    private void doSomethingElse() {
    }


}
