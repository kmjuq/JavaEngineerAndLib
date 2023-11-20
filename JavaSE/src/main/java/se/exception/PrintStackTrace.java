package se.exception;

import cn.hutool.core.util.RandomUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2021/2/16 10:05
 */
public class PrintStackTrace {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    exception();
                } catch (Exception ex) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    System.out.println(sw);
                }
            }).start();
        }
    }

    public static void exception() {
        throw new RuntimeException(RandomUtil.randomString(100));
    }

}
