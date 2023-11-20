package design.pattern.behave.mediator.simple;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColleagueA extends AbstractColleague {

    public ColleagueA(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    void send(Request req) {
        log.info("A send {}", req.toString());
    }

    @Override
    void receive(Request req) {
        log.info("A receive {}", req.toString());
    }
}
