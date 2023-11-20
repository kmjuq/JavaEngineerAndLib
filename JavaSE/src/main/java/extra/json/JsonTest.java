package extra.json;

import cn.hutool.json.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/9/22 22:20
 */
public class JsonTest {
    public static void main(String[] args) {
    }

    // 内部类
    public JSONArray test() {
        return new JSONArray() {
            {

            }
        };
    }

    @Test
    public void demo1() {
        // fastjson
        final HashMap<String, String> kmj = new HashMap<String, String>() {{
            put("name", "kmj");
            put("age", "27");
            put("height", null);
        }};
//        System.out.println(JSONObject.toJSONString(kmj));
    }

    @Test
    public void demo2() throws JsonProcessingException {
        // jackson
        final ObjectMapper om = new ObjectMapper();
        final HashMap<String, String> kmj = new HashMap<String, String>() {{
            put("name", "kmj");
            put("age", "27");
            put("height", null);
        }};
        System.out.println(om.writeValueAsString(kmj));
    }

}
