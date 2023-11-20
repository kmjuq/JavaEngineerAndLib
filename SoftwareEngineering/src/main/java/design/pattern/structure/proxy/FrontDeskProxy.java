package design.pattern.structure.proxy;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class FrontDeskProxy implements IFrontDesk{

    private IFrontDesk frontDesk;
    private AtomicInteger ticketNum = new AtomicInteger(0);

    public FrontDeskProxy(IFrontDesk frontDesk){
        this.frontDesk = frontDesk;
    }

    @Override
    public void checkTicket() {
        ticketNum.addAndGet(1);
        frontDesk.checkTicket();
    }

    public void ticketNumShow(){
        log.info("checked ticket number is {}",ticketNum.get());
    }
}
