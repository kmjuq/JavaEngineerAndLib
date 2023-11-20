package se.mt;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsTest {

    /**
     * {@link Executors#newCachedThreadPool()}
     * 特性：
     *
     */
    @Test
    public void cache() throws ExecutionException, InterruptedException {
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<Integer> submit = cachedThreadPool.submit(() -> 2);
        System.out.println(submit.get());
    }

    public void fix(){
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }




}
