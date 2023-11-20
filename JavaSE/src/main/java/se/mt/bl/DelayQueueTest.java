package se.mt.bl;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DelayQueueTest {

    @Test
    public void demo1() throws InterruptedException {
        DelayQueue<Task> delayQueue = new DelayQueue<Task>();
        Task task1 = new Task(5, TimeUnit.SECONDS);
        Task task2 = new Task(10, TimeUnit.SECONDS);
        Task task3 = new Task(15, TimeUnit.SECONDS);

        delayQueue.put(task2);
        delayQueue.put(task1);
        delayQueue.put(task3);

        while (!delayQueue.isEmpty()) {
            Task task = delayQueue.take();
            log.info("{}", task);
        }
    }

}

@ToString
class Task implements Delayed {

    Long time;
    Integer value;
    TimeUnit unit;

    public Task(Integer value, TimeUnit unit) {
        this.value = value;
        this.unit = unit;
        this.time = System.currentTimeMillis() + unit.toMillis(value);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o.getDelay(TimeUnit.MILLISECONDS) > this.getDelay(TimeUnit.MILLISECONDS)) {
            return -1;
        } else if (o.getDelay(TimeUnit.MILLISECONDS) == this.getDelay(TimeUnit.MILLISECONDS)) {
            return 0;
        } else {
            return 1;
        }

    }

}