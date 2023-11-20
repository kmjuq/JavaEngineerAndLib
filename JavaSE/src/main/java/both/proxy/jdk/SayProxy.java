package both.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/2 11:09
 */
public class SayProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(SayProxy.class);

    private final Object obj;

    public SayProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("{}方法调用成功", method.getName());
        return method.invoke(obj, args);
    }

}
