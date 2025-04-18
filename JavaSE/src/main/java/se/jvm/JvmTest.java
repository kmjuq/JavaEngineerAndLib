package se.jvm;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class JvmTest {

    @Test
    public void JvmThreadStack(){
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        for (String arg : arguments) {
            if (arg.startsWith("-Xss")) {
                System.out.println("当前JVM使用的线程栈大小设置为: " + arg);
                return;
            }
        }
        System.out.println("未显式设置线程栈大小，使用JVM默认值。");
    }

}
