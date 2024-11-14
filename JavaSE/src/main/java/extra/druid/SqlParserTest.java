package extra.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class SqlParserTest {

    @Test
    public void demo1() {
        String sql = """
                        SELECT CI.CARD_NO, CI.CANCEL_DT
                        FROM CSYS.CARD_INFO CI
                        	INNER JOIN CSYS.ACCT_INFO AI ON CI.ACCT_NO = AI.ACCT_NO
                        WHERE AI.ACCT_ST IN ('04', '07')
                """;
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
        MySqlSchemaStatVisitor mySqlSchemaStatVisitor = new MySqlSchemaStatVisitor();

        for (SQLStatement sqlStatement : sqlStatements) {
            sqlStatement.accept(mySqlSchemaStatVisitor);
        }

        System.out.println(mySqlSchemaStatVisitor.getColumns());
        System.out.println(mySqlSchemaStatVisitor.getTables());
        System.out.println(mySqlSchemaStatVisitor.getRelationships());
        System.out.println(mySqlSchemaStatVisitor.getConditions());
        System.out.println(SQLUtils.format(sql, DbType.mysql));
    }

}
