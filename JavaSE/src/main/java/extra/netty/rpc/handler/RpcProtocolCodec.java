package extra.netty.rpc.handler;

import extra.netty.rpc.domain.RpcMsg;
import extra.netty.rpc.serial.JdkSerialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RpcProtocolCodec extends ByteToMessageCodec<RpcMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMsg msg, ByteBuf out) throws Exception {
        byte[] serialize = JdkSerialization.serialize(msg);
        out.writeInt(serialize.length);
        out.writeBytes(serialize);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readInt();
        byte[] payload = new byte[size];
        ByteBuf byteBuf = in.readBytes(payload);
        RpcMsg rpcMsg = JdkSerialization.deSerialize(payload);
        out.add(rpcMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("", cause);
    }
}
