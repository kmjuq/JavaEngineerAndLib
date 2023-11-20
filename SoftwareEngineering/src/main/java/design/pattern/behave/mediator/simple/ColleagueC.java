package design.pattern.behave.mediator.simple;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColleagueC extends AbstractColleague {

    public ColleagueC(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    void send(Request req) {
        log.info("C send {}", req.toString());
    }

    @Override
    void receive(Request req) {
        log.info("C receive {}", req.toString());
    }
}
