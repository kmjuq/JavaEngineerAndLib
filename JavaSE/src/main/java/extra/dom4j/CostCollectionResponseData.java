package extra.dom4j;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 费用归集返回bean
 * </p>
 *
 * @author maoting.tang@hand-china.com
 * 2019/9/1 22:18
 */


@Setter
@Getter
@ToString
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESPONSE")
public class CostCollectionResponseData {

    /**
     *
     * <RESPONSE>
     * 	<TRANSACTION_ID>12211</TRANSACTION_ID>
     * 	<RETURN_CODE>S000A000</RETURN_CODE>
     * 	<RETURN_MESG></RETURN_MESG>
     * 	<RESPONSE_DATE>
     * 		<JOURNAL>
     * 			<JOURNAL_NAME>961517530 采购发票 CNY</JOURNAL_NAME>
     * 			<JOURNAL_DESC>/编号: SGFP0420181008 / 张楠, 0329</JOURNAL_DESC>
     * 			<JE_SOURCE_NAME>应付账款</JE_SOURCE_NAME>
     * 			<JE_CATEGORY_NAME>采购发票</JE_CATEGORY_NAME>
     * 			<PERIOD_NAME>2018-10</PERIOD_NAME>
     * 			<EFFECTIVE_DATE>2018-10-08</EFFECTIVE_DATE>
     * 			<COMPANY_SEGMENT>潍坊新希望六和饲料科技有限公司</COMPANY_SEGMENT>
     * 			<ACCOUNT_SEGMENT>研发费用-可控-职工薪酬-年金缴费</ACCOUNT_SEGMENT>
     * 			<PROJECT_SEGMENT>乳仔猪料关健技术集成研究及产业化</PROJECT_SEGMENT>
     * 			<PROJECT_NUM>180005</PROJECT_NUM>
     * 			<PROJECT_SEGMENT_ATTR>乳仔猪料关健技术集成研究及产业化</PROJECT_SEGMENT_ATTR>
     * 			<PROJECT_NUM_ATTR>180005</PROJECT_NUM_ATTR>
     * 			<ENTERED_DR>193</ENTERED_DR>
     * 		</JOURNAL>
     * 	</RESPONSE_DATE>
     * </RESPONSE>
     * 校验失败返回数据：
     * <RESPONSE>
     * 	<TRANSACTION_ID>12211</TRANSACTION_ID>
     * 	<RETURN_CODE>E0002D15</RETURN_CODE>
     * 	<RETURN_MESG>最后更新时间至不能为空！</RETURN_MESG>
     * 	<RESPONSE_DATE/>
     * </RESPONSE>
     */


    /**
     * 事务ID
     */
    @XmlElement(name = "TRANSACTION_ID")
    private String transactionId = "";

    /**
     * 响应码
     */
    @XmlElement(name = "RETURN_CODE")
    private String returnCode = "";

    /**
     * 响应消息
     */
    @XmlElement(name = "RETURN_MESG")
    private String returnMesg = "";

    @XmlElementWrapper(name = "RESPONSE_DATE")
    @XmlElement(name = "JOURNAL")
    private List<Collection> collections;

    @Setter
    @Getter
    @ToString
    @XmlRootElement(name = "JOURNAL")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Collection {
        /**
         * 日记账名称
         */
        @XmlElement(name = "JOURNAL_NAME")
        private String journalName = "";
        /**
         * 日记账说明
         */
        @XmlElement(name = "JOURNAL_DESC")
        private String journalDesc = "";
        /**
         * 来源
         */
        @XmlElement(name = "JE_SOURCE_NAME")
        private String jeSourceName = "";
        /**
         * 类别
         */
        @XmlElement(name = "JE_CATEGORY_NAME")
        private String jeCategoryName = "";
        /**
         * 期间
         */
        @XmlElement(name = "PERIOD_NAME")
        private String periodName = "";
        /**
         * 有效日期
         */
        @XmlElement(name = "EFFECTIVE_DATE")
        private String effectiveDate = "";
        /**
         * 公司段
         */
        @XmlElement(name = "COMPANY_SEGMENT")
        private String companySegment = "";
        /**
         * 科目段
         */
        @XmlElement(name = "ACCOUNT_SEGMENT")
        private String accountSegment = "";
        /**
         * 子公司项目
         */
        @XmlElement(name = "PROJECT_SEGMENT")
        private String projectSegment = "";
        /**
         * 子公司项目
         */
        @XmlElement(name = "PROJECT_NUM")
        private String projectNum = "";
        /**
         * 说明性弹性域项目
         */
        @XmlElement(name = "PROJECT_SEGMENT_ATTR")
        private String projectSegmentAttr = "";
        /**
         * 说明性弹性域项目编码
         */
        @XmlElement(name = "PROJECT_NUM_ATTR")
        private String projectNumAttr = "";
        /**
         * 借项
         */
        @XmlElement(name = "ENTERED_DR")
        private String enteredCr = "";
    }
}
