package extra.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-12-16 20:01
 */
public class JsonUtilTest {

    @Test
    public void demo1() throws JsonProcessingException {
        final String[] strings = {"1", "2"};
        String[] strs = new String[3];
        strs[1] = "321";
        System.out.println(new ObjectMapper().writeValueAsString(strings));
        System.out.println(new ObjectMapper().writeValueAsString(Lists.newArrayList(1, 2)));
        System.out.println(new ObjectMapper().writeValueAsString(strs));
    }

    public static void main(String[] args) {
        String accEntityName = "天马--香港";
        if (accEntityName.contains("天马") && accEntityName.contains("香港")) {
            System.out.println("1");
        } else {
            System.out.println("2");
        }
    }

}
