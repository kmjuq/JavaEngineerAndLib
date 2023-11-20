package both.io.nio.chatroom;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

@Slf4j
public class ChatClient implements Runnable {

    Selector selector;
    ServerSocketChannel clientSocketChannel;
    SocketChannel socketChannel;
    String username;
    int localPort;
    int targetPort;
    static final ChatClient client = new ChatClient();

    private ChatClient() {
    }

    public static ChatClient getInstance() {
        return client;
    }

    public ChatClient username(String username) {
        this.username = username;
        return this;
    }

    public ChatClient localPort(int port) {
        this.localPort = port;
        return this;
    }

    public ChatClient targetPort(int port) {
        this.targetPort = port;
        return this;
    }

    @Override
    public void run() {
        Assertions.assertNotNull(username);
        final InetSocketAddress targetInetSocketAddress = new InetSocketAddress("127.0.0.1", targetPort);
        final InetSocketAddress localInetSocketAddress = new InetSocketAddress("127.0.0.1", localPort);
        try {
            selector = Selector.open();
            clientSocketChannel = ServerSocketChannel.open();
            clientSocketChannel.bind(localInetSocketAddress);
            clientSocketChannel.configureBlocking(false);
            clientSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            socketChannel = SocketChannel.open(targetInetSocketAddress);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            socketChannel.write(ByteBuffer.wrap(StrUtil.format("{} 进入聊天室", username).getBytes(StandardCharsets.UTF_8)));

            while (true) {
                final int select = selector.selectNow();
                if (select > 0) {
                    final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        final SelectionKey next = iterator.next();
                        if (next.isAcceptable()) {

                        } else if (next.isConnectable()) {

                        } else if (next.isReadable()) {
                            final SocketChannel channel = (SocketChannel) next.channel();
                            // 读取服务端连接的数据
                            final ByteBuffer allocate = ByteBuffer.allocate(1024);
                            channel.read(allocate);
                            allocate.flip();
                            byte[] bytes = new byte[allocate.remaining()];
                            allocate.get(bytes);
                            String content = new String(bytes);
                            log.info("从服务器接收的消息 {}", content);
                        } else if (next.isWritable()) {
                            // ignore
                        } else {
                            // ignore
                        }
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            //ignore
        }
    }

    public void sendMsg(String msg) throws IOException {
        socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
    }

    static class ChatRoomTest1 {
        public static void main(String[] args) throws InterruptedException, IOException {
            String username = RandomUtil.randomString(5);
            ChatClient client = ChatClient.getInstance().username(username).localPort(8078).targetPort(8080);
            new Thread(client).start();
            Thread.sleep(1000);
            //发送数据给服务器端
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String content = scanner.nextLine();
                client.sendMsg(StrUtil.format("{} : {}", username, content));
            }
        }
    }

    static class ChatRoomTest2 {
        public static void main(String[] args) throws IOException, InterruptedException {
            String username = RandomUtil.randomString(5);
            ChatClient client = ChatClient.getInstance().username(username).localPort(8079).targetPort(8080);
            new Thread(client).start();
            Thread.sleep(1000);
            //发送数据给服务器端
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String content = scanner.nextLine();
                client.sendMsg(StrUtil.format("{} : {}", username, content));
            }
        }
    }

}
