package se.jaxb;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Setter
@Getter
@Accessors(chain = true)
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROOT_PERSON")
class Person {

    @XmlElement(name = "NAME")
//    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String name;

    @XmlElement(name = "AGE")
//    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String age;

    @XmlElement(name = "ADDRESS")
    private Address address;

}
