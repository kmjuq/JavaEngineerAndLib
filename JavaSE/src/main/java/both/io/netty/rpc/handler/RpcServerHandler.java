package both.io.netty.rpc.handler;

import both.io.netty.rpc.BeanManager;
import both.io.netty.rpc.domain.RpcMsg;
import both.io.netty.rpc.domain.RpcRequest;
import both.io.netty.rpc.domain.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcMsg<RpcRequest>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcMsg<RpcRequest> msg) throws Exception {
        RpcRequest request = msg.getData();

        Object instace = BeanManager.getInstace(request.getClassName());
        Method method = instace.getClass().getMethod(request.getMethodName(),request.getParameterTypes());
        Object result = method.invoke(instace, request.getParameterValue());

        RpcResponse<Object> response = new RpcResponse<>();
        response.setData(result);
        RpcMsg<RpcResponse<Object>> rpcMsg = new RpcMsg<>();
        rpcMsg.setId(msg.getId()).setData(response).setType(2);
        ctx.channel().writeAndFlush(rpcMsg);
    }
}
