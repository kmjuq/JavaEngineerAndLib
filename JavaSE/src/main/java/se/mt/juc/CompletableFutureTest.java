package se.mt.juc;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * 相关API
 */
@Slf4j
public class CompletableFutureTest {

    static final StopWatch sw = new StopWatch();

    @BeforeEach
    public void beforeEach() {
        sw.start();
    }

    @AfterEach
    public void afterEach() {
        sw.stop();
    }

    @AfterAll
    public static void afterAll() {
        log.info(sw.prettyPrint());
    }

    public String supply() {
        log.info("supply start");
        ThreadUtil.sleep(500);
        log.info("supply end");
        return "supply";
    }

    public Integer supplyInt() {
        log.info("supplyInt start");
        ThreadUtil.sleep(500);
        log.info("supplyInt end");
        return 1;
    }

    public Integer supplyInt1S() {
        log.info("supplyInt1S start");
        ThreadUtil.sleep(1000);
        log.info("supplyInt1S end");
        return 1;
    }

    public void consumer(String consumer) {
        log.info("consumer start {}", consumer);
        ThreadUtil.sleep(500);
        log.info("consumer end {}", consumer);
    }

    public String apply(Integer from) {
        log.info("apply start");
        ThreadUtil.sleep(500);
        log.info("apply end");
        return String.format("%s -> apply", from);
    }

    public String apply(CompletionStage<Integer> from) {
        log.info("apply start");
        ThreadUtil.sleep(500);
        log.info("apply end");
        return String.format("%s -> apply", from);
    }

    public void run() {
        log.info("run start");
        ThreadUtil.sleep(500);
        log.info("run end");
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#runAsync(java.lang.Runnable)}
     * 执行无参数无返回值的异步任务
     */
    @Test
    public void runTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> run = CompletableFuture.runAsync(this::run);
        Assertions.assertNull(run.get());
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#supplyAsync(java.util.function.Supplier)}
     * 执行无参数有返回值的异步任务
     */
    @Test
    public void supplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        Assertions.assertEquals("supply", supply.get());
    }

    /**
     * {@link CompletableFuture#get()}
     * {@link CompletableFuture#get(long, TimeUnit)}
     */
    @Test
    public void getTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> run = CompletableFuture.runAsync(this::run);
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        Assertions.assertNull(run.get());
        Assertions.assertEquals("supply", supply.get());
        CompletableFuture<String> supplyTime = CompletableFuture.supplyAsync(this::supply);
        Assertions.assertThrows(TimeoutException.class, () -> supplyTime.get(100, TimeUnit.MILLISECONDS));
    }

    /**
     * {@link CompletableFuture#getNow(Object)}
     */
    @Test
    public void getNowTest() {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        Assertions.assertEquals("getnow", supply.getNow("getnow"));
        ThreadUtil.sleep(1000);
        Assertions.assertEquals("supply", supply.getNow("getnow"));
    }

    @Test
    public void joinTest() {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        Assertions.assertEquals("supply", supply.join());
    }

    /**
     * get 与 join 的区别
     * <p>
     * get 必须捕获 checked exception ， 抛出的异常会经过 java.util.concurrent.ExecutionException 封装
     * java.util.concurrent.ExecutionException 是 checked exception
     * </p>
     * <p>
     * join 不用捕获 unchecked exception ，抛出的异常会经过 java.util.concurrent.CompletionException 封装
     * java.util.concurrent.CompletionException 是 unchecked exception
     * </p>
     */
    @Test
    public void getjoinTest() {
        CompletableFuture<String> supplyJoin = CompletableFuture.supplyAsync(this::supply);
        Assertions.assertEquals("supply", supplyJoin.join());

        CompletableFuture<String> supplyGet = CompletableFuture.supplyAsync(this::supply);
        try {
            Assertions.assertEquals("supply", supplyGet.get());
        } catch (InterruptedException | ExecutionException ignore) {

        }
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#complete(java.lang.Object)}
     * 该方法给 cf 设置结果，设置成正常执行的值
     * 如果 CompletableFuture 已经完成，该方法无效。get 还是获取正确的数据。
     */
    @Test
    public void completeTest() throws ExecutionException, InterruptedException {
        // 算是通知等待模型的一种实现
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(this::supply);
        //ThreadUtil.sleep(1000);
        cf.complete("complete");
        Assertions.assertEquals("complete", cf.get());
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#completeExceptionally(java.lang.Throwable)}
     * 该方法给 cf 设置结果，设置成异常
     */
    @Test
    public void completeExceptionallyTest() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(this::run);
        cf.completeExceptionally(new RuntimeException());
        Assertions.assertThrows(ExecutionException.class, cf::get);
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#cancel(boolean)}
     * 该方法给 cf 设置结果，设置成取消
     */
    @Test
    public void cancelTest() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(this::run);
        cf.cancel(true);
        Assertions.assertThrows(CancellationException.class, cf::join);
        Assertions.assertTrue(cf.isCancelled());
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#isDone()}
     * 如果 cf 执行结果为 正常执行 或者 异常 或者 取消 ，结果都为true
     */
    @Test
    public void isDoneTest() {
        CompletableFuture<String> cfCompelete = CompletableFuture.supplyAsync(this::supply);
        cfCompelete.complete("complete");

        CompletableFuture<String> cfException = CompletableFuture.supplyAsync(this::supply);
        cfException.completeExceptionally(new RuntimeException());

        CompletableFuture<String> cfCancel = CompletableFuture.supplyAsync(this::supply);
        cfCancel.cancel(true);

        ThreadUtil.sleep(1000);
        Assertions.assertTrue(cfCompelete.isDone());
        Assertions.assertTrue(cfException.isDone());
        Assertions.assertTrue(cfCancel.isDone());
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#handle(java.util.function.BiFunction)}
     * 相当于 try catch 的 finally
     */
    @Test
    public void handleTest() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(this::supply);
        cf.handle((s, e) -> {
            log.info("handleTest [{},{}]", s, e);
            return "handle";
        });
        cf.join();
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#whenComplete(java.util.function.BiConsumer)}
     * 当有执行结果就回调
     */
    @Test
    public void whenCompleteTest() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(this::supply);
        cf.whenComplete((r, e) -> log.info("whenComplete success[{},{}]", r, e));
        cf.join();
    }

    /**
     * {@link java.util.concurrent.CompletableFuture#exceptionally(java.util.function.Function)}
     * 当执行结果为异常时回调
     */
    @Test
    public void exceptionallyTest() {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(1000);
            return "supply";
        });
        supply.completeExceptionally(new RuntimeException());
        supply.exceptionally(e -> {
            log.info("exceptionally", e);
            return "e";
        });
        Assertions.assertThrows(ExecutionException.class, supply::get);
    }

    /**
     * <p>
     * 在进行异步任务的回调API调用时，回调任务有顺序要求。
     *
     * @see java.util.concurrent.CompletableFuture#exceptionally(java.util.function.Function)
     * @see java.util.concurrent.CompletableFuture#handle(java.util.function.BiFunction)
     * 上述两个方法都可以设置返回值，且方法的返回值会传递给下一个回调方法
     * </p>
     */
    @Test
    public void callbackTest() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(this::supply);
        cf.completeExceptionally(new RuntimeException());
        cf
                .exceptionally(e -> {
                    log.info("exceptionally", e);
                    return "e";
                }).handle((s, e) -> {
                    log.info("handleTest [{},{}]", s, e);
                    return "handle";
                })
                .whenComplete((r, e) ->
                        log.info("whenComplete success [{},{}]", r, e)
                );
        Assertions.assertThrows(RuntimeException.class, cf::join);
    }

    @Test
    public void thenApplyTest() {
        CompletableFuture<Integer> supply = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<String> apply = supply.thenApply(this::apply);
        Assertions.assertEquals(1, supply.join());
        Assertions.assertEquals("1 -> apply", apply.join());
    }

    @Test
    public void thenAcceptTest() {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        CompletableFuture<Void> consumer = supply.thenAccept(this::consumer);
        Assertions.assertEquals("supply", supply.join());
        Assertions.assertNull(consumer.join());
    }

    @Test
    public void thenRunTest() {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        CompletableFuture<Void> run = supply.thenRunAsync(this::run);
        Assertions.assertEquals("supply", supply.join());
        Assertions.assertNull(run.join());
    }

    @Test
    public void thenComposeTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<String> compose = supplyInt.thenCompose((integer -> CompletableFuture.supplyAsync(() -> this.apply(integer))));
        Assertions.assertEquals("1 -> apply", compose.get());
    }

    @Test
    public void thenCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        CompletableFuture<byte[]> result = supplyInt.thenCombine(supply, ((i, s) -> (i + s).getBytes(StandardCharsets.UTF_8)));
        Assertions.assertEquals("1supply", new String(result.get(), StandardCharsets.UTF_8));
    }

    /**
     * 等待两个异步任务执行完毕，然后对异步结果进行消费。
     */
    @Test
    public void thenAcceptBothTest() {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        CompletableFuture<Void> acceptBoth = supplyInt.thenAcceptBoth(supply, (i, s) -> log.info("{},{}", i, s));
        acceptBoth.join();
    }

    /**
     * 两个异步任务执行完后，再执行一个任务。
     */
    @Test
    public void runAfterBothTest() {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        supplyInt.runAfterBoth(supply, () -> log.info("Runnable"));
        supplyInt.join();
    }

    /**
     * 获取两个异步任务中最快的任务并执行apply
     */
    @Test
    public void applyToEitherTest() {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<Integer> supplyInt1S = CompletableFuture.supplyAsync(this::supplyInt1S);
        CompletableFuture<String> result = supplyInt.applyToEither(supplyInt1S, String::valueOf);
        Assertions.assertEquals("1", result.join());
    }

    /**
     * 获取两个异步任务中最快的任务并执行consume
     */
    @Test
    public void acceptEitherTest() {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<Integer> supplyInt1S = CompletableFuture.supplyAsync(this::supplyInt1S);
        CompletableFuture<Void> acceptEither = supplyInt.acceptEither(supplyInt1S, i -> log.info("{}", i));
        Assertions.assertNull(acceptEither.join());
    }

    @Test
    public void allOfTest() {
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<Integer> supplyInt1S = CompletableFuture.supplyAsync(this::supplyInt1S);
        CompletableFuture<Void> future = CompletableFuture.allOf(supplyInt1S, supplyInt);
        future.join();
    }

    /**
     * anyOf 使用时需要控制返回的结果类型一致。
     */
    @Test
    public void anyOfTest() {
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(this::supply);
        CompletableFuture<Integer> supplyInt = CompletableFuture.supplyAsync(this::supplyInt);
        CompletableFuture<Object> future = CompletableFuture.anyOf(supply, supplyInt);
        Object join = future.join();
    }

}
