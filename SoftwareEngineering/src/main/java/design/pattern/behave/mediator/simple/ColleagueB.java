package design.pattern.behave.mediator.simple;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColleagueB extends AbstractColleague {

    public ColleagueB(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    void send(Request req) {
        log.info("B send {}", req.toString());
    }

    @Override
    void receive(Request req) {
        log.info("B receive {}", req.toString());
    }
}
