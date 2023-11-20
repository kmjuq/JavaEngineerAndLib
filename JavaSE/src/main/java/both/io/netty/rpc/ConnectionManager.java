package both.io.netty.rpc;

import both.io.netty.rpc.domain.RpcMsg;
import both.io.netty.rpc.domain.RpcRequest;
import both.io.netty.rpc.domain.RpcResponse;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.UUID;
import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class ConnectionManager {

    private static final ConcurrentHashMap<String, Pair<RpcMsg<RpcRequest>, Promise<RpcMsg<RpcResponse<?>>>>> maps = new ConcurrentHashMap<>();
    private static final EventExecutor eventExecutor= new DefaultEventLoop();

    public static void request(RpcMsg<RpcRequest> rpcMsg) {
        String uuid = UUID.fastUUID().toString(true);
        rpcMsg.setId(uuid);
        maps.put(uuid, Pair.of(rpcMsg, new DefaultPromise<>(eventExecutor)));
    }

    public static void response(RpcMsg<RpcResponse<?>> rpcMsg) {
        String uuid = rpcMsg.getId();
        Pair<RpcMsg<RpcRequest>, Promise<RpcMsg<RpcResponse<?>>>> rpcMsgPromisePair = maps.get(uuid);
        rpcMsgPromisePair.getValue().setSuccess(rpcMsg);
    }

    public static RpcMsg<RpcResponse<?>> result(RpcMsg<RpcRequest> rpcMsg) throws ExecutionException, InterruptedException {
        String uuid = rpcMsg.getId();
        Pair<RpcMsg<RpcRequest>, Promise<RpcMsg<RpcResponse<?>>>> rpcMsgPromisePair = maps.get(uuid);
        return rpcMsgPromisePair.getValue().get();
    }

}
