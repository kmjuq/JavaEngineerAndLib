package both.io.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/10/6 22:47
 */
public class SelectorTest {

    @Test
    public void demo() throws IOException {
        final Selector s = Selector.open();
        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));

    }


}
