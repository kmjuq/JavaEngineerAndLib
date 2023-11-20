package se.optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/4/3 15:09
 */
@Setter
@Getter
@ToString
public class Supplier {

    private String name;

    private String phone;

    private Address address;

    private List<Fruit> fruits;

}
