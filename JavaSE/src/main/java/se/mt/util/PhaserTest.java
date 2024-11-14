package se.mt.util;

import com.pivovarit.function.ThrowingRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PhaserTest {

    @Test
    public void demo1() throws InterruptedException {
        final int[] resources = {10};
        Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return super.onAdvance(phase, registeredParties);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(ThrowingRunnable.unchecked(() -> {
                if (resources[0] < 0) return;
                resources[0]--;
                log.info("{}", resources[0]);
                phaser.arriveAndAwaitAdvance();
            })).start();
        }
        TimeUnit.SECONDS.sleep(10);
    }

}
