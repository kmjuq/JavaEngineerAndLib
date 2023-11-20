package design.pattern.create.single.simple;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleSingle {

    private static final SimpleSingle simpleSingle = new SimpleSingle();

    private SimpleSingle(){}

    public static SimpleSingle getInstance(){
        return simpleSingle;
    }

    public void exec(){
        ThreadUtil.sleep(2000);
        log.info("{} 已 sleep 2000",Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final SimpleSingle instance = SimpleSingle.getInstance();
        ThreadUtil.concurrencyTest(4, instance::exec);
    }
}
