package extra.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.ToString;

/**
 * <p>
 * 用于解决同时使用 lombok,jackson 注解时遇到的序列化key和字段名field不一致时不能正确序列化的解决方案
 * </p>
 *
 * @author mengjian.ke@hand-china.com
 * 2019/8/12 13:37
 */
@ToString
@Builder(builderClassName = "XmysRequestBuilder")
@JsonDeserialize(builder = XmysRequest.XmysRequestBuilder.class)
public class XmysRequest {

    @JsonProperty("F_YWXT")
    @Builder.Default
    private final String fYwxt = "1";
    @JsonProperty("F_DJBH")
    private final String fDjbh;

    @JsonPOJOBuilder(withPrefix = "")
    public static class XmysRequestBuilder {

    }

    public static void main(String[] args) throws JsonProcessingException {
        XmysRequest build = XmysRequest.builder().fDjbh("2321").build();
        System.out.println(JsonUtil.obj2Str(build));
    }
}
