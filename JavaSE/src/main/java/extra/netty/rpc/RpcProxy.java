package extra.netty.rpc;

import extra.netty.rpc.domain.RpcMsg;
import extra.netty.rpc.domain.RpcRequest;
import extra.netty.rpc.domain.RpcResponse;
import extra.netty.rpc.handler.RpcClientHandler;
import extra.netty.rpc.handler.RpcProtocolCodec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxy implements InvocationHandler {

    public static <T> T newInstance(Class<T> clazz) throws Exception {
        // 创建代理对象
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                new RpcProxy());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest()
                .setClassName(method.getDeclaringClass().getName())
                .setMethodName(method.getName())
                .setParameterTypes(method.getParameterTypes())
                .setParameterValue(args);

        RpcMsg<RpcRequest> rpcMsg = new RpcMsg<>();
        rpcMsg.setType(1).setData(rpcRequest);

        ConnectionManager.request(rpcMsg);
        remoteCall(rpcMsg);
        RpcMsg<RpcResponse<?>> rpcResponse = ConnectionManager.result(rpcMsg);
        return rpcResponse.getData().getData();
    }

    public ChannelFuture nettyClient() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ChannelFuture sync = bootstrap
                .channel(NioSocketChannel.class)
                .group(worker)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new RpcProtocolCodec())
                                .addLast(new RpcClientHandler())
                                .addLast(new LoggingHandler(LogLevel.DEBUG));
                    }
                })
                .connect("127.0.0.1", 8080)
                .sync();
        return sync;
    }


    public void remoteCall(RpcMsg<RpcRequest> rpcMsg) throws InterruptedException {
        ChannelFuture channelFuture = nettyClient();
        channelFuture.channel().writeAndFlush(rpcMsg);
    }
}
