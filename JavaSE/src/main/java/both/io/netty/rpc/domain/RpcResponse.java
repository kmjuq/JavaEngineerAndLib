package both.io.netty.rpc.domain;

import java.io.Serializable;

public class RpcResponse<T> implements Serializable {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "data=" + data +
                '}';
    }
}
