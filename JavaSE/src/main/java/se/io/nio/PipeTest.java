package se.io.nio;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/10/6 22:53
 */
@Slf4j
public class PipeTest {

    AtomicInteger ai = new AtomicInteger();

    @Test
    public void demo() throws IOException, InterruptedException {
        final Pipe open = Pipe.open();
        final ByteBuffer read = ByteBuffer.allocate(1024);
        final ByteBuffer write = ByteBuffer.allocate(1024);
        new Thread(new PipeThread(open) {
            @Override
            public void run() {
                final Pipe.SinkChannel sink = open.sink();
                while (true) {
                    write.put("sink write".getBytes(StandardCharsets.UTF_8));
                    try {
                        write.flip();
                        sink.write(write);
                        write.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "write").start();
        new Thread(new PipeThread(open) {
            @Override
            public void run() {
                final Pipe.SourceChannel source = open.source();
                while (true) {
                    try {
                        read.clear();
                        source.read(read);
                        log.info("read {},{}", new String(read.array()), ai.incrementAndGet());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "read").start();
        Thread.currentThread().join();
    }

    @Setter
    @Getter
    abstract static class PipeThread implements Runnable {
        Pipe pipe;

        public PipeThread(Pipe pipe) {
            this.pipe = pipe;
        }
    }

}
