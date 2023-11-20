package extra.spring.di;

import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/10/31 04:27
 */
public class BeanTest {

    @Test
    public void demo() {
        final HelloController bean = ApplicationContext.getBean(HelloController.class);
        System.out.println(bean.requestMapping("kmj"));
    }

}
