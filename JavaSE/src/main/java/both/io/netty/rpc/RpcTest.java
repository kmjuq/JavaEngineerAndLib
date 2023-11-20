package both.io.netty.rpc;

import both.io.netty.rpc.handler.RpcProtocolCodec;
import both.io.netty.rpc.handler.RpcServerHandler;
import cn.hutool.core.lang.Tuple;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcTest {
    static class RPC {

        static class CommonModule {
            interface Echo {
                String echo(String echo);
            }
        }

        static class Producer {

            public static class EchoImpl implements CommonModule.Echo {
                @Override
                public String echo(String echo) {
                    return echo + "world";
                }
            }

            static class NettyServer {
                public static void main(String[] args) throws InterruptedException {
                    BeanManager.of(new Tuple(CommonModule.Echo.class.getName(),new EchoImpl()));
                    new ServerBootstrap()
                            .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                            .channel(NioServerSocketChannel.class)
                            .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline()
                                            .addLast(new RpcProtocolCodec())
                                            .addLast(new RpcServerHandler())
                                            .addLast(new LoggingHandler(LogLevel.DEBUG));
                                }
                            })
                            .bind(8080)
                            .sync();
                }
            }

        }

        static class Consumer {

            public static void main(String[] args) throws Exception {
                CommonModule.Echo echo = RpcProxy.newInstance(CommonModule.Echo.class);
                System.out.println(echo.echo("hello rpc"));
            }

        }
    }
}
