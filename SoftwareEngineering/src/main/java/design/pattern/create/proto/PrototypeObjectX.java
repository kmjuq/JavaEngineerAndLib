package design.pattern.create.proto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/10 01:01
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
public class PrototypeObjectX implements Cloneable, Serializable {

    private String n1;
    private String n2;
    private String n3;
    private String n4;
    private String n5;
    private String n6;
    private String n7;
    private String n8;
    private String n9;
    private String n10;
    private String n11;
    private String n12;
    private PrototypeObjectY prototypeObjectY;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
