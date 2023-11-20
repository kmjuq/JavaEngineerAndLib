package se.lang.strings;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StringCheckParam {

    private void checkStringParam(String... args) {
        for (String arg : args)
            if (StrUtil.isEmpty(arg))
                throw new IllegalArgumentException(
                        String.format("参数错误:参数为空,参数列表为 %s", Arrays.toString(args))
                );
    }

    @Test
    public void demo1() {
        checkStringParam("123", "456", "87654", "1111");
    }

}
