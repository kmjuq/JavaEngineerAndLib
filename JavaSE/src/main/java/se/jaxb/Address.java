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
@XmlRootElement(name = "ROOT_ADDRESS")
class Address {

    @XmlElement(name = "COUNTRY")
//    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String country;

    @XmlElement(name = "PROVINCE")
//    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String province;

    @XmlElement(name = "CITY")
//    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String city;

}
