package se.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 供应商接口响应报文对应的 bean
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-07-18 21:29
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DATA")
public class ResponseDataGet {

    @XmlElement(name = "HEADER")
    List<Header> header;

    @Setter
    @Getter
    @ToString
    @XmlRootElement(name = "HEADER")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        @XmlElement(name = "SOURCE_FLG")
        private String sourceFlg = "";
        @XmlElement(name = "SOURCE_ID")
        private String sourceId = "";
        /**
         * MDM供应商编号
         */
        @XmlElement(name = "MDM_ID")
        private String mdmId = "";
        @XmlElement(name = "CREATION_DATE")
        private String creationDate = "";
        /**
         * 供应商名称
         */
        @XmlElement(name = "VENDOR_NAME")
        private String vendorName = "";
        /**
         * 供应商别名
         */
        @XmlElement(name = "VENDOR_NAME_ALT")
        private String vendorNameAlt = "";
        @XmlElement(name = "NAME")
        private String name = "";
        /**
         * 交易关系
         */
        @XmlElement(name = "JY_NAME")
        private String jyName = "";
        @XmlElement(name = "GX_NAME")
        private String gxName = "";
        @XmlElement(name = "ZY_NAME")
        private String zyName = "";
        @XmlElement(name = "PRINCIPAL_NAME")
        private String principalName = "";
        @XmlElement(name = "ADDRESS1")
        private String address1 = "";
        /**
         * 供应商类型
         */
        @XmlElement(name = "VENDOR_TYPE")
        private String vendorType = "";
        @XmlElement(name = "EMPLOYEE_NUMBER")
        private String employeeNumber = "";
        @XmlElement(name = "TAX_REFERENCE")
        private String taxReference = "";
        @XmlElement(name = "TERM_NAME")
        private String termName = "";
        @XmlElement(name = "TAX_CLASS_CODE")
        private String taxClassCode = "";
        /**
         * 默认付款方法
         */
        @XmlElement(name = "PAYMENT_METHOD_CODE")
        private String paymentMethodCode = "";
        @XmlElement(name = "VAT_CLASS")
        private String vatClass = "";
    }

}
