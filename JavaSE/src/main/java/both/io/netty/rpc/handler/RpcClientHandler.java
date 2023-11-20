package both.io.netty.rpc.handler;

import both.io.netty.rpc.ConnectionManager;
import both.io.netty.rpc.domain.RpcMsg;
import both.io.netty.rpc.domain.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcClientHandler extends SimpleChannelInboundHandler<RpcMsg<RpcResponse<?>>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcMsg<RpcResponse<?>> msg) throws Exception {
        ConnectionManager.response(msg);
    }
}
