package design.pattern.behave.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoilCook extends AbstractCook {
    @Override
    void cook() {
        log.info("boiling cook");
    }
}
