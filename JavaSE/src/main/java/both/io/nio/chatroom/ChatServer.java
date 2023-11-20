package both.io.nio.chatroom;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class ChatServer {

    public static void start() throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        final Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            final int select = selector.selectNow();
            if (select > 0) {
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey next = iterator.next();
                    if (next.isAcceptable()) {
                        final SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                        accept.write(ByteBuffer.wrap("欢迎来到聊天室".getBytes(StandardCharsets.UTF_8)));
                    } else if (next.isConnectable()) {

                    } else if (next.isReadable()) {
                        final SocketChannel channel = (SocketChannel) next.channel();
                        // 读取客户端连接的数据
                        final ByteBuffer allocate = ByteBuffer.allocate(1024);
                        channel.read(allocate);
                        allocate.flip();
                        byte[] bytes = new byte[allocate.remaining()];
                        allocate.get(bytes);
                        String content = new String(bytes);
                        log.info("从客服端读取的数据: {}", content);

                        // 将读取的数据转发到其他连接
                        final Set<SelectionKey> keys = selector.keys();
                        final Iterator<SelectionKey> keyIterator = keys.iterator();
                        while (keyIterator.hasNext()) {
                            final SelectionKey key = keyIterator.next();
                            final SelectableChannel socketChannel = key.channel();
                            if (socketChannel instanceof SocketChannel sc && socketChannel != channel) {
                                sc.write(ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)));
                            }
                        }
                    } else if (next.isWritable()) {
                        // ignore
                    } else {
                        // ignore
                    }
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        start();
    }

}
