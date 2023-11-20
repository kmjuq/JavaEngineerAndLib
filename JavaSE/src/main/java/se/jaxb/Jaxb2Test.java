package se.jaxb;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import se.jaxb.cdata.JaxbUtils;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class Jaxb2Test {

    @Test
    public void demo1() throws JAXBException {
        Person person = new Person().setAge("23").setName("wangjianyi");
        Address address = new Address().setCountry("CN").setProvince("yue").setCity("GUANGZHOU");
        person.setAddress(address);
        System.out.println(JaxbUtils.bean2xml(person));
        System.out.println(JaxbUtils.xml2bean(JaxbUtils.bean2xml(person), Person.class));
    }

    @Test
    public void demo3() throws JAXBException, XMLStreamException {
        Address address = new Address().setCountry("CN").setProvince("yue").setCity("GUANGZHOU");
        Person person = new Person().setAge("23").setName("wangjianyi").setAddress(address);
        System.out.println(JaxbUtils.obj2XmlWithCData(person));
    }

    @Test
    public void demo2() throws JAXBException {
        String xml = "<DATA>\n" +
                "\t<HEADER>\n" +
                "\t\t<SOURCE_FLG>OA</SOURCE_FLG>\n" +
                "\t\t<SOURCE_ID></SOURCE_ID>\n" +
                "\t\t<MDM_ID>1160817</MDM_ID>\n" +
                "\t\t<CREATION_DATE>2019-05-09 17:39:59</CREATION_DATE>\n" +
                "\t\t<VENDOR_NAME>LA TUERCA BOLTS CENTER & GENERAL MERCHANDISE</VENDOR_NAME>\n" +
                "\t\t<VENDOR_NAME_ALT></VENDOR_NAME_ALT>\n" +
                "\t\t<NAME>立即付款</NAME>\n" +
                "\t\t<JY_NAME>外部</JY_NAME>\n" +
                "\t\t<GX_NAME>生产型企业</GX_NAME>\n" +
                "\t\t<ZY_NAME>一般</ZY_NAME>\n" +
                "\t\t<PRINCIPAL_NAME></PRINCIPAL_NAME>\n" +
                "\t\t<ADDRESS1>tarlac</ADDRESS1>\n" +
                "\t\t<VENDOR_TYPE>供应商</VENDOR_TYPE>\n" +
                "\t\t<EMPLOYEE_NUMBER></EMPLOYEE_NUMBER>\n" +
                "\t\t<TAX_REFERENCE>231-744-160-000</TAX_REFERENCE>\n" +
                "\t\t<TERM_NAME>10000</TERM_NAME>\n" +
                "\t\t<TAX_CLASS_CODE></TAX_CLASS_CODE>\n" +
                "\t\t<PAYMENT_METHOD_CODE></PAYMENT_METHOD_CODE>\n" +
                "\t\t<VAT_CLASS></VAT_CLASS>\n" +
                "\t</HEADER>\n" +
                "\t<HEADER>\n" +
                "\t\t<SOURCE_FLG></SOURCE_FLG>\n" +
                "\t\t<SOURCE_ID></SOURCE_ID>\n" +
                "\t\t<MDM_ID>1163205</MDM_ID>\n" +
                "\t\t<CREATION_DATE>2019-05-29 04:30:46</CREATION_DATE>\n" +
                "\t\t<VENDOR_NAME>Tornado Import & Export</VENDOR_NAME>\n" +
                "\t\t<VENDOR_NAME_ALT></VENDOR_NAME_ALT>\n" +
                "\t\t<NAME>立即付款</NAME>\n" +
                "\t\t<JY_NAME>外部</JY_NAME>\n" +
                "\t\t<GX_NAME>贸易型企业</GX_NAME>\n" +
                "\t\t<ZY_NAME>一般</ZY_NAME>\n" +
                "\t\t<PRINCIPAL_NAME></PRINCIPAL_NAME>\n" +
                "\t\t<ADDRESS1>cairo - egypt</ADDRESS1>\n" +
                "\t\t<VENDOR_TYPE>供应商</VENDOR_TYPE>\n" +
                "\t\t<EMPLOYEE_NUMBER></EMPLOYEE_NUMBER>\n" +
                "\t\t<TAX_REFERENCE>401-623-440</TAX_REFERENCE>\n" +
                "\t\t<TERM_NAME>10000</TERM_NAME>\n" +
                "\t\t<TAX_CLASS_CODE></TAX_CLASS_CODE>\n" +
                "\t\t<PAYMENT_METHOD_CODE></PAYMENT_METHOD_CODE>\n" +
                "\t\t<VAT_CLASS></VAT_CLASS>\n" +
                "\t</HEADER>\n" +
                "\t<HEADER>\n" +
                "\t\t<SOURCE_FLG></SOURCE_FLG>\n" +
                "\t\t<SOURCE_ID></SOURCE_ID>\n" +
                "\t\t<MDM_ID>1163022</MDM_ID>\n" +
                "\t\t<CREATION_DATE>2019-05-29 04:26:49</CREATION_DATE>\n" +
                "\t\t<VENDOR_NAME>Al Mona Misr Import & Export（免代扣税）</VENDOR_NAME>\n" +
                "\t\t<VENDOR_NAME_ALT></VENDOR_NAME_ALT>\n" +
                "\t\t<NAME>立即付款</NAME>\n" +
                "\t\t<JY_NAME>外部</JY_NAME>\n" +
                "\t\t<GX_NAME>贸易型企业</GX_NAME>\n" +
                "\t\t<ZY_NAME>一般</ZY_NAME>\n" +
                "\t\t<PRINCIPAL_NAME></PRINCIPAL_NAME>\n" +
                "\t\t<ADDRESS1>ALEXANDRIA</ADDRESS1>\n" +
                "\t\t<VENDOR_TYPE>供应商</VENDOR_TYPE>\n" +
                "\t\t<EMPLOYEE_NUMBER></EMPLOYEE_NUMBER>\n" +
                "\t\t<TAX_REFERENCE>374-446-768</TAX_REFERENCE>\n" +
                "\t\t<TERM_NAME>10000</TERM_NAME>\n" +
                "\t\t<TAX_CLASS_CODE></TAX_CLASS_CODE>\n" +
                "\t\t<PAYMENT_METHOD_CODE></PAYMENT_METHOD_CODE>\n" +
                "\t\t<VAT_CLASS></VAT_CLASS>\n" +
                "\t</HEADER></DATA>";
        System.out.println(JaxbUtils.xml2bean(xml, ResponseDataGet.class));
    }

    @Test
    public void demo4() throws JAXBException {
        String xml = "<RESPONSE>\n" +
                "    <TRANSACTION_ID>1</TRANSACTION_ID>\n" +
                "    <RETURN_CODE>1</RETURN_CODE>\n" +
                "    <RETURN_MESG>1</RETURN_MESG>\n" +
                "    <RESPONSE_DATE>\n" +
                "        <ASSET>\n" +
                "            <BOOK_TYPE_NAME>1</BOOK_TYPE_NAME>\n" +
                "        </ASSET>\n" +
                "        <ASSET>\n" +
                "            <BOOK_TYPE_NAME>1</BOOK_TYPE_NAME>\n" +
                "        </ASSET>\n" +
                "        <ASSET>\n" +
                "            <BOOK_TYPE_NAME>1</BOOK_TYPE_NAME>\n" +
                "        </ASSET>\n" +
                "    </RESPONSE_DATE>\n" +
                "</RESPONSE>";
        System.out.println(JaxbUtils.xml2bean(xml, AssetWarpper.class));
    }

    @Test
    public void demo5() throws JAXBException {
        AssetWarpper assetWarpper = new AssetWarpper().setAsset(
                new ArrayList<AssetWarpper.Asset>() {{
                    add(new AssetWarpper.Asset());
                    add(new AssetWarpper.Asset());
                    add(new AssetWarpper.Asset());
                }}
        );
        System.out.println(JaxbUtils.bean2xml(assetWarpper));
    }

}
