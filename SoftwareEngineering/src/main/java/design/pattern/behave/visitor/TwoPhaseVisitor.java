package design.pattern.behave.visitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwoPhaseVisitor extends VisitorAdaptor {

    @Override
    public void visit(TwoPhase twoPhase) {
        log.info("log two phase visitor {}",twoPhase.getContent());
    }
}
