package both.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/2 18:14
 */
public class CglibTest {

    private static final Logger log = LoggerFactory.getLogger(CglibTest.class);

    @Test
    public void demo1() {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibHello.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            log.info("[{}] 方法调用成功", method.getName());
            return proxy.invokeSuper(obj, args);
        });
        final CglibHello cglibHello = (CglibHello) enhancer.create();
        cglibHello.sayHello();
    }

}
