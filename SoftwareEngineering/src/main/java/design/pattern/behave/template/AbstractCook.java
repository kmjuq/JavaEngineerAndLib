package design.pattern.behave.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractCook {

    abstract void cook();

    void wash() {
        log.info("washing vegetables");
    }

    void dishUp() {
        log.info("dish up");
    }

}
