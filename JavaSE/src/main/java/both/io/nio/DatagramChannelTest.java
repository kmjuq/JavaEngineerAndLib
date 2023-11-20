package both.io.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/10/6 21:00
 */
@Slf4j
public class DatagramChannelTest {

    AtomicInteger ai = new AtomicInteger(0);

    @Test
    public void demo() throws IOException {
        final InetSocketAddress revISA = new InetSocketAddress(8000);
        final InetSocketAddress sendISA = new InetSocketAddress("127.0.0.1", 8000);
        final DatagramChannel rev = DatagramChannel.open().bind(revISA);
        final DatagramChannel send = DatagramChannel.open();
        final ByteBuffer buf = ByteBuffer.allocate(1024);
        rev.configureBlocking(false);
        send.configureBlocking(false);
        while (true) {
            rev.receive(buf);
            buf.flip();
            log.info(new String(buf.array()));
            buf.clear();
            buf.put(("" + ai.incrementAndGet()).getBytes(StandardCharsets.UTF_8));
            send.send(buf, sendISA);
        }
    }

}
