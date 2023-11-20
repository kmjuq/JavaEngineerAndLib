package extra.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用于解决同时使用 lombok,jackson 注解时遇到的序列化key和字段名field不一致时不能正确序列化的解决方案
 * </p>
 *
 * @author mengjian.ke@hand-china.com
 * 2019/8/12 13:37
 */
@ToString
@Getter
@Accessors(chain = true)
public class XmysRequestMethod {

    //@Setter(onMethod_ = {@JsonProperty("F_YWXT")})
    private final String fYwxt = "1";
    //@Setter(onMethod_ = {@JsonProperty("F_DJBH")})
    private String fDjbh;

    public static void main(String[] args) throws JsonProcessingException {
        //XmysRequestMethod build = new XmysRequestMethod().setFDjbh("2321");
        //System.out.println(JsonUtil.obj2Str(build));
    }
}
