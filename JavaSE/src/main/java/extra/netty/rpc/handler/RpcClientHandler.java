package extra.netty.rpc.handler;

import extra.netty.rpc.ConnectionManager;
import extra.netty.rpc.domain.RpcMsg;
import extra.netty.rpc.domain.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcClientHandler extends SimpleChannelInboundHandler<RpcMsg<RpcResponse<?>>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcMsg<RpcResponse<?>> msg) throws Exception {
        ConnectionManager.response(msg);
    }
}
