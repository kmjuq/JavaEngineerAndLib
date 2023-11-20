package extra.spring.di;


import com.google.common.collect.Maps;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/10/31 00:21
 */
public class ScanUtil {

    public static ConcurrentMap<Class<?>, Object> getBean(String packages, Class<? extends Annotation> anno) {
        ConcurrentMap<Class<?>, Object> cla = null;
        try {
            //  扫描实例化
            cla = getClassForAnnotation(packages, anno);
            //  处理依赖
            dealDependency(cla);
        } catch (IOException e) {
            // do nothing
        }
        return cla;
    }

    public static ConcurrentMap<Class<?>, Object> getClassForAnnotation(String packages, Class<? extends Annotation> anno) throws IOException {
        final ConcurrentMap<Class<?>, Object> cm = Maps.newConcurrentMap();
        cm.put(HelloController.class, createProxy(HelloController.class));
        cm.put(HelloService.class, createProxy(HelloService.class));
        return cm;
    }

    private static void dealDependency(ConcurrentMap<Class<?>, Object> cm) {
        try {
            for (Map.Entry<Class<?>, Object> entry : cm.entrySet()) {
                final Class<?> k = entry.getKey();
                final Object v = entry.getValue();
                Field[] declaredFields = k.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    Class<?> type = declaredField.getType();
                    if (cm.containsKey(type)) {
                        declaredField.set(v, cm.get(type));
                    }
                }
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static <T> T createProxy(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        //申明要代理的类也可以是接口
        enhancer.setSuperclass(cls);
        //申明回调函数
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        return (T) enhancer.create();
    }

}
