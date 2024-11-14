package extra.rxjava;

import cn.hutool.core.thread.ThreadUtil;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableTest {


    @Test
    public void demo1() throws InterruptedException {
        Observable<String> helloWorld = Observable.just("hello world");
        helloWorld.subscribe(
                onNext -> log.info("kmj {}", onNext),
                onError -> {
                }
        );
    }

    @Test
    public void demo2() throws InterruptedException {
        final ObservableEmitter[] emitter = new ObservableEmitter[1];

        Observable.<String>create(subscriber -> emitter[0] = subscriber)
                .subscribe(onNext -> log.info("{}", onNext));

        ScheduledThreadPoolExecutor scheduledExecutor = ThreadUtil.createScheduledExecutor(1);
        scheduledExecutor.scheduleAtFixedRate(() -> {
            log.info("记录");
            emitter[0].onNext("12321");
        }, 0, 1, TimeUnit.SECONDS);
        emitter[0].onComplete();
        TimeUnit.SECONDS.sleep(10);
    }

}
