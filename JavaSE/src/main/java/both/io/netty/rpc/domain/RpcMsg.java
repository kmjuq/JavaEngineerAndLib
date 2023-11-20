package both.io.netty.rpc.domain;

import java.io.Serializable;


public class RpcMsg<T> implements Serializable {

    private String id;

    // 1 request 2 response
    private int type;

    private T data;

    public String getId() {
        return id;
    }

    public RpcMsg<T> setId(String id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public RpcMsg<T>  setType(int type) {
        this.type = type;
        return this;
    }

    public T getData() {
        return data;
    }

    public RpcMsg<T>  setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "RpcMsg{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", data=" + data +
                '}';
    }
}
