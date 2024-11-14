package se.jaxb;


import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Accessors(chain = true)
@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssetWarpper {

    @XmlElement(name = "TRANSACTION_ID")
    private String transactionId = "1";

    @XmlElement(name = "RETURN_CODE")
    private String returnCode = "1";

    @XmlElement(name = "RETURN_MESG")
    private String returnMesg = "1";

    /**
     * 属性默认的空集合 不要使用 Collections.emptyList() ,这个是只读版本的空集合
     */
    @XmlElementWrapper(name = "RESPONSE_DATE")
    @XmlElement(name = "ASSET")
    private List<Asset> asset = new ArrayList<>();

    @Setter
    @Getter
    @ToString
    @XmlRootElement(name = "ASSET")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Asset {

        @XmlElement(name = "BOOK_TYPE_NAME")
        private String bookTypeName = "1";

    }
}
