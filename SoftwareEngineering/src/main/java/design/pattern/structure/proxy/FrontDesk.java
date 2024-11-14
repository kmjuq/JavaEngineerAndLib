package design.pattern.structure.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FrontDesk implements IFrontDesk {
    @Override
    public void checkTicket() {
        log.info("check ticket");
    }
}
