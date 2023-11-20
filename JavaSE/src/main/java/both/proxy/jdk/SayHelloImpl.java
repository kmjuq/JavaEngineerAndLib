package both.proxy.jdk;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/2 16:28
 */
public class SayHelloImpl implements SayHello {

    private final String hello = "hello";

    @Override
    public String sayHello() {
        return this.hello;
    }
}
