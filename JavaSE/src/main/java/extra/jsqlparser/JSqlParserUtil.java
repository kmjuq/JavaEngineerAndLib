package extra.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

public class JSqlParserUtil {

    private static final String sql = """
            SELECT
            	IF( h.SETTLE_AMOUNT > 0, '标准', '贷项通知单' ) INVOICE_TYPE,
            	party.MERCHANT_NUMBER VENDOR_NUM,
            	z.party_site_number,
            	h.ACCOUNTING_DATE,
            	h.PO_SETTLE_NUMBER,
            	( SELECT GROUP_CONCAT( hbbi.BILL_CODE SEPARATOR '/' ) FROM hscs_bl_bill_info hbbi WHERE hbbi.CORRELATE_NUMBER = h.CORRELATE_NUMBER ) ACTUAL_INVOICE_NUM,
            	h.SETTLE_AMOUNT,
            	h.ACCOUNTING_DATE,
            	h.COMMENTS,
            	h.PAY_CONDITION,
            	h.PAY_METHOD,
            	et.EXRATE_TYPE_CODE,
            	h.EXCHANGE_RATE,
            	h.EXRATE_DATE,
            	cd.CURRENCY_CODE,
            	h.SIGN_COMPANY,
            	h.DEPARTMENT_CODE,
            	h.TAX_AMOUNT,
            	h.PROJECT_CODE,
            	h.CONTRACT_NUMBER,
            	party.MERCHANT_NUMBER PARTY_CODE,
            	bt.BUSINESS_TYPE_CODE,
            	h.BUSINESS_NATURE,
            	0 SPARE1,
            	0 SPARE2,
            	bt.DESCRIPTION FUNDING_PLAN_ITEM,
            	h.DEPARTMENT_CODE,
            	h.CONTRACT_NUMBER,
            	h.BUSINESS_NATURE BUSINESS_LINE,
            	'ITEM' LINE_TYPE,
            	tbi.TAX_CODE,
            	h.SETTLE_AMOUNT LINE_AMT,
            	h.COMMENTS LINE_DESC,
            	h.ACCOUNTING_DATE,
            	h.CASH_FLOW\s
            FROM
            	hscs_ap_po_settle_orders h
            	LEFT JOIN hscs_pub_party_merchant party ON h.MERCHANT_ID = party.MERCHANT_ID
            	LEFT JOIN hscs_pub_party_sites z ON h.PARTY_SITE_ID = z.PARTY_SITE_ID
            	LEFT JOIN HSCS_PUB_EXRATE_TYPE et ON et.EXRATE_TYPE_ID = h.EXRATE_TYPE_ID
            	LEFT JOIN HSCS_PUB_CURRENCY_DETAILS cd ON cd.CURRENCY_ID = h.CURRENCY_ID
            	LEFT JOIN HSCS_PUB_BUSINESS_TYPE_B bt ON h.BUSINESS_TYPE_ID = bt.BUSINESS_TYPE_ID,
            	hscs_ap_settle_order_lines l
            	LEFT JOIN hscs_pub_tax_basic_info tbi ON l.TAX_ID = tbi.TAX_ID\s
            WHERE
            	h.PO_SETTLE_ID = l.PO_SETTLE_ID\s
            	AND h.SETTLE_ORDER_STATUS = 'APPROVED'\s
            	AND ( 1 = 2 OR h.PO_SETTLE_NUMBER IN ( '321', '123' ))
            """;

    @Test
    public void demo() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        System.out.println(statement.toString());
    }

    @Test
    public void demo1() throws JSQLParserException {
        CCJSqlParserManager pm = new CCJSqlParserManager();
        Statement statement = pm.parse(new StringReader(sql));

        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List tableList = tablesNamesFinder.getTableList(selectStatement);
        for (Object o : tableList) {
            System.out.println(o);
        }
    }

    @Test
    public void demo2() throws JSQLParserException, ParseException {
        SimpleNode node = (SimpleNode)CCJSqlParserUtil.parseAST(sql);
        node.dump("");
    }

}
