package extra.lombok;

import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * toString 注解样例
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-09-13 12:20
 */
@ToString(includeFieldNames = false, callSuper = true, doNotUseGetters = true, onlyExplicitlyIncluded = true)
public class ToStringExample extends Parent {
    private static String staticField;
    private String include;
    @ToString.Include(rank = -2)
    private String rank1;
    @ToString.Include(rank = 1, name = "rank3")
    private String rank2;
    @Getter
    @ToString.Include
    private String getter;

    private String exclude;
}

class Parent {
    private String parentField;
}
