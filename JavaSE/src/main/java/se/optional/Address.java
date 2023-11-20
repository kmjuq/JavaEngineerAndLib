package se.optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/4/3 15:10
 */
@Setter
@Getter
@ToString
public class Address {

    private String country;

    private String province;

    private String city;

    private String detail;

}
