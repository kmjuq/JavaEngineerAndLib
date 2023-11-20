package extra.disruptor;

import cn.hutool.core.util.RandomUtil;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

/**
 * disrutor 在创建 Ringbuffer 时，会初始化对象数组，具体如下：
 *
 * @see com.lmax.disruptor.RingBufferFields#fill(com.lmax.disruptor.EventFactory)
 * 所以 disrutor 在创建时，会需要传入对象创建逻辑。因此也可以得出域对象必须有空构造函数。
 * 由于 disrutor 的生产消费都是在 RingBuffer 中进行，在生产时，仅仅修改域对象内的业务数据，因为域对象已经经过初始化了。
 */
@Slf4j
public class DisruptorTest {

    Disruptor<BinaryOperatorEvent> disruptor;
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    @BeforeEach
    public void init() {
        disruptor = new Disruptor<>(BinaryOperatorEvent::new, 1024, DaemonThreadFactory.INSTANCE);
    }

    /**
     * p1 -> c1
     */
    @Test
    public void demo1() throws InterruptedException {
        disruptor.handleEventsWith(consumer(1, UnaryOperator.identity()));
        disruptor.start();
        produce(1);
        // 防止测试线程提前结束
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * p1 -> c1 -> c2 -> c3
     */
    @Test
    public void demo2() throws InterruptedException {
        // x * 2 + 3
        disruptor.handleEventsWith(consumer(1, event -> event.add(event.first() + event.second())))
                .then(consumer(2, event -> event.add(event.add() + 2)))
                .then(consumer(3, event -> event.add(event.add() * 2)));
        disruptor.start();
        produce(1);
        // 防止测试线程提前结束
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * p1 -> c1
     * p2 -> c1
     * p3 -> c1
     */
    @Test
    public void demo3() throws InterruptedException {
        disruptor.handleEventsWith(consumer(1, UnaryOperator.identity()));
        disruptor.start();
        produce(1);
        produce(2);
        produce(3);
        // 防止测试线程提前结束
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * p1 -> c1
     * p1 -> c2
     * p1 -> c3
     * <p>
     * 看日志数据时，会发现各个消费者打印的数据是一致的，这里猜测是打印日志时，node数据已经全部消费完成。
     */
    @Test
    public void demo4() throws InterruptedException {
        disruptor.handleEventsWith(
                consumer(1, node -> node.add(node.first() + node.second())),
                consumer(2, node -> node.sub(node.first() - node.second())),
                consumer(3, node -> node.mul(node.first() * node.second()))
        );
        disruptor.start();
        produce(1);

        // 防止测试线程提前结束
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * p1 -> c1
     * p1 -> c2
     * c1 -> c3
     * c2 -> c3
     */
    @Test
    public void demo5() throws InterruptedException {
        disruptor.handleEventsWith(
                consumer(1, node -> node.add(node.first() + node.second())),
                consumer(2, node -> node.sub(node.first() - node.second()))
        ).then(
                consumer(3, node -> node.mul(node.add() * node.sub()))
        );
        disruptor.start();
        produce(1);

        // 防止测试线程提前结束
        TimeUnit.SECONDS.sleep(10);
    }

    private void schedule1S(Runnable run) {
        ses.scheduleAtFixedRate(run, 1, 1, TimeUnit.SECONDS);
    }

    private void produce(int num) {
        schedule1S(() -> {
            int first = RandomUtil.randomInt(0, 10);
            int second = RandomUtil.randomInt(0, 10);
            disruptor.publishEvent((event, sequence) -> {
                log.info("生产者{},序列号{},生产内容为{},{}", num, sequence, first, second);
                event.first(first).second(second);
            });
        });
    }

    private EventHandler<BinaryOperatorEvent> consumer(int num, UnaryOperator<BinaryOperatorEvent> unaryOperator) {
        return (event, sequence, endOfBatch) -> {
            log.info("消费者{},序列号{},消费内容为{}", num, sequence, unaryOperator.apply(event));
        };
    }

}
