package se.lang.strings;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 * 字符串格式化
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-07-14 22:52
 */
public class StringFormatForTest {

    @Test
    public void demo1() {
        formatStr("%s , %s", "按顺序定位", "测试");
        formatStr("%2$s, %1$s", "按照索引定位", "测试");
        formatStr("%06d", 1242);
        formatStr("%.3f", new BigDecimal("123.321623"));

        Date date = new Date();
        formatStr("%tc", date);
        formatStr("%tF", date);
        formatStr("%tD", date);
        formatStr("%tr", date);
        formatStr("%tT", date);
        formatStr("%tR", date);
    }

    @Test
    public void demo2() {
        String str = "T201R1";
        System.out.println(str.substring(str.indexOf("R") + 1));
        System.out.println(str.substring(1, str.indexOf("R")));
        System.out.println(str.substring(0, str.indexOf("R")));
    }

    @Test
    public void demo3() {
        String str = "kmj,,sdf";
        String[] split = str.split(",");
        System.out.println(Arrays.toString(split));
    }

    private void formatStr(String format, Object... objs) {
        System.out.printf((format) + "%n", objs);
    }

}
