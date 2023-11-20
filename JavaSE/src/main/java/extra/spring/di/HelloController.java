package extra.spring.di;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/10/30 10:20
 */
@BBB
public class HelloController {

    @DI
    HelloService helloService;

    public String requestMapping(String name) {
        return helloService.HelloWorld(name);
    }

}
