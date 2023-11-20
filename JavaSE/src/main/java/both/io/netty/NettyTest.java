package both.io.netty;

import both.io.netty.proto.PersonDataInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NettyTest {


    public static class Template {

        private Template() {
        }

        static class Server {
            public ServerBootstrap serverBootstrap;
            public NioEventLoopGroup boss;
            public NioEventLoopGroup worker;
            public ChannelFuture cf;

            Server(int port, ChannelHandler ch) throws InterruptedException {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                NioEventLoopGroup boss = new NioEventLoopGroup();
                NioEventLoopGroup worker = new NioEventLoopGroup();
                ChannelFuture sync = serverBootstrap
                        .group(boss, worker)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(ch)
                        .bind(port)
                        .sync();
                this.serverBootstrap = serverBootstrap;
                this.boss = boss;
                this.worker = worker;
                this.cf = sync;
            }

            Server(ChannelHandler ch) throws InterruptedException {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                NioEventLoopGroup boss = new NioEventLoopGroup();
                NioEventLoopGroup worker = new NioEventLoopGroup();
                ChannelFuture sync = serverBootstrap
                        .group(boss, worker)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(ch)
                        .bind(8080)
                        .sync();
                this.serverBootstrap = serverBootstrap;
                this.boss = boss;
                this.worker = worker;
                this.cf = sync;
            }
        }

        public static class Client {
            public Bootstrap bootstrap;
            public NioEventLoopGroup worker;
            public ChannelFuture cf;

            Client(String host, int port, ChannelHandler ch) throws InterruptedException {
                Bootstrap bootstrap = new Bootstrap();
                NioEventLoopGroup worker = new NioEventLoopGroup();
                ChannelFuture sync = bootstrap
                        .channel(NioSocketChannel.class)
                        .group(worker)
                        .handler(ch)
                        .connect(host, port)
                        .sync();
                this.bootstrap = bootstrap;
                this.worker = worker;
                this.cf = sync;
            }

            Client(ChannelHandler ch) throws InterruptedException {
                Bootstrap bootstrap = new Bootstrap();
                NioEventLoopGroup worker = new NioEventLoopGroup();
                ChannelFuture sync = bootstrap
                        .channel(NioSocketChannel.class)
                        .group(worker)
                        .handler(ch)
                        .connect("127.0.0.1", 8080)
                        .sync();
                this.bootstrap = bootstrap;
                this.worker = worker;
                this.cf = sync;
            }
        }
    }

    /**
     * 粘包拆包
     * 滑动窗口
     */

    static class Netty {
        static class Server {
            public static void main(String[] args) {
                new ServerBootstrap()
                        .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        log.info(buf.toString(Charset.defaultCharset()));
                                    }
                                });
                            }
                        })
                        .bind(8989);
            }
        }

        static class Client {
            public static void main(String[] args) throws InterruptedException {
                Channel channel = new Bootstrap()
                        .group(new NioEventLoopGroup())
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new StringEncoder());
                            }
                        })
                        .connect(new InetSocketAddress("127.0.0.1", 8989))
                        .sync()
                        .channel();
                System.out.println(channel);
            }
        }
    }

    static class ByteBufTest {

        /**
         * 使用 ByteBuf 需要注意释放。
         * 在 tail handler 里面会释放 ChannelHandlerContext 传递的 ByteBuf ,如果传递的不是,则需要在ByteBuf最后一次使用的时候释放
         */

        public static void main(String[] args) {
            ByteBuf directBuffer = ByteBufAllocator.DEFAULT.directBuffer();
            ByteBuf heapBuffer = ByteBufAllocator.DEFAULT.heapBuffer();
        }
    }

    static class HandlerTest {

        /**
         * 在请求处理 head -> i1 -> i2 -> o1 -> o2 -> tail
         * i 代表 入站
         * o 代表 出战
         * 入站时执行顺序为 head -> i1 -> i2 -> tail
         * 出站时执行顺序为 tail -> o2 -> o1 -> head
         * handler 链的逻辑结构为双向链表
         */

        static class Server {
            public static void main(String[] args) {

            }
        }

        static class Client {
            public static void main(String[] args) {

            }
        }
    }

    static class ChatRoom {

        /**
         * 做的功能群聊消息广播。
         * <p>
         * 当让 channel 发送接收数据时，所有的数据出战必须为 ByteBuf , 入站也必须为 ByteBuf
         */

        static class VisitorManager {
            static ConcurrentHashMap<String, Channel> visitors = new ConcurrentHashMap<>();

            private VisitorManager() {
            }

            public static void addVisitor(Channel sc) {
                visitors.put(sc.remoteAddress().toString(), sc);
            }


            public static void broadcastOtherVisitor(Channel self, String msg) {
                visitors.values().forEach(sc -> {
                    if (sc.equals(self)) {

                    } else {
                        log.info("将消息通知给其他客户端: {}", msg);
                        sc.writeAndFlush(Unpooled.buffer().writeBytes(msg.getBytes(StandardCharsets.UTF_8)));
                    }
                });
            }
        }

        static class Server {
            public static void main(String[] args) throws InterruptedException {
                new ServerBootstrap()
                        .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        .childHandler(new ChannelInitializer<>() {
                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                VisitorManager.addVisitor(ch);
                                ch.pipeline()
                                        .addLast(new LoggingHandler(LogLevel.DEBUG))
                                        .addLast(new StringEncoder())
                                        .addLast(new StringDecoder())
                                        .addLast(new ChannelInboundHandlerAdapter() {
                                            @Override
                                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                                log.info("从客户端接收的消息: {}", msg);
                                                VisitorManager.broadcastOtherVisitor(ctx.channel(), (String) msg);
                                                super.channelRead(ctx, msg);
                                            }
                                        });
                            }
                        })
                        .bind(8989)
                        .sync();
            }
        }

        static class Client {
            public static void main(String[] args) throws InterruptedException, IOException {
                Channel channel = new Bootstrap()
                        .group(new NioEventLoopGroup())
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<>() {
                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                ch.pipeline()
                                        .addLast(new LoggingHandler(LogLevel.DEBUG))
                                        .addLast(new StringEncoder())
                                        .addLast(new StringDecoder())
                                        .addLast(new ChannelInboundHandlerAdapter() {
                                            @Override
                                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                                log.info("从服务端接收的消息: {}", msg.toString());
                                                super.channelRead(ctx, msg);
                                            }
                                        });

                            }
                        })
                        .connect("127.0.0.1", 8989)
                        .sync()
                        .channel();
                System.out.println("Enter text (quit to end)");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                for (; ; ) {
                    String line = in.readLine();
                    if (line == null || "quit".equalsIgnoreCase(line)) {
                        break;
                    }
                    channel.writeAndFlush(line);
                }
            }
        }
    }

    static class HttpServer {

        public static void main(String[] args) {
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            final NioEventLoopGroup boss = new NioEventLoopGroup();
            final NioEventLoopGroup worker = new NioEventLoopGroup();
            final InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);

            try {
                final ChannelFuture server = serverBootstrap
                        .group(boss, worker)
                        .channel(NioServerSocketChannel.class)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline()
                                        .addLast(new LoggingHandler(LogLevel.INFO))
                                        .addLast(new HttpServerCodec())
                                        .addLast(new SimpleChannelInboundHandler<HttpObject>() {
                                            @Override
                                            protected void channelRead0(ChannelHandlerContext ctx, HttpObject ho) throws Exception {
                                                ByteBuf HELLO_WORLD = Unpooled.copiedBuffer("hello,world".getBytes(StandardCharsets.UTF_8));
                                                if (ho instanceof HttpRequest httpRequest) {
                                                    final FullHttpResponse fullHttpResponse =
                                                            new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, HELLO_WORLD);
                                                    fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                                                    fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, HELLO_WORLD.readableBytes());
                                                    ctx.writeAndFlush(fullHttpResponse);
                                                }
                                            }
                                        });
                            }
                        })
                        .bind(inetSocketAddress);
                server.sync();
                server.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        }
    }

    static class WsServer {
        public static void main(String[] args) throws InterruptedException {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            NioEventLoopGroup boss = new NioEventLoopGroup();
            NioEventLoopGroup worker = new NioEventLoopGroup();
            ChannelFuture channelFuture = serverBootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline
                                    .addLast(new LoggingHandler(LogLevel.INFO))
                                    .addLast(new HttpServerCodec())
                                    .addLast(new HttpObjectAggregator(8192))
                                    .addLast(new WebSocketServerProtocolHandler("/ws"))
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            switch (msg) {
                                                case PingWebSocketFrame ping -> log.info(ping.toString());
                                                case PongWebSocketFrame pong -> log.info(pong.toString());
                                                case BinaryWebSocketFrame binary -> log.info(binary.toString());
                                                case TextWebSocketFrame text -> log.info(text.text());
                                                case ContinuationWebSocketFrame continuation -> log.info(continuation.toString());
                                                case CloseWebSocketFrame close -> log.info(close.toString());
                                                default -> super.channelRead(ctx, msg);
                                            }
                                            super.channelRead(ctx, msg);
                                        }
                                    });
                        }
                    })
                    .bind(8080)
                    .sync();
        }
    }

    static class SimpleProtoBuf {
        static class Server {
            public static void main(String[] args) throws InterruptedException {
                new Template.Server(8080, new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        if (msg instanceof ByteBuf bf) {
                                            byte[] bytes = new byte[bf.readableBytes()];
                                            bf.readBytes(bytes);
                                            PersonDataInfo.Person person = PersonDataInfo.Person.parseFrom(bytes);
                                            log.info("服务器接收到的 protobuf 字节流 {}", person);
                                        }
                                    }
                                });
                    }
                });
            }
        }

        static class Client {
            public static void main(String[] args) throws InterruptedException {
                new Template.Client("127.0.0.1", 8080, new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        PersonDataInfo.Person kmj = PersonDataInfo.Person.newBuilder()
                                                .setDataTypeValue(1)
                                                .setName("kmj")
                                                .setAddress(PersonDataInfo.Address.newBuilder().setName("cn").build())
                                                .build();
                                        log.info("连接已经被激活 {}", kmj);
                                        ctx.writeAndFlush(Unpooled.copiedBuffer(kmj.toByteArray()));
                                    }
                                });
                    }
                });
            }
        }
    }

    static class StandardProtoBuf {
        static class Server {
            public static void main(String[] args) throws InterruptedException {
                new Template.Server(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(PersonDataInfo.Person.getDefaultInstance()))
                                .addLast(new SimpleChannelInboundHandler<PersonDataInfo.Person>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, PersonDataInfo.Person msg) throws Exception {
                                        log.info("接收到的实例内容为：{}", msg);
                                    }
                                });
                    }
                });
            }
        }

        static class Client {
            public static void main(String[] args) throws InterruptedException {
                new Template.Client(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(PersonDataInfo.Person.getDefaultInstance()))
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        PersonDataInfo.Person kmj = PersonDataInfo.Person.newBuilder()
                                                .setDataTypeValue(1)
                                                .setName("kmj")
                                                .setAddress(PersonDataInfo.Address.newBuilder().setName("cn").build())
                                                .build();
                                        log.info("连接已经被激活 {}", kmj);
                                        ctx.writeAndFlush(kmj);
                                    }
                                });
                    }
                });
            }
        }
    }

    static class RPCProtoBuf {
        static class Server {
            public static void main(String[] args) throws InterruptedException {
                new Template.Server(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(PersonDataInfo.Person.getDefaultInstance()))
                                .addLast(new SimpleChannelInboundHandler<PersonDataInfo.Person>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, PersonDataInfo.Person msg) throws Exception {
                                        log.info("接收到的实例内容为：{}", msg);
                                    }
                                });
                    }
                });
            }
        }

        static class Client {
            public static void main(String[] args) throws InterruptedException {
                new Template.Client(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(PersonDataInfo.Person.getDefaultInstance()))
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        PersonDataInfo.Person kmj = PersonDataInfo.Person.newBuilder()
                                                .setDataTypeValue(1)
                                                .setName("kmj")
                                                .setAddress(PersonDataInfo.Address.newBuilder().setName("cn").build())
                                                .build();
                                        log.info("连接已经被激活 {}", kmj);
                                        ctx.writeAndFlush(kmj);
                                    }
                                });
                    }
                });
            }
        }
    }

}
