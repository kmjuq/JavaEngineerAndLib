package both.io.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/10/5 21:48
 */
@Slf4j
public class SocketServerChannelDemo {

    @Test
    public void demo() throws IOException, InterruptedException {
        final ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(80));
        ssc.configureBlocking(false);
        while (true) {
            final SocketChannel accept = ssc.accept();
            if (accept != null && accept.isConnected()) {
                log.info("请求连接 {}", accept.getRemoteAddress());
                accept.close();
            }
            Thread.sleep(500);
        }
    }


}
