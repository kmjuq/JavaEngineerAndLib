package extra.yml;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Iterator;

@Slf4j
public class YmlLoadU {

    static Iterable<Object> loads = null;

    @BeforeAll
    static void init() {
        /**
         * loadAll 用来读取多文档，也就是没有 --- 标记的yml文件
         * load 用来读取单文档
         */
        loads = new Yaml().loadAll(new ClassPathResource("yml/yml-load.yml").getStream());
    }

    /**
     * 文档分割
     */
    @Test
    public void toFormatString() {
        for (Object o : loads) {
            log.info(o.toString());
        }
    }

    @Test
    public void referenced() {
        log.info(getDocument(3).toString());
    }

    @Test
    public void questionMark(){
        log.info(getDocument(4).toString());
    }

    private Object getDocument(int i) {
        final Iterator<Object> iterator = loads.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            final Object obj = iterator.next();
            if (count == i) {
                return obj;
            }
            count++;
        }
        return new Object();
    }

}
