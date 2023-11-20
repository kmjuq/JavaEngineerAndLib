package extra.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.junit.jupiter.api.Test;

public class ResolveXmlStr {

    @Test
    public void demo1() throws DocumentException {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><esb:RESPONSE xmlns:esb=\"http://w3.ibm.com/gbs/ais/ei/esb\"><esb:RETURN_CODE>S22</esb:RETURN_CODE><esb:RETURN_DATA/><esb:RETURN_DESC>其他错误</esb:RETURN_DESC></esb:RESPONSE></soapenv:Body></soapenv:Envelope>";

        String replace = xmlStr.replace("esb:", "");
        Document document = DocumentHelper.parseText(replace);
        String returnCode = document.selectSingleNode("//RESPONSE/RETURN_CODE").getText();
        String returnData = document.selectSingleNode("//RESPONSE/RETURN_DATA").getText();
        String returnDesc = document.selectSingleNode("//RESPONSE/RETURN_DESC").getText();
        System.out.printf("%s %s %s", returnCode, returnData, returnDesc);
    }

    /**
     * 直接获取节点的属性
     */
    @Test
    public void demo2() throws DocumentException {
        String xmlStr = "<OrderResponse filter_result=\"2\" mailno=\"SF7444400596655\" orderid=\"DEVIN20190910003\"/>";
        Document document = DocumentHelper.parseText(xmlStr);
        Node node = document.selectSingleNode("//OrderResponse");
        String filter_result = node.valueOf("@filter_result");
        String mailno = node.valueOf("@mailno");
        String orderid = node.valueOf("@orderid");
        System.out.printf("%s %s %s%n", filter_result, mailno, orderid);
    }

}
