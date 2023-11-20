package extra.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

@Setter
@Getter
@ToString
public class SetterGetterExample {

    // @Setter(onMethod_={@JsonProperty("ONX_EXAMPLE")})
//    @JsonProperty("ONX_EXAMPLE")
    private String ONxExamPle;

    @JsonProperty("F_YWXT")
    private String fYwxt;

    private boolean noIsPrefix;

    private Boolean success;

    /**
     * 生成的方法 setter 和 getter方法中会去掉 accessors
     */
    @Accessors(prefix = "accessors")
    private String accessorsPrefix;

    /**
     * 生成链式调用的 setter 方法
     */
    @Accessors(chain = true)
    @NonNull
    private String accessorsChain;

    /**
     * 生成链式调用,且方法名为 fluent 风格的 setter/getter 方法
     */
    @Accessors(fluent = true)
    private String accessorsFluent;

    /**
     * 不生成 setter getter 方法
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String accessLevelNone;

    /**
     * 生成私有的 setter getter 方法
     */
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private String accessLevelPrivate;

    @Test
    public void demo2() throws JsonProcessingException {
        final SetterGetterExample lombokExample = new SetterGetterExample();
        lombokExample.setONxExamPle("TEST");
        lombokExample.setFYwxt("JSON_PROPERTY");
        System.out.println(new ObjectMapper().writeValueAsString(lombokExample));
    }
}
