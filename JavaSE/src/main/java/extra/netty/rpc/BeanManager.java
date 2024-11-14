package extra.netty.rpc;

import cn.hutool.core.lang.Tuple;

import java.util.concurrent.ConcurrentHashMap;


public final class BeanManager {

    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    private BeanManager() {
    }

    public static void of(Tuple... tuples) {
        for (Tuple tuple : tuples) {
            map.put((String) tuple.getMembers()[0], tuple.getMembers()[1]);
        }
    }

    public static Object getInstace(String className) {
        return map.get(className);
    }

}
