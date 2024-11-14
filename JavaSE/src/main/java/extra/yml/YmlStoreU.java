package extra.yml;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class YmlStoreU {

    public static void main(String[] args) {
        final List<LinkedHashMap<String, String>> objs = new ArrayList<>();
        final LinkedHashMap<String, String> obj1 = new LinkedHashMap<>() {{
            put("kmj1", "name");
        }};
        final LinkedHashMap<String, String> obj2 = new LinkedHashMap<>() {{
            put("kmj2", "name");
        }};
        objs.add(obj1);
        objs.add(obj2);
        log.info(new Yaml().dumpAll(objs.iterator()));
    }

}
