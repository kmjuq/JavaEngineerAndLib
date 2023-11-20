package design.pattern.behave.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyA implements Strategy {

    @Override
    public void strategy() {
        log.info("log strategy a");
    }

}
