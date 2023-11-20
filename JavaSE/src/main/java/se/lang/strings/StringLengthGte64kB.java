package se.lang.strings;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

@Slf4j
public class StringLengthGte64kB {

    @Test
    public void demo1(){
        boolean flag = true;
        int length = 1000;
        while (flag){
            String s = "我".repeat(length);
            if(s.getBytes(StandardCharsets.UTF_8).length>=65535){
                flag = false;
            }else{
                length+=1000;
            }
        }
        log.info("字符串长度为 {}",length);
    }

}
