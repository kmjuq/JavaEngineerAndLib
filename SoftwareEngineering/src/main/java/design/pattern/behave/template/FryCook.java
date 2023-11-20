package design.pattern.behave.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FryCook extends AbstractCook {
    @Override
    void cook() {
        log.info("frying cook");
    }
}
