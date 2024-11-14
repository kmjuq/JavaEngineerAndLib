package jep;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * 涉及jackson序列化时可以使用
 * <a href="https://github.com/FasterXML/jackson-future-ideas/issues/46">jackson</a>
 */
@Slf4j
public class RecordJep {

    @Test
    public void demo1() throws JsonProcessingException {
        final Example example = new Example("test");
        log.info(example.toString());
        final ObjectMapper ob = new ObjectMapper();
        ob.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        final String json = ob.writeValueAsString(example);
        log.info(json);
        final Example example1 = ob.readValue(json, Example.class);
        log.info(example1.toString());
    }

    record Example(String name) implements Serializable {

    }

//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Example {
//        private String name;
//    }

    record Range(int lo, int hi) {
        Range {
            if (lo > hi)  // referring here to the implicit constructor parameters
                throw new IllegalArgumentException(String.format("(%d,%d)", lo, hi));
        }
    }
}
