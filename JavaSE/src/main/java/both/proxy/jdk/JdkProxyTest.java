package both.proxy.jdk;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/2 11:11
 */
public class JdkProxyTest {

    @Test
    public void demo1() {
        final SayHello sh = (SayHello) Proxy.newProxyInstance(
                SayHello.class.getClassLoader(),
                new Class[]{SayHello.class},
                new SayProxy((SayHello) () -> "Hello,kmj")
        );
        final SayBye sb = (SayBye) Proxy.newProxyInstance(
                SayBye.class.getClassLoader(),
                new Class[]{SayBye.class},
                new SayProxy((SayBye) () -> "Bye,kmj")
        );
        sh.sayHello();
        sb.sayBye();
    }

    @Test
    public void demo2() {
        // 生成的代理类对象是不继承原生对象的字段的
        final SayHello sh = (SayHello) Proxy.newProxyInstance(
                SayHello.class.getClassLoader(),
                new Class[]{SayHello.class},
                new SayProxy(new SayHelloImpl())
        );
        final SayBye sb = (SayBye) Proxy.newProxyInstance(
                SayBye.class.getClassLoader(),
                new Class[]{SayBye.class},
                new SayProxy(new SayByeImpl())
        );
        sh.sayHello();
        sb.sayBye();
        System.out.println(Arrays.toString(sh.getClass().getDeclaredFields()));
        System.out.println(Arrays.toString(sb.getClass().getDeclaredFields()));
    }

}
