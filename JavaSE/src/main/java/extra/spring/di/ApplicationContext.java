package extra.spring.di;

import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/10/30 10:15
 */
public interface ApplicationContext {

    static <T> T getBean(Class<T> requiredType) {
        final ConcurrentMap<Class<?>, Object> bean = ScanUtil.getBean("com.extra.spring", BBB.class);
        return (T) bean.get(requiredType);
    }

}
