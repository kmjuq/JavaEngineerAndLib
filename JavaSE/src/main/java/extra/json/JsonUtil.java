package extra.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * JSON处理工具类,依赖 jackson
 * </p>
 *
 * @author mengjian.ke@hand-china.com
 * 2019/8/12 8:45
 */
public class JsonUtil {

    private static final ObjectMapper om = new ObjectMapper();

    /**
     * [{},{}] 转 list
     *
     * @param arrayStr 数组字符串
     * @param clz      类
     */
    public static <T> List<T> arrayStr2List(@NonNull String arrayStr, Class<T> clz) throws IOException {
        JavaType javaType = om.getTypeFactory().constructParametricType(List.class, clz);
        return om.readValue(arrayStr, javaType);
    }

    /**
     * {} 转 obj
     *
     * @param content json字符串
     * @param clz     类型
     */
    public static <T> T str2Obj(String content, Class<T> clz) throws IOException {
        return om.readValue(content, clz);
    }

    /**
     * 对象转字符串
     */
    public static String obj2Str(Object obj) throws JsonProcessingException {
        return om.writeValueAsString(obj);
    }
}
